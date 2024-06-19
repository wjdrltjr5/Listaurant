package com.example.listaurant;


import com.example.listaurant.member.service.MemberDetails;
import com.example.listaurant.txt.controller.port.TxtService;
import com.example.listaurant.txt.controller.request.CommentRequest;
import com.example.listaurant.txt.controller.response.TxtResponse;
import com.example.listaurant.txt.infra.TxtEntity;
import com.example.listaurant.txt.service.dto.TxtDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/board")
public class IndexController {

    private final TxtService txtService;

    @GetMapping
    public String board(@RequestParam("title") String title, @RequestParam("lat") float lat,
                        @RequestParam("lng") float lng, Model model){
        model.addAttribute("title",title);
        model.addAttribute("lat",lat);
        model.addAttribute("lng",lng);
        log.info("get /board {},{},{}",title, lat, lng);

        TxtEntity txtPopularEntity = txtService.findMostPopularTxt();
        TxtEntity txtRecentEntity = txtService.findMostRecentTxt();
        TxtResponse popResponse = TxtResponse.from(txtPopularEntity);
        TxtResponse recentResponse = TxtResponse.from(txtRecentEntity);
        model.addAttribute("pop", popResponse);
        model.addAttribute("recent", recentResponse);

        return "board";
    }

    @PostMapping
    public String boardComment(@RequestParam("title") String title, @RequestParam("lat") String lat,
                               @RequestParam("lng") String lng, Model model){
        log.info("post /board {},{},{}" ,title, lat, lng);
        model.addAttribute(title, title);
        model.addAttribute(lat, lat);
        model.addAttribute(lng, lng);
        return "board";
    }

    @PostMapping("/comment")
    public String textSave(@Valid @ModelAttribute CommentRequest commentRequest,
                           @AuthenticationPrincipal MemberDetails memberDetails,
                           RedirectAttributes redirectAttributes) {
        log.info("commnetRequest ={}", commentRequest);
        commentRequest.setMemberId(memberDetails.getId());
        commentRequest.setWrittenDate(LocalDate.now());
        txtService.saveTxt(TxtDto.from(commentRequest));

        // RedirectAttributes에 필요한 파라미터 추가
        redirectAttributes.addAttribute("title", commentRequest.getPlaceName());
        redirectAttributes.addAttribute("lat", commentRequest.getLat());
        redirectAttributes.addAttribute("lng", commentRequest.getLng());

        // 리다이렉트 URL 생성 및 반환
        return "redirect:/board?title={title}&lat={lat}&lng={lng}";
    }
}
