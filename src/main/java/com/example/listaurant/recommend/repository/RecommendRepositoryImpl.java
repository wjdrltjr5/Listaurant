package com.example.listaurant.recommend.repository;

import com.example.listaurant.recommend.service.dto.RecommendDto;
import com.example.listaurant.recommend.service.port.RecommendRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class RecommendRepositoryImpl implements RecommendRepository {

    private final RecommendMapper recommendMapper;

    @Override
    public void save(RecommendDto recommendDto){
        recommendMapper.save(RecommendEntity.from(recommendDto));
    }

    @Override
    public Optional<RecommendEntity> findByTxtIdAndMemberId(RecommendDto recommendDto){
        return recommendMapper.findByTxtIdAndMemberId(RecommendEntity.from(recommendDto));
    }

    @Override
    public void delete(RecommendDto recommendDto){
        recommendMapper.delete(RecommendEntity.from(recommendDto));
    }

}
