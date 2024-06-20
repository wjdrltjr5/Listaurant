package com.example.listaurant.member.controller.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PasswordCheckResponse {
    private String txt;
    private boolean result;

    public PasswordCheckResponse(String txt, boolean result) {
        this.txt = txt;
        this.result = result;
    }
}
