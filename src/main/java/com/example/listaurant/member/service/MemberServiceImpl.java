package com.example.listaurant.member.service;

import com.example.listaurant.member.controller.port.MemberService;
import com.example.listaurant.member.infra.MemberEntity;
import com.example.listaurant.member.service.dto.MemberDto;
import com.example.listaurant.member.service.port.MailSender;
import com.example.listaurant.member.service.port.MemberRepository;
import lombok.RequiredArgsConstructor;
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
        MemberEntity member = MemberEntity.builder()
                .email(memberDto.getEmail())
                .pno(memberDto.getPno())
                .passwd(passwordEncoder.encode(memberDto.getPasswd()))
                .role("USER")
                .build();
        memberRepository.save(member);
    }

    @Transactional(readOnly = true)
    @Override
    public boolean isDuplicationEmail(String email) {
        return memberRepository.findByEmail(email).isPresent();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<MemberEntity> findByEmail(String email) {
        return memberRepository.findByEmail(email);
    }
    @Transactional(readOnly = true)
    @Override
    public Optional<MemberEntity> findById(Long id) {
        return memberRepository.findById(id);
    }

    @Override
    @Transactional
    public void update(MemberDto memberDto) {
        memberRepository.update(MemberEntity.builder().memberId(memberDto.getMemberId())
                .pno(memberDto.getPno())
                .passwd(memberDto.getPasswd())
                .role(memberDto.getRole())
                .build());
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
        mailSender.send(memberDto.getEmail(), uuid);
        if(isDuplicationEmail(memberDto.getEmail())){
            MemberEntity memberEntity = memberRepository.findByEmail(memberDto.getEmail()).get();
            memberEntity.setPasswd(passwordEncoder.encode(uuid));
            memberRepository.update(memberEntity);
        }
    }
}
