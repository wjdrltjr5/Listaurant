package com.example.listaurant.txt.service.port;

import com.example.listaurant.txt.infra.TxtEntity;
import com.example.listaurant.txt.service.dto.TxtDto;

import java.util.List;

public interface TxtService {
    void saveTxt(TxtDto txtDto);
//    void updateTxt(TxtDto txtDto);
//
    TxtEntity findMostRecentTxt(String title, double lat, double lng);
    TxtEntity findMostPopularTxt(String title, double lat, double lng);
    List<TxtEntity> findAllRecentTxt(String title, double lat, double lng);
    List<TxtEntity> findAllPopularTxt(String title, double lat, double lng);
    List<TxtDto> findByMemberId(Long memberId);

    double getAvgScope(String title, double lat, double lng);

    void plusOneRecommend(Long txtId);

    void deleteTxt(Long txtId);
    void updateTxt(TxtDto txtDto);
}
