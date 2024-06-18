package com.example.listaurant.member.repository;

import com.example.listaurant.member.service.port.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest
@Transactional
class MemberRepositoryImplTest {
    @Autowired
    private MemberRepository repository;

    @Test
    @DisplayName("MemberEntity 타입을 받으면 데이터 베이스에 값을 저장한다.")
    void save() {
        // given
        MemberEntity memberEntity = MemberEntity.builder()
                .email("wjdrltjr5@naver.com")
                .passwd("123123")
                .pno("123123")
                .build();
        // when
        repository.save(memberEntity);
        MemberEntity result = repository.findByEmail("wjdrltjr5@naver.com").get();
        // then
        assertThat(result.getPno()).isEqualTo("123123");
    }
}