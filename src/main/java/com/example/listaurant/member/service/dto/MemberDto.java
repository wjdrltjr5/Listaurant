package com.example.listaurant.member.service.dto;

import com.example.listaurant.member.controller.request.PwdUpdateRequest;
import com.example.listaurant.member.controller.request.SignUpRequest;
import com.example.listaurant.member.controller.request.UpdateRequest;
import com.example.listaurant.member.domain.MemberStatus;
import lombok.*;

import java.lang.reflect.Member;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
public class MemberDto {

    private Long memberId;
    private String email;
    private String nickname;
    private String passwd;
    private String pno;
    private String role;
    private String status;
    private String certificationCode;

    public static MemberDto from(UpdateRequest request){
        return MemberDto.builder()
                .memberId(request.getMemberId())
                .nickname(request.getNickname())
                .pno(request.getPno())
                .build();
    }

    public static MemberDto from(SignUpRequest request){
        return MemberDto.builder()
                .email(request.getEmail())
                .nickname(request.getNickname())
                .passwd(request.getPasswd())
                .pno(request.getPno())
                .status(MemberStatus.PENDING.toString())
                .certificationCode(request.getCertificationCode())
                .build();
    }

    public static MemberDto from(PwdUpdateRequest request) {
        return MemberDto.builder()
                .memberId(request.getMemberId())
                .passwd(request.getPasswd())
                .build();
    }

    public void activeStatus(){
        this.status = MemberStatus.ACTIVE.toString();
    }
}
