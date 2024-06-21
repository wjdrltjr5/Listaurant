package com.example.listaurant.member.infra;

import com.example.listaurant.member.service.dto.MemberDto;
import com.example.listaurant.member.service.port.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepository {

    private final MemberMapper memberMapper;

    @Override
    public void save(MemberDto memberDto) {
        memberMapper.save(MemberEntity.from(memberDto));
    }

    @Override
    public Optional<MemberDto> findByEmail(String email) {
        return memberMapper.findByEmail(email).map(MemberEntity::toDto);
    }

    @Override
    public Optional<MemberDto> findById(Long id) {
        return memberMapper.findById(id).map(MemberEntity::toDto);
    }

    @Override
    public Optional<MemberDto> findByNickname(String nickname) {
        return memberMapper.findByNickname(nickname).map(MemberEntity::toDto);
    }

    @Override
    public void update(MemberDto memberDto) {
        memberMapper.update(MemberEntity.from(memberDto));
    }

    @Override
    public void delete(Long id) {
        memberMapper.delete(id);
    }

}
