package com.example.listaurant.txt.controller.response;

import com.example.listaurant.txt.infra.TxtEntity;
import lombok.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Getter
public class TxtResponse {
    private static final Logger log = LoggerFactory.getLogger(TxtResponse.class);
    private Long txtId;
    private String placeName;
    private LocalDateTime writtenDate;
    private int recommend;
    private int scope;
    private long memberId;
    private double lat;
    private double lng;
    private String text;
    private String nickname;

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
                .nickname(txtEntity.getNickname())
                .build();
    }
}
