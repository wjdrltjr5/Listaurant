package com.example.listaurant.recommend.controller.request;

import lombok.Data;

@Data
public class RecommendCheckRequest {
    private Long memberId;
    private Long txtId;
}
