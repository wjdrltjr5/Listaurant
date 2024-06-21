package com.example.listaurant.txt.infra;

import com.example.listaurant.txt.service.dto.TxtDto;
import com.example.listaurant.txt.service.port.TxtRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Repository
@Slf4j
public class TxtRepositoryImpl implements TxtRepository {

    private final TxtMapper txtMapper;

    @Override
    public void saveTxt(TxtEntity txtEntity) { txtMapper.saveTxt(txtEntity); }

    @Override
    public TxtEntity findMostRecentTxt(String title, double lat, double lng) {
        TxtEntity txtEntity = TxtEntity.builder()
                .placeName(title)
                .lat(lat)
                .lng(lng)
                .build();
        return txtMapper.findMostRecentTxt(txtEntity);
    }

    @Override
    public TxtEntity findMostPopularTxt(String title, double lat, double lng) {
        TxtEntity txtEntity = TxtEntity.builder()
                .placeName(title)
                .lat(lat)
                .lng(lng)
                .build();
        return txtMapper.findMostPopularTxt(txtEntity);
    }

    @Override
    public List<TxtEntity> findAllRecentTxt(String title, double lat, double lng) {
        TxtEntity txtEntity = TxtEntity.builder()
                .placeName(title)
                .lat(lat)
                .lng(lng)
                .build();
        return txtMapper.findAllRecentTxt(txtEntity);
    }

    @Override
    public double getAvgScope(String title, double lat, double lng) {
        TxtEntity txtEntity = TxtEntity.builder()
                .placeName(title)
                .lat(lat)
                .lng(lng)
                .build();
        if(null == txtMapper.getAvgScope(txtEntity)) {
            return 0;
        } else return txtMapper.getAvgScope(txtEntity);
    }

    @Override
    public void plusOneRecommend(Long txtId) {
        txtMapper.plusOneRecommend(txtId);
    }

    @Override
    public List<TxtDto> findByMemberId(Long memberId) {
        return txtMapper.findByMemberId(memberId).stream().map(TxtEntity::toDto).toList();
    }
//
//    @Override
//    public List<TxtEntity> findByRecent() { return txtMapper.findByRecent(); }
//
//    @Override
//    public List<TxtEntity> findByPopular() { return txtMapper.findByPopular();    }
//
//    @Override
//    public void updateTxt(TxtEntity txtEntity) { txtMapper.updateTxt(txtEntity);
//    }
//
//    @Override
//    public void deleteTxt(Long id) { txtMapper.deleteTxt(id); }
}
