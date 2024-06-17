package com.example.listaurant.member.controller;

import com.example.listaurant.member.controller.port.MemberService;
import com.example.listaurant.member.controller.request.SignUpRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

    @GetMapping("/sign-up")
    public String signUpPage(){
        return "sign-up";
    }

    @GetMapping("/main")
    public String main(){
        return "main";
    }
    @PostMapping("/sign-up")
    public String signUp(@ModelAttribute SignUpRequest signUpRequest){
        log.info("SignUpRequest = {}",signUpRequest);
        if(memberService.isDuplicationEmail(signUpRequest.getEmail())){
            return "redirect:/sign-up?errormessage=사용중인이메일입니다.";
        }
        memberService.save(signUpRequest);
        return "login";
    }
}
