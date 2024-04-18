package com.subproject.board.controller;

import com.subproject.board.dto.auth.SignUpDto;
import com.subproject.board.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public String signup(@Valid @ModelAttribute("reqSignup") SignUpDto.Request request){

        SignUpDto.Response response = userService.signup(request);

        return "redirect:/";
    }

}
