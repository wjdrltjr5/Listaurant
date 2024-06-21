package com.example.listaurant.recommend.service.port;

import com.example.listaurant.recommend.repository.RecommendEntity;
import com.example.listaurant.recommend.service.dto.RecommendDto;

import java.util.Optional;

public interface RecommendRepository {

    void save(RecommendDto recommendDto);

    Optional<RecommendEntity> findByTxtIdAndMemberId(RecommendDto recommendDto);

    void delete(RecommendDto recommendDto);
}
