package com.example.listaurant.recommend.repository;

import com.example.listaurant.recommend.service.dto.RecommendDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RecommendEntity {
    Long member_id;
    Long txt_id;


    public static RecommendEntity from(RecommendDto recommendDto){
        return RecommendEntity.builder()
                .txt_id(recommendDto.getTxt_id())
                .member_id(recommendDto.getMember_id())
                .build();
    }
}
