package com.example.listaurant.member.controller;

import com.example.listaurant.member.controller.port.MemberService;
import com.example.listaurant.member.controller.request.SignUpRequest;
import com.example.listaurant.member.controller.request.UpdateRequest;
import com.example.listaurant.member.controller.response.MemberResponse;
import com.example.listaurant.member.repository.MemberEntity;
import com.example.listaurant.member.service.MemberDetails;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/login")
    public String loginPage(@RequestParam(value = "error-message", required = false) String message, Model model) {
        model.addAttribute("message", message);
        return "login";
    }

    @GetMapping("/sign-up")
    public String signUpPage(Model model) {
        model.addAttribute("signUpRequest", new SignUpRequest());
        return "sign-up";
    }

    @PostMapping("/sign-up")
    public String signUp(@Valid @ModelAttribute SignUpRequest signUpRequest, BindingResult br) {

        if (memberService.isDuplicationEmail(signUpRequest.getEmail())) {
            br.reject("globalError", "이미 존재하는 사용자입니다.");
            return "sign-up";
        }
        if (br.hasErrors()) {
            return "sign-up";
        }
        memberService.save(signUpRequest);
        return "login";
    }

    @GetMapping({"/mypage","/mypage/"})
    public String mypage(@AuthenticationPrincipal MemberDetails memberDetails, Model model) {
        log.info("memberDetatils = {}", memberDetails);
        MemberEntity memberEntity = memberService.findById(memberDetails.getId()).get();
        MemberResponse response = MemberResponse.from(memberEntity);
        log.info("memberEntity = {}", memberDetails.getUsername());
        log.info("response = {}", response.toString());
        model.addAttribute("member", response);
        return "mypage";
    }

    @GetMapping("/mypage/{memberId}")
    public String updateForm(@PathVariable("memberId") Long memberId, Model model) {
        MemberEntity memberEntity = memberService.findById(memberId).orElseThrow(() -> new UsernameNotFoundException("회원 정보가 없습니다."));
        MemberResponse response = MemberResponse.from(memberEntity);
        model.addAttribute("member", response);
        return "mypage-edit";
    }

    @PostMapping("/mypage/update")
    public String update(@AuthenticationPrincipal MemberDetails memberDetails, @Valid @ModelAttribute UpdateRequest updateRequest, BindingResult br) {
        if (br.hasErrors()) {
            return "mypage-edit";
        }
        updateRequest.setMemberId(memberDetails.getId());
        memberService.update(updateRequest);
        return "redirect:/mypage";
    }

    @PostMapping("/mypage/delete")
    public String delete(@AuthenticationPrincipal MemberDetails memberDetails){
        memberService.delete(memberDetails.getId());
        return "redirect:/";
    }
}