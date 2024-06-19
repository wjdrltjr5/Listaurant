package com.example.listaurant.txt.controller.request;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@ToString
@Setter
public class CommentRequest {

    private Long txtId;
    private String placeName;
    private LocalDate writtenDate;
    private int recommend;
    private String text;
    private int scope;
    private double lat;
    private double lng;
    private Long memberId;
}
