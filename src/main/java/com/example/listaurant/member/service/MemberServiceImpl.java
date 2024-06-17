package com.example.listaurant.member.service;

import com.example.listaurant.member.controller.port.MemberService;
import com.example.listaurant.member.controller.request.SignUpRequest;
import com.example.listaurant.member.repository.MemberEntity;
import com.example.listaurant.member.service.port.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

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

    @Override
    public boolean isDuplicationEmail(String email) {
        boolean result = memberRepository.findByEmail(email).isPresent();
        return result;
    }
}
