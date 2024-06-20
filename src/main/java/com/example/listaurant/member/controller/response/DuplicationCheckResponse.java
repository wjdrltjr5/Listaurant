package com.example.listaurant.member.controller.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DuplicationCheckResponse {
    private String txt;
    private boolean result;

    public DuplicationCheckResponse(String txt, boolean result) {
        this.txt = txt;
        this.result = result;
    }
}
