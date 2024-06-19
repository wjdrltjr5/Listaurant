package com.example.listaurant;

import com.example.listaurant.member.controller.port.MemberService;
import com.example.listaurant.member.controller.request.SignUpRequest;
import com.example.listaurant.member.controller.request.UpdateRequest;
import com.example.listaurant.member.controller.response.MemberResponse;
import com.example.listaurant.member.repository.MemberEntity;
import com.example.listaurant.member.service.MemberDetails;
import com.example.listaurant.txt.controller.port.TxtService;
import com.example.listaurant.txt.controller.request.CommentRequest;
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
public class IndexController {

    private final MemberService memberService;

    @GetMapping("/")
    public String main(){
        return "index";
    }


    @GetMapping("/board")
    public String board(@RequestParam("title") String title, @RequestParam("lat") float lat,
                        @RequestParam("lng") float lng, Model model){
        model.addAttribute("title",title);
        model.addAttribute("lat",lat);
        model.addAttribute("lng",lng);
        log.info("get /board {},{},{}",title, lat, lng);
        return "board";
    }

    @PostMapping("/board")
    public String boardComment(@RequestParam("title") String title, @RequestParam("lat") String lat,
                               @RequestParam("lng") String lng, Model model){
        log.info("post /board {},{},{}" ,title, lat, lng);
        model.addAttribute(title, title);
        model.addAttribute(lat, lat);
        model.addAttribute(lng, lng);
        return "board";
    }

    @PostMapping("/board")
    public String textSave(@Valid @ModelAttribute CommentRequest commentRequest) {
        TxtService.saveTxt(commentRequest);
        return "board";
    }
}
