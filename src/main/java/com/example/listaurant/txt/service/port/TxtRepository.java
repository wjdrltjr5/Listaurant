package com.example.listaurant.txt.service.port;

import com.example.listaurant.txt.infra.TxtEntity;

import java.util.List;

public interface TxtRepository {

    void saveTxt(TxtEntity txtEntity);

    TxtEntity findMostRecentTxt();
    TxtEntity findMostPopularTxt();
//    List<TxtEntity> findByRecent();
//    List<TxtEntity> findByPopular();
//
//    void updateTxt(TxtEntity txtEntity);
//
//    void deleteTxt(Long id);
}
