package com.example.listaurant.recommend.service.port;

import com.example.listaurant.recommend.service.dto.RecommendDto;

public interface RecommendService {

    boolean isRecommendPresent(RecommendDto dto);

    void save(RecommendDto dto);

    void delete(RecommendDto dto);
}
