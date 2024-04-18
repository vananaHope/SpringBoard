package com.subproject.board.controller;

import com.subproject.board.common.security.UserPrincipal;
import com.subproject.board.dto.auth.SignUpDto;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {
    @GetMapping(value = "/")
    public String home() {

        return "/view/Login";
    }

    @GetMapping(value = "/signup")
    public String signup(Model model){

        model.addAttribute("reqSignup",new SignUpDto.Request());

        return "/view/SignUp";
    }

    @GetMapping("/board")
    public String board(@AuthenticationPrincipal UserPrincipal user, Model model){

        model.addAttribute("userId",user.getUserId());
        model.addAttribute("userNickname",user.getUserNickname());
        model.addAttribute("userAuthorities",user.getAuthorities());

        return "/view/Board";
    }

}
