package com.example.listaurant.member.service.port;

import com.example.listaurant.member.infra.MemberEntity;
import com.example.listaurant.member.service.dto.MemberDto;

import java.lang.reflect.Member;
import java.util.Optional;

public interface MemberRepository {

    void save(MemberDto memberDto);

    Optional<MemberDto> findByEmail(String email);

    Optional<MemberDto> findById(Long id);

    void update(MemberDto memberDto);

    void delete(Long id);

    Optional<MemberDto> findByNickname(String nickname);
}
