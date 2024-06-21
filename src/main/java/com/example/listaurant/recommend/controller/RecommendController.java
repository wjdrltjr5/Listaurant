package com.example.listaurant.recommend.controller;

import com.example.listaurant.recommend.controller.port.RecommendService;
import com.example.listaurant.recommend.controller.request.RecommendCheckRequest;
import com.example.listaurant.recommend.controller.response.RecommendCheckResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class RecommendController {

    private final RecommendService recommendService;

    @GetMapping("/recommend-check")
    @ResponseBody
    public RecommendCheckResponse recommendCheck(RecommendCheckRequest recommendCheckRequest) {
        recommendService.isRecommendPresent(re)
    }


//    @PostMapping("/recommend")
}
