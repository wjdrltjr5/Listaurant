package com.example.listaurant.recommend.service;

import com.example.listaurant.recommend.controller.port.RecommendService;
import com.example.listaurant.recommend.service.dto.RecommendDto;
import com.example.listaurant.recommend.service.port.RecommendRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecommendServiceImpl implements RecommendService {

    private final RecommendRepository recommendRepository;

    @Override
    public boolean isRecommendPresent(RecommendDto recommendDto) {
        return recommendRepository.findByTxtIdAndMemberId(recommendDto).isPresent();
    }

    @Override
    public void save(RecommendDto recommendDto) {
        recommendRepository.save(recommendDto);
    }

    @Override
    public void delete(RecommendDto recommendDto) {
        recommendRepository.delete(recommendDto);
    }
}
