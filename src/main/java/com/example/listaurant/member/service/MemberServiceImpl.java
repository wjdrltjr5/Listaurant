package com.example.listaurant.member.service;

import com.example.listaurant.member.controller.port.MemberService;
import com.example.listaurant.member.controller.request.SignUpRequest;
import com.example.listaurant.member.controller.request.UpdateRequest;
import com.example.listaurant.member.repository.MemberEntity;
import com.example.listaurant.member.service.port.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public void save(SignUpRequest signUpRequest) {
        MemberEntity member = MemberEntity.builder()
                .email(signUpRequest.getEmail())
                .pno(signUpRequest.getPno())
                .passwd(passwordEncoder.encode(signUpRequest.getPasswd()))
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
    public void update(UpdateRequest updateRequest) {
        memberRepository.update(MemberEntity.builder().memberId(updateRequest.getMemberId())
                .pno(updateRequest.getPno()).build());
    }

    @Override
    public void delete(Long id) {
        memberRepository.delete(id);
    }
}
