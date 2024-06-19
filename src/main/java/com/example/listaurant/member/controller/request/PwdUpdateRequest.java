package com.example.listaurant.member.controller.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class PwdUpdateRequest {

    private Long memberId;

    @NotBlank(message = "필수입력 값 입니다.")
    @Pattern(
            regexp = "^(?=.*[!@#$%^&*(),.?\":{}|<>]).{8,}$",
            message = "비밀번호는 특수문자포함 8글자 이상이어야 합니다."
    )
    private String passwd;
}
