package com.example.listaurant.txt.infra;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TxtMapper {
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
