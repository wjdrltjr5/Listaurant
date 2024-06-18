package com.example.listaurant.member.controller.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateRequest {

    Long memberId;

    @NotBlank(message = "필수 입력 값입니다.")
    String pno;

}
