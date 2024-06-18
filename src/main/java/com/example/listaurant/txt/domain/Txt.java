package com.example.listaurant.txt.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Txt {
    public Long txtId;
    public String placeName;
    public LocalDate writtenDate;
    public int recommend;
    public float scope;
    public String text;
}
