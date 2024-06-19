package com.example.listaurant.txt.controller.port;

import com.example.listaurant.txt.infra.TxtEntity;
import com.example.listaurant.txt.service.dto.TxtDto;

import java.util.List;

public interface TxtService {
    void saveTxt(TxtDto txtDto);
//    void updateTxt(TxtDto txtDto);
//
    TxtEntity findMostRecentTxt();
    TxtEntity findMostPopularTxt();
//    List<TxtEntity> findByRecent();
//    List<TxtEntity> findByPopular();
//    void deleteTxt(Long id);
}
