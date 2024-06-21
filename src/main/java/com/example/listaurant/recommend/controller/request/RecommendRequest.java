package com.example.listaurant.recommend.controller.request;

import com.example.listaurant.recommend.service.dto.RecommendDto;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class RecommendRequest {
    private Long memberId;
    private Long txtId;
    private String title;
    private String lat;
    private String lng;


    public RecommendDto toDto(){
        return RecommendDto.builder()
                .memberId(this.getMemberId())
                .txtId(this.getTxtId())
                .build();
    }
}
