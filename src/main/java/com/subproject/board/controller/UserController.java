package com.subproject.board.controller;

import com.subproject.board.dto.auth.LoginDto;
import com.subproject.board.dto.auth.SignUpDto;
import com.subproject.board.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("login") LoginDto.Request request, Model model) {

        LoginDto.Response response = userService.login(request.getUserId(), request.getUserPassword());

        model.addAttribute("res",response);

        return "Board";
    }

    @PostMapping("/signup")
    public String signup(@Valid @RequestBody SignUpDto.Request request){

        return "SignUp";
    }

}
