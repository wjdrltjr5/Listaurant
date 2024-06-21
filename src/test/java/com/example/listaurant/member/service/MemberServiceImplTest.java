//package com.example.listaurant.member.service;
//
//import com.example.listaurant.member.controller.port.MemberService;
//import com.example.listaurant.member.infra.MailSenderImpl;
//import com.example.listaurant.member.infra.MemberEntity;
//import com.example.listaurant.member.service.dto.MemberDto;
//import com.example.listaurant.member.service.port.MemberRepository;
//import jakarta.mail.MessagingException;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@SpringBootTest
//@Transactional
//class MemberServiceImplTest {
//
//    @Autowired
//    private MemberRepository memberRepository;
//    @Autowired
//    private MailSenderImpl mailSenderImpl;
//
//    @Autowired
//    private MemberService memberService;
//    @Test
//    @DisplayName("이미 존재하는 사용자인지 확인합니다.")
//    void isDuplicationCheck() {
//        // given
//        MemberEntity mem = MemberEntity.builder()
//                .email("test@test.com")
//                .passwd("123")
//                .pno("123")
//                .role("USER")
//                .build();
//
//        memberRepository.save(mem);
//        // when
//        // then
//        assertThat(memberService.isDuplicationEmail("test@test.com")).isTrue();
//    }
//
//    @Test
//    @DisplayName("입력한 비밀번호로 변경합니다.")
//    void updatePassword() {
//        // given
//        MemberDto member = MemberDto.builder().email("test123")
//                .pno("123123")
//                .passwd("123123").build();
//        memberService.save(member);
//        MemberEntity result = memberService.findByEmail("test123").get();
//        // when
//        memberService.update(MemberDto.builder()
//                .memberId(result.getMemberId())
//                .passwd("0000")
//                .build());
//        result = memberService.findByEmail("test123").get();
//        // then
//        assertThat(result.getPasswd()).isEqualTo("0000");
//    }
//}