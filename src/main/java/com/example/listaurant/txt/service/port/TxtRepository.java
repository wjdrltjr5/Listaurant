package com.example.listaurant.txt.service.port;

import com.example.listaurant.txt.infra.TxtEntity;

import java.util.List;

public interface TxtRepository {

    void saveTxt(TxtEntity txtEntity);

    TxtEntity findMostRecentTxt(String title, double lat, double lng);
    TxtEntity findMostPopularTxt(String title, double lat, double lng);
    List<TxtEntity> findAllRecentTxt(String title, double lat, double lng);

    double getAvgScope(String title, double lat, double lng);

    void plusOneRecommend(Long txtId);
//    List<TxtEntity> findByRecent();
//    List<TxtEntity> findByPopular();
//
//    void updateTxt(TxtEntity txtEntity);
//
//    void deleteTxt(Long id);
}
