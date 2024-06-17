package com.example.listaurant.member.repository;

import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface MemberMapper {

    void save(MemberEntity memberEntity);

    Optional<MemberEntity> findByEmail(String email);

}
