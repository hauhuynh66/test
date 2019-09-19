package com.house.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {
    @GetMapping({"/","/login"})
    public ModelAndView login(){
        return new ModelAndView("login");
    }
    @GetMapping("/forget-password")
    public ModelAndView forgetPassword(){
        return new ModelAndView("forget-password");
    }
}
