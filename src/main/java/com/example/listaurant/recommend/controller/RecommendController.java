package com.example.listaurant.recommend.controller;

import com.example.listaurant.member.service.MemberDetails;
import com.example.listaurant.recommend.controller.port.RecommendService;
import com.example.listaurant.recommend.controller.request.RecommendRequest;
import com.example.listaurant.recommend.service.dto.RecommendDto;
import com.example.listaurant.txt.controller.port.TxtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequiredArgsConstructor
@Slf4j
public class RecommendController {

    private final RecommendService recommendService;
    private final TxtService txtService;

    @PostMapping("/recommend")
    public String recommend(@ModelAttribute RecommendRequest request, @AuthenticationPrincipal MemberDetails memberDetails,
                            RedirectAttributes redirectAttributes) {
        request.setMemberId(memberDetails.getId());
        log.info("recommend request: {}", request);
        RecommendDto dto = request.toDto();

        redirectAttributes.addAttribute("title",request.getTitle());
        redirectAttributes.addAttribute("lat",request.getLat());
        redirectAttributes.addAttribute("lng",request.getLng());


        if(recommendService.isRecommendPresent(dto)){
            redirectAttributes.addAttribute("msg","이미 추천한 댓글입니다.");
            return "redirect:/board";
        }
        recommendService.save(dto);
        txtService.plusOneRecommend(dto.getTxtId());
        redirectAttributes.addAttribute("msg","추천하였습니다.");
        return "redirect:/board";
    }
}
