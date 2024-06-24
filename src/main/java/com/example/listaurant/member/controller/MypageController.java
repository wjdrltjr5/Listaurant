package com.example.listaurant.member.controller;

import com.example.listaurant.member.service.port.MemberService;
import com.example.listaurant.member.controller.request.PwdUpdateRequest;
import com.example.listaurant.member.controller.request.UpdateRequest;
import com.example.listaurant.member.controller.response.MemberResponse;
import com.example.listaurant.member.service.MemberDetails;
import com.example.listaurant.member.service.dto.MemberDto;
import com.example.listaurant.txt.service.port.TxtService;
import com.example.listaurant.txt.controller.request.UpdateTxtRequest;
import com.example.listaurant.txt.service.dto.TxtDto;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/mypage")
@RequiredArgsConstructor
@Slf4j
public class MypageController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;
    private final TxtService txtService;

    @GetMapping
    public String mypage(@AuthenticationPrincipal MemberDetails memberDetails, Model model) {
        MemberDto memberDto = memberService.findById(memberDetails.getId()).get();
        List<TxtDto> commentList = txtService.findByMemberId(memberDetails.getId());
        MemberResponse response = MemberResponse.from(memberDto);
        model.addAttribute("member", response);
        model.addAttribute("commentList",commentList);
        return "mypage";
    }

    @GetMapping("/{memberId}")
    public String updateForm(@PathVariable("memberId") Long memberId, Model model) {
        MemberDto memberDto = memberService.findById(memberId).orElseThrow(() -> new UsernameNotFoundException("회원 정보가 없습니다."));
        MemberResponse response = MemberResponse.from(memberDto);
        model.addAttribute("member", response);
        return "mypage-edit";
    }

    @PostMapping("/update")
    public String updatePhoneNumber(@AuthenticationPrincipal MemberDetails memberDetails, @Valid @ModelAttribute UpdateRequest updateRequest, BindingResult br) {
        if (br.hasErrors()) {
            return "mypage-edit";
        }
        updateRequest.setMemberId(memberDetails.getId());
        memberService.update(MemberDto.from(updateRequest));
        return "redirect:/mypage";


    }

    @GetMapping("/delete")
    public String delete(@AuthenticationPrincipal MemberDetails memberDetails, HttpSession session){
        memberService.delete(memberDetails.getId());
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/password-update")
    public String updatePasswordPage(Model model){
        model.addAttribute("pwdUpdateRequest",new PwdUpdateRequest());
        return "password-update";
    }

    @PostMapping("/password-update")
    public String updatePassword(@AuthenticationPrincipal MemberDetails memberDetails, @Valid @ModelAttribute PwdUpdateRequest pwdUpdateRequest
            , BindingResult br){
        if(br.hasErrors()){
            return "password-update";
        }
        pwdUpdateRequest.setMemberId(memberDetails.getId());
        pwdUpdateRequest.setPasswd(passwordEncoder.encode(pwdUpdateRequest.getPasswd()));
        memberService.update(MemberDto.from(pwdUpdateRequest));
        return "redirect:/mypage";
    }

    @PostMapping("/comment-delete")
    public String textDelete(@RequestParam("commentId") long txtId) {
        txtService.deleteTxt(txtId);
        return "redirect:/mypage";
    }
    @PostMapping("/comment-update")
    public String textUpdate(@ModelAttribute UpdateTxtRequest updateTxtRequest) {
        log.info("updateTxtRequest = {}",updateTxtRequest);
        txtService.updateTxt(TxtDto.from(updateTxtRequest));
        return "redirect:/mypage";
    }
}
