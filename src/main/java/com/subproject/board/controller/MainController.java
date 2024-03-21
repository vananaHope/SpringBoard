package com.subproject.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping(value = "/")
    public String home() {

        return "Login";
    }

    @GetMapping(value = "/signup")
    public String signup(){
        return "SignUp";
    }

}
