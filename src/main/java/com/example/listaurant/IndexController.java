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

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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

        TxtEntity txtPopularEntity = txtService.findMostPopularTxt(title, changeAdr(lat), changeAdr(lng));
        TxtEntity txtRecentEntity = txtService.findMostRecentTxt(title, changeAdr(lat), changeAdr(lng));

        TxtResponse popResponse = TxtResponse.from(txtPopularEntity);
        TxtResponse recentResponse = TxtResponse.from(txtRecentEntity);
        List<TxtEntity> listRecentResponse = txtService.findAllRecentTxt(title, changeAdr(lat), changeAdr(lng));
        model.addAttribute("pop", popResponse);
        model.addAttribute("recent", recentResponse);
        model.addAttribute("comments", listRecentResponse);
        log.info("txtPopularEntity = {}",listRecentResponse);
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
        commentRequest.setWrittenDate(LocalDateTime.now());
        txtService.saveTxt(TxtDto.from(commentRequest));

        // RedirectAttributes에 필요한 파라미터 추가
        redirectAttributes.addAttribute("title", commentRequest.getPlaceName());
        redirectAttributes.addAttribute("lat", commentRequest.getLat());
        redirectAttributes.addAttribute("lng", commentRequest.getLng());

        // 리다이렉트 URL 생성 및 반환
        return "redirect:/board?title={title}&lat={lat}&lng={lng}";
    }

    public double changeAdr(double adr) {
        // BigDecimal을 사용하여 숫자를 자르기
        BigDecimal bd = new BigDecimal(adr);

        // 정수부의 길이를 계산
        int integerPartLength = bd.toBigInteger().toString().length();

        // 정수부와 소수점 하나를 제외한 나머지 길이를 소수부로 사용
        int decimalPlaces = 9 - integerPartLength - 1;

        // 소수부의 자릿수 만큼 잘라내기
        bd = bd.setScale(decimalPlaces, RoundingMode.DOWN);

        return bd.doubleValue();
    }
}
