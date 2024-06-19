package com.example.listaurant.txt.controller.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateTxtRequest {
    @NotBlank(message = "댓글을 입력해주세요.")
    String text;
    int scope;
}
