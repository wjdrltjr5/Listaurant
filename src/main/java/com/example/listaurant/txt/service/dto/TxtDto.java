package com.example.listaurant.txt.service.dto;

import com.example.listaurant.txt.controller.request.CommentRequest;
import com.example.listaurant.txt.controller.request.UpdateTxtRequest;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TxtDto {

    public Long txtId;
    public String placeName;
    public LocalDateTime writtenDate;
    public int recommend;
    public int scope;
    public String text;
    private double lat;
    private double lng;
    public Long memberId;

    public static TxtDto from(CommentRequest request){
        return TxtDto.builder()
                .txtId(request.getTxtId())
                .placeName(request.getPlaceName())
                .writtenDate(request.getWrittenDate())
                .recommend(request.getRecommend())
                .scope(request.getScope())
                .text(request.getText())
                .memberId(request.getMemberId())
                .lat(request.getLat())
                .lng(request.getLng())
                .build();
    }
}
