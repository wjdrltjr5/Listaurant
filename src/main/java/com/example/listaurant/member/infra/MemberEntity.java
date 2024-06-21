package com.example.listaurant.member.infra;

import com.example.listaurant.member.service.dto.MemberDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
@Setter
public class MemberEntity {
    private Long memberId;
    private String email;
    private String nickname;
    private String passwd;
    private String pno;
    private String role;

    public static MemberEntity from(MemberDto dto){
        return MemberEntity.builder()
                .memberId(dto.getMemberId())
                .email(dto.getEmail())
                .nickname(dto.getNickname())
                .passwd(dto.getPasswd())
                .pno(dto.getPno())
                .role(dto.getRole())
                .build();
    }

    public MemberDto toDto(){
        return MemberDto.builder()
                .memberId(this.memberId)
                .email(this.email)
                .nickname(this.nickname)
                .passwd(this.passwd)
                .pno(this.pno)
                .role(this.role)
                .build();
    }
}
