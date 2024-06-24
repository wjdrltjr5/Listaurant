package com.example.listaurant.txt.service;

import com.example.listaurant.txt.service.port.TxtService;
import com.example.listaurant.txt.infra.TxtEntity;
import com.example.listaurant.txt.service.dto.TxtDto;
import com.example.listaurant.txt.service.port.TxtRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TxtServiceImpl implements TxtService {
    private final TxtRepository txtRepository;

    @Override
    public void saveTxt(TxtDto txtDto) {
        TxtEntity txt = TxtEntity.builder()
                .txtId(txtDto.getTxtId())
                .placeName(txtDto.getPlaceName())
                .writtenDate(txtDto.getWrittenDate())
                .recommend(txtDto.getRecommend())
                .scope(txtDto.getScope())
                .text(txtDto.getText())
                .memberId(txtDto.getMemberId())
                .lat(txtDto.getLat())
                .lng(txtDto.getLng())
                .nickname(txtDto.getNickname())
                .build();
        txtRepository.saveTxt(txt);
    }

    @Override
    public TxtEntity findMostRecentTxt(String title,double lat, double lng) {
        return txtRepository.findMostRecentTxt(title, lat, lng);
    }

    @Override
    public TxtEntity findMostPopularTxt(String title, double lat, double lng) {
        return txtRepository.findMostPopularTxt(title, lat, lng);
    }

    @Override
    public List<TxtEntity> findAllRecentTxt(String title, double lat, double lng) {
        return txtRepository.findAllRecentTxt(title, lat, lng);
    }

    @Override
    public List<TxtEntity> findAllPopularTxt(String title, double lat, double lng) {
        return txtRepository.findAllPopularTxt(title, lat, lng);
    }

    @Override
    public List<TxtDto> findByMemberId(Long memberId) {
        return txtRepository.findByMemberId(memberId);
    }

    @Override
    public double getAvgScope(String title, double lat, double lng) {
        return txtRepository.getAvgScope(title, lat, lng);
    }

    @Override
    public void plusOneRecommend(Long txtId) {
        txtRepository.plusOneRecommend(txtId);
    }

    @Override
    public void deleteTxt(Long txtId) { txtRepository.deleteTxt(txtId); }

    @Override
    public void updateTxt(TxtDto txtDto) {
        TxtEntity txt = TxtEntity.builder()
                .txtId(txtDto.getTxtId())
                .placeName(txtDto.getPlaceName())
                .writtenDate(txtDto.getWrittenDate())
                .recommend(txtDto.getRecommend())
                .scope(txtDto.getScope())
                .text(txtDto.getText())
                .memberId(txtDto.getMemberId())
                .lat(txtDto.getLat())
                .lng(txtDto.getLng())
                .nickname(txtDto.getNickname())
                .build();

        txtRepository.updateTxt(txt); }
}
