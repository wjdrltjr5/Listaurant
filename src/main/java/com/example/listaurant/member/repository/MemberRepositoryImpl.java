package com.example.listaurant.member.repository;

import com.example.listaurant.member.service.port.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepository {

    private final MemberMapper memberMapper;

    @Override
    public void save(MemberEntity memberEntity) {
        memberMapper.save(memberEntity);
    }

    @Override
    public Optional<MemberEntity> findByEmail(String email) {
        return memberMapper.findByEmail(email);
    }
}