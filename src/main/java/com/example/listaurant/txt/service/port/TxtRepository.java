package com.example.listaurant.txt.service.port;

import com.example.listaurant.txt.infra.TxtEntity;
import com.example.listaurant.txt.service.dto.TxtDto;

import java.util.List;

public interface TxtRepository {

    void saveTxt(TxtEntity txtEntity);

    TxtEntity findMostRecentTxt(String title, double lat, double lng);
    TxtEntity findMostPopularTxt(String title, double lat, double lng);
    List<TxtEntity> findAllRecentTxt(String title, double lat, double lng);
    List<TxtEntity> findAllPopularTxt(String title, double lat, double lng);
    double getAvgScope(String title, double lat, double lng);

    void plusOneRecommend(Long txtId);

    List<TxtDto> findByMemberId(Long memberId);

    void deleteTxt(Long txtId);

    void updateTxt(TxtEntity txtEntity);
}
