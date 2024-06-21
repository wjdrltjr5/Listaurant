package com.example.listaurant.member.controller.response;

import com.example.listaurant.member.infra.MemberEntity;
import com.example.listaurant.member.service.dto.MemberDto;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Getter
public class MemberResponse {
    private Long memberId;
    private String email;
    private String nickname;
    private String pno;

    public static MemberResponse from(MemberDto memberDto){
        return MemberResponse.builder()
                .memberId(memberDto.getMemberId())
                .email(memberDto.getEmail())
                .nickname(memberDto.getNickname())
                .pno(memberDto.getPno())
                .build();
    }
}
