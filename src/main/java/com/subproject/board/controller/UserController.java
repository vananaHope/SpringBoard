package com.subproject.board.controller;

import com.subproject.board.dto.auth.SignUpDto;
import com.subproject.board.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

//    @PostMapping("/login-process")
//    public String login(@Valid @ModelAttribute("reqLogin") LoginDto.Request request, Model model) {
//
//        LoginDto.Response response = userService.login(request.getUserId(), request.getUserPassword());
//
//        model.addAttribute("login",response);
//
//        return "Board";
//    }

    @PostMapping("/signup")
    public String signup(@Valid @ModelAttribute("reqSignup") SignUpDto.Request request, Model model){

        SignUpDto.Response response = userService.signup(request);

        model.addAttribute("signUp", response);

        return "Login";
    }

}
