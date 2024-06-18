package com.example.listaurant.member.controller.request;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@ToString
@Setter
public class SignUpRequest {

    @NotBlank(message = "필수입력 값 입니다.")
    private String email;

    @NotBlank(message = "필수입력 값 입니다.")
    @Pattern(
            regexp = "^(?=.*[!@#$%^&*(),.?\":{}|<>]).{8,}$",
            message = "비밀번호는 특수문자포함 8글자 이상이어야 합니다."
    )
    private String passwd;
    @NotBlank(message = "필수입력 값 입니다.")
    private String pno;

}
