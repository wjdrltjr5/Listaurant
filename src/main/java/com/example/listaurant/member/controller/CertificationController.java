package com.example.listaurant.member.controller;

import com.example.listaurant.member.controller.port.MemberService;
import com.example.listaurant.member.service.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequiredArgsConstructor
@Slf4j
public class CertificationController {

    private final MemberService memberService;

    @GetMapping("/certification")
    public String certification(@RequestParam("code") String code,@RequestParam("email") String email){
        MemberDto memberDto = memberService.findByEmail(email).get();
        if(memberDto.getCertificationCode().equals(code)){
            memberService.activeStatus(email);
        }
        return "redirect:/login";
    }

}
