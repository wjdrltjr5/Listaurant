package com.example.listaurant.member.service.port;

import com.example.listaurant.member.repository.MemberEntity;

import java.util.Optional;

public interface MemberRepository {

    void save(MemberEntity memberEntity);

    Optional<MemberEntity> findByEmail(String email);
}
