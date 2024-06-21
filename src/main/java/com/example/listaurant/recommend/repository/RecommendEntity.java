package com.example.listaurant.recommend.repository;

import com.example.listaurant.recommend.service.dto.RecommendDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RecommendEntity {
    Long memberId;
    Long txtId;


    public static RecommendEntity from(RecommendDto recommendDto){
        return RecommendEntity.builder()
                .txtId(recommendDto.getTxtId())
                .memberId(recommendDto.getMemberId())
                .build();
    }
}
