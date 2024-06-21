package com.example.listaurant.recommend.service.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class RecommendDto {

    private Long txtId;
    private Long memberId;
}
