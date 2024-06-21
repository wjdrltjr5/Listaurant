package com.example.listaurant.txt.infra;

import com.example.listaurant.txt.service.dto.TxtDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TxtMapper {
    void saveTxt(TxtEntity txtEntity);

    TxtEntity findMostRecentTxt(TxtEntity txtEntity);
    TxtEntity findMostPopularTxt(TxtEntity txtEntity);
    List<TxtEntity> findAllRecentTxt(TxtEntity txtEntity);
    Double getAvgScope(TxtEntity txtEntity);

    void plusOneRecommend(Long txtId);

    List<TxtEntity> findByMemberId(Long memberId);
//    List<TxtEntity> findByRecent();
//    List<TxtEntity> findByPopular();
//
//    void updateTxt(TxtEntity txtEntity);
//
//    void deleteTxt(Long id);

}
