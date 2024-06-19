package com.example.listaurant.member.service.dto;

import com.example.listaurant.member.controller.request.PwdUpdateRequest;
import com.example.listaurant.member.controller.request.SignUpRequest;
import com.example.listaurant.member.controller.request.UpdateRequest;
import lombok.*;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MemberDto {

    private Long memberId;
    private String email;
    private String passwd;
    private String pno;
    private String role;

    public static MemberDto from(UpdateRequest request){
        return MemberDto.builder()
                .memberId(request.getMemberId())
                .pno(request.getPno())
                .build();
    }

    public static MemberDto from(SignUpRequest request){
        return MemberDto.builder()
                .email(request.getEmail())
                .passwd(request.getPasswd())
                .pno(request.getPno())
                .build();
    }

    public static MemberDto from(PwdUpdateRequest request) {
        return MemberDto.builder()
                .memberId(request.getMemberId())
                .passwd(request.getPasswd())
                .build();
    }
}
