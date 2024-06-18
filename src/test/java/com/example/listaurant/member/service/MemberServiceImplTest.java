package com.example.listaurant.member.service;

import com.example.listaurant.member.controller.port.MemberService;
import com.example.listaurant.member.repository.MemberEntity;
import com.example.listaurant.member.repository.MemberRepositoryImpl;
import com.example.listaurant.member.service.port.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class MemberServiceImplTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberService memberService;
    @Test
    @DisplayName("이미 존재하는 사용자인지 확인합니다.")
    void isDuplicationCheck() {
        // given
        MemberEntity mem = MemberEntity.builder()
                .email("test@test.com")
                .passwd("123")
                .pno("123")
                .role("USER")
                .build();

        memberRepository.save(mem);
        // when
        // then
        assertThat(memberService.isDuplicationEmail("test@test.com")).isTrue();
    }
}