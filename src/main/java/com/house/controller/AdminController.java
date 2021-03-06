package com.house.controller;

import com.house.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @GetMapping("/register")
    public ModelAndView registerForm(){
        ModelAndView mv = new ModelAndView("register");
        mv.addObject("user",new User());
        return mv;
    }

    @GetMapping("/dashboard")
    public ModelAndView adminHome(){
        return new ModelAndView("admin");
    }

    @GetMapping("/user_list")
    public ModelAndView userList(){
        ModelAndView mv = new ModelAndView("users");
        mv.addObject("users","");
        return mv;
    }
}
