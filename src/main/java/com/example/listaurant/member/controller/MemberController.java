package com.example.listaurant.member.controller;

import com.example.listaurant.member.controller.port.MemberService;
import com.example.listaurant.member.controller.request.SignUpRequest;
import com.example.listaurant.member.service.dto.MemberDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@Slf4j
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
        memberService.save(MemberDto.from(signUpRequest));
        return "login";
    }

    @GetMapping("/temp-password")
    public String tempPasswordPage() {
        return "temp-password";
    }

    @PostMapping("/temp-password")
    public String sendTempPassword(@RequestParam("email")String email){
        log.info("email = {}",email);
        memberService.sendTempPassword(MemberDto.builder().email(email).build());
        return "redirect:/login";
    }
}