package com.example.listaurant.member.service;

import com.example.listaurant.member.controller.port.MemberService;
import com.example.listaurant.member.service.dto.MemberDto;
import com.example.listaurant.member.service.port.MailSender;
import com.example.listaurant.member.service.port.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final MailSender mailSender;

    @Transactional
    @Override
    public void save(MemberDto memberDto) {
        memberDto.setPasswd(passwordEncoder.encode(memberDto.getPasswd()));
        memberRepository.save(memberDto);
        mailSender.sendCertificationCode(memberDto.getEmail(), memberDto.getCertificationCode());
    }

    @Transactional(readOnly = true)
    @Override
    public boolean isDuplicationEmail(String email) {
        return memberRepository.findByEmail(email).isPresent();
    }

    @Override
    public boolean isDuplicationNickname(String nickname) {
        return memberRepository.findByNickname(nickname).isPresent();
    }


    @Transactional(readOnly = true)
    @Override
    public Optional<MemberDto> findByEmail(String email) {
        return memberRepository.findByEmail(email);
    }
    @Transactional(readOnly = true)
    @Override
    public Optional<MemberDto> findById(Long id) {
        return memberRepository.findById(id);
    }

    @Override
    @Transactional
    public void update(MemberDto memberDto) {
        memberRepository.update(memberDto);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        memberRepository.delete(id);
    }

    @Override
    @Transactional
    public void sendTempPassword(MemberDto memberDto) {
        String uuid = UUID.randomUUID().toString();
        mailSender.sendTempPassword(memberDto.getEmail(), uuid);
        if(isDuplicationEmail(memberDto.getEmail())){
            memberDto = memberRepository.findByEmail(memberDto.getEmail()).get();
            memberDto.setPasswd(passwordEncoder.encode(uuid));
            memberRepository.update(memberDto);
        }
    }

    @Transactional
    @Override
    public void activeStatus(String email) {
        MemberDto dto = memberRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("등록되지 않은 이메일입니다."));
        dto.activeStatus();
        memberRepository.update(dto);
    }
}
