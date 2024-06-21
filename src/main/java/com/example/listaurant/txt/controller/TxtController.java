package com.example.listaurant.txt.controller;

import com.example.listaurant.member.service.MemberDetails;
import com.example.listaurant.txt.controller.port.TxtService;
import com.example.listaurant.txt.service.TxtServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@RequiredArgsConstructor
//@Controller
public class TxtController {
    private final TxtService txtService;

    @GetMapping("/board")
    public String board(Model model) {
        model.addAttribute("");
        return "board";
    }
}
