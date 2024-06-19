package com.example.listaurant.member.infra;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
@Setter
public class MemberEntity {
    private Long memberId;
    private String email;
    private String passwd;
    private String pno;
    private String role;
}
