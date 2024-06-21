package com.example.listaurant.txt.controller.port;

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
    List<TxtDto> findByMemberId(Long memberId);

    double getAvgScope(String title, double lat, double lng);
//    List<TxtEntity> findByRecent();
//    List<TxtEntity> findByPopular();
//    void deleteTxt(Long id);

    void plusOneRecommend(Long txtId);

}
