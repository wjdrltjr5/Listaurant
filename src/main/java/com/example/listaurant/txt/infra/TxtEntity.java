package com.example.listaurant.txt.infra;

import com.example.listaurant.txt.service.dto.TxtDto;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Setter
public class TxtEntity {

    public Long txtId;
    public String placeName;
    public LocalDateTime writtenDate;
    public int recommend;
    public int scope;
    public String text;
    private double lat;
    private double lng;
    public Long memberId;
    public String nickname;

    public TxtDto toDto(){
        return TxtDto.builder()
                .txtId(this.txtId)
                .placeName(this.placeName)
                .writtenDate(this.writtenDate)
                .recommend(this.recommend)
                .scope(this.scope)
                .text(this.text)
                .lat(this.lat)
                .lng(this.lng)
                .memberId(this.memberId)
                .nickname(this.nickname)
                .build();
    }
}
