package com.example.listaurant;

import com.example.listaurant.member.service.MemberDetails;
import com.example.listaurant.member.controller.port.MemberService;
import com.example.listaurant.member.service.dto.MemberDto;
import com.example.listaurant.txt.controller.port.TxtService;
import com.example.listaurant.txt.controller.request.CommentRequest;
import com.example.listaurant.txt.controller.request.UpdateTxtRequest;
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
import java.time.LocalDateTime;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/board")
public class IndexController {

    private final TxtService txtService;
    private final MemberService memberService;


    @GetMapping
    public String board(@RequestParam("title") String title, @RequestParam("lat") float lat,
                        @RequestParam("lng") float lng, Model model, @AuthenticationPrincipal MemberDetails memberDetails) {
        TxtEntity txtPopularEntity = txtService.findMostPopularTxt(title, changeNum(lat), changeNum(lng));
        TxtEntity txtRecentEntity = txtService.findMostRecentTxt(title, changeNum(lat), changeNum(lng));
        List<TxtEntity> listRecentResponse = txtService.findAllRecentTxt(title, changeNum(lat), changeNum(lng));
        List<TxtEntity> listPopularResponse = txtService.findAllPopularTxt(title, changeNum(lat), changeNum(lng));

        TxtResponse popResponse = TxtResponse.from(txtPopularEntity);
        TxtResponse recentResponse = TxtResponse.from(txtRecentEntity);
        double avgScope = txtService.getAvgScope(title, changeNum(lat), changeNum(lng));

        // 모델에 필요한 데이터 추가
        if (memberDetails != null){
            MemberDto memberDto = memberService.findById(memberDetails.getId()).get();
            model.addAttribute("memberId", memberDto.getMemberId());
        }
        model.addAttribute("title", title);
        model.addAttribute("lat", lat);
        model.addAttribute("lng", lng);
        model.addAttribute("pop", popResponse);
        model.addAttribute("recent", recentResponse);
        model.addAttribute("comments", listRecentResponse);
        model.addAttribute("populars", listPopularResponse);
        model.addAttribute("avgScope", avgScope);
        model.addAttribute("countComments", listRecentResponse.size());
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

    @GetMapping("/comment")
    public String textSave(@Valid @ModelAttribute CommentRequest commentRequest,
                           @AuthenticationPrincipal MemberDetails memberDetails,
                           RedirectAttributes redirectAttributes) {
        double tmpLat = commentRequest.getLat();
        double tmpLng = commentRequest.getLng();

        MemberDto memberDto = memberService.findById(memberDetails.getId()).get();
        commentRequest.setMemberId(memberDto.getMemberId());
        commentRequest.setNickname(memberDto.getNickname());
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

    @PostMapping("/delete")
    public String textDelete(@RequestParam("title") String title, @RequestParam("lat") float lat,
                             @RequestParam("lng") float lng, @RequestParam("commentId") long txtId,
                             RedirectAttributes redirectAttributes) {

        txtService.deleteTxt(txtId);

        redirectAttributes.addAttribute("title", title);
        redirectAttributes.addAttribute("lat", lat);
        redirectAttributes.addAttribute("lng", lng);

        return "redirect:/board?title={title}&lat={lat}&lng={lng}";
    }

    @PostMapping("/update")
    public String textUpdate(@ModelAttribute UpdateTxtRequest updateTxtRequest,
                             @RequestParam("title") String title, @RequestParam("lat") float lat,
                             @RequestParam("lng") float lng, @RequestParam("commentId") long txtId,
                             @RequestParam("text") String text, RedirectAttributes redirectAttributes) {
        log.info("adfsafas");

        updateTxtRequest.setTxtId(txtId);
        updateTxtRequest.setText(text);
        redirectAttributes.addAttribute("title", title);
        redirectAttributes.addAttribute("lat", lat);
        redirectAttributes.addAttribute("lng", lng);

        txtService.updateTxt(TxtDto.from(updateTxtRequest));
        // 리다이렉트 URL 생성 및 반환
        return "redirect:/board?title={title}&lat={lat}&lng={lng}";
    }

    public double changeNum(double num){
        BigDecimal bd = new BigDecimal(num).setScale(4, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
