package com.example.listaurant.txt.infra;

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
}
