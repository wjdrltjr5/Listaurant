package com.example.listaurant.member.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    private Long memberId;
    private String email;
    private String passwd;
    private String pno;
    private String role;

}
