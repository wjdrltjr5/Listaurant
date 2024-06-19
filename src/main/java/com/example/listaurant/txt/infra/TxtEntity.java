package com.example.listaurant.txt.infra;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Builder
@Setter
public class TxtEntity {

    public Long txtId;
    public String placeName;
    public LocalDate writtenDate;
    public int recommend;
    public int scope;
    public String text;
    private double lat;
    private double lng;
    public Long memberId;
}
