package com.example.listaurant.member.controller.request;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import lombok.*;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@ToString
@Setter
public class SignUpRequest {

    private String email;
    private String passwd;
    private String pno;

}
