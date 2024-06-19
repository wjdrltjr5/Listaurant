package com.example.listaurant.member.infra;

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

    @Override
    public Optional<MemberEntity> findById(Long id) {
        return memberMapper.findById(id);
    }

    @Override
    public void update(MemberEntity memberEntity) {
        memberMapper.update(memberEntity);
    }

    @Override
    public void delete(Long id) {
        memberMapper.delete(id);
    }
}
