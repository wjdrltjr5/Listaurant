package com.example.listaurant.member.repository;

import com.example.listaurant.member.domain.Member;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberEntity {

    private Long memberId;
    private String email;
    private String passwd;
    private String pno;
    private String role;

    public static MemberEntity from(Member member){
        return MemberEntity.builder()
                .email(member.getEmail())
                .pno(member.getPno())
                .passwd(member.getPasswd())
                .build();
    }
}
