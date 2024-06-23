package com.example.listaurant.member.controller.port;

import com.example.listaurant.member.infra.MemberEntity;
import com.example.listaurant.member.service.dto.MemberDto;

import java.util.Optional;

public interface MemberService {

    void save(MemberDto memberDto);

    boolean isDuplicationEmail(String email);

    boolean isDuplicationNickname(String nickname);

    Optional<MemberDto> findByEmail(String email);

    Optional<MemberDto> findById(Long id);

    void update(MemberDto memberDto);

    void delete(Long id);

    void sendTempPassword(MemberDto dto);

    void activeStatus(String email);
}
