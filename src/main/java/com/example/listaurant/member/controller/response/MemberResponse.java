package com.example.listaurant.member.controller.response;

import com.example.listaurant.member.infra.MemberEntity;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Getter
public class MemberResponse {
    private Long memberId;
    private String email;
    private String pno;

    public static MemberResponse from(MemberEntity memberEntity){
        return MemberResponse.builder()
                .memberId(memberEntity.getMemberId())
                .email(memberEntity.getEmail())
                .pno(memberEntity.getPno())
                .build();
    }
}
