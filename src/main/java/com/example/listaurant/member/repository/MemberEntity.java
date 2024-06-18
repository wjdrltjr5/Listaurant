package com.example.listaurant.member.repository;

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
}
