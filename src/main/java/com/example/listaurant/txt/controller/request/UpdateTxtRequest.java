package com.example.listaurant.txt.controller.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@ToString
@Setter
public class UpdateTxtRequest {
    private long txtId;
    private String text;
    private int scope;
}
