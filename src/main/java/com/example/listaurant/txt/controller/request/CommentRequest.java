package com.example.listaurant.txt.controller.request;

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
public class CommentRequest {
    @NotBlank(message = "댓글을 입력해주세요.")
    private String text;
    @NotBlank(message = "댓글을 입력해주세요.")
    private String text;
    @NotBlank(message = "댓글을 입력해주세요.")
    private String text;
    @NotBlank(message = "댓글을 입력해주세요.")
    private String text;

    @NotBlank(message = "댓글을 입력해주세요.")
    private String text;

    @NotBlank(message = "별점을 선택해주세요.")
    private int scope;
}
