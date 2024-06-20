package com.example.listaurant;


import com.example.listaurant.member.infra.MemberEntity;
import com.example.listaurant.member.service.MemberDetails;
import com.example.listaurant.member.controller.port.MemberService;
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

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/board")
public class IndexController {

    private final TxtService txtService;
    private final MemberService memberService;


    @GetMapping
    public String board(@RequestParam("title") String title, @RequestParam("lat") float lat,
                        @RequestParam("lng") float lng, Model model){
        TxtEntity txtPopularEntity = txtService.findMostPopularTxt(title, changeNum(lat), changeNum(lng));
        TxtEntity txtRecentEntity = txtService.findMostRecentTxt(title, changeNum(lat), changeNum(lng));
        List<TxtEntity> listRecentResponse = txtService.findAllRecentTxt(title, changeNum(lat), changeNum(lng));

        TxtResponse popResponse = TxtResponse.from(txtPopularEntity);
        TxtResponse recentResponse = TxtResponse.from(txtRecentEntity);

        model.addAttribute("title",title);
        model.addAttribute("lat",lat);
        model.addAttribute("lng",lng);
        model.addAttribute("pop", popResponse);
        model.addAttribute("recent", recentResponse);
        model.addAttribute("comments", listRecentResponse);

        return "board";
    }

    @PostMapping
    public String boardComment(@RequestParam("title") String title, @RequestParam("lat") String lat,
                               @RequestParam("lng") String lng, Model model){
        model.addAttribute(title, title);
        model.addAttribute(lat, lat);
        model.addAttribute(lng, lng);
        return "board";
    }

    @PostMapping("/comment")
    public String textSave(@Valid @ModelAttribute CommentRequest commentRequest,
                           @AuthenticationPrincipal MemberDetails memberDetails,
                           RedirectAttributes redirectAttributes) {
        double tmpLat = commentRequest.getLat();
        double tmpLng = commentRequest.getLng();
        MemberEntity memberEntity = memberService.findById(memberDetails.getId()).get();
        commentRequest.setMemberId(memberEntity.getMemberId());
        commentRequest.setNickname(memberEntity.getNickname());
        commentRequest.setWrittenDate(LocalDateTime.now());
        commentRequest.setLat(changeNum(commentRequest.getLat()));
        commentRequest.setLng(changeNum(commentRequest.getLng()));

        // RedirectAttributes에 필요한 파라미터 추가
        redirectAttributes.addAttribute("title", commentRequest.getPlaceName());
        redirectAttributes.addAttribute("lat", tmpLat);
        redirectAttributes.addAttribute("lng", tmpLng);

        txtService.saveTxt(TxtDto.from(commentRequest));
        // 리다이렉트 URL 생성 및 반환
        return "redirect:/board?title={title}&lat={lat}&lng={lng}";
    }

    public double changeNum(double num){
        BigDecimal bd = new BigDecimal(num).setScale(5, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
