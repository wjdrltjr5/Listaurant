package com.example.listaurant.txt.controller.response;

import com.example.listaurant.txt.infra.TxtEntity;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Getter
public class TxtResponse {
    private Long txtId;
    private String placeName;
    private LocalDateTime writtenDate;
    private int recommend;
    private int scope;
    private long memberId;
    private double lat;
    private double lng;
    private String text;

    public static TxtResponse from(TxtEntity txtEntity) {
        if(txtEntity == null){
            return new TxtResponse();
        }
        return TxtResponse.builder()
                .txtId(txtEntity.getTxtId())
                .placeName(txtEntity.getPlaceName())
                .writtenDate(txtEntity.getWrittenDate())
                .recommend(txtEntity.getRecommend())
                .scope(txtEntity.getScope())
                .memberId(txtEntity.getMemberId())
                .text(txtEntity.getText())
                .lat(txtEntity.getLat())
                .lng(txtEntity.getLng())
                .build();
    }
}
