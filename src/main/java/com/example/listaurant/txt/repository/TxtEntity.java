package com.example.listaurant.txt.repository;

import lombok.Builder;
import lombok.Getter;
import java.time.LocalDate;

@Getter
@Builder
public class TxtEntity {
    private Long txtId;
    private String placeName;
    private LocalDate writtenDate;
    private int recommend;
    private int scope;
    private long memberId;
    private String text;
}
