package com.example.listaurant.member.service.port;

import com.example.listaurant.member.infra.MemberEntity;

import java.util.Optional;

public interface MemberRepository {

    void save(MemberEntity memberEntity);

    Optional<MemberEntity> findByEmail(String email);

    Optional<MemberEntity> findById(Long id);

    void update(MemberEntity memberEntity);

    void delete(Long id);
}
