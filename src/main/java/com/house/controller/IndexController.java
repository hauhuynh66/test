package com.house.controller;

import com.house.model.User;
import com.house.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {
    @Autowired
    private Utils utils;

    @GetMapping({"/","/login"})
    public ModelAndView login(){
        User user = utils.getUser();
        if(user==null){
            return new ModelAndView("login");
        }else{
            if(user.getRoleList().contains("ADMIN")){
                return new ModelAndView("redirect:/admin/dashboard");
            }else{
                return new ModelAndView("redirect:/user/dashboard");
            }
        }
    }

    @GetMapping("/forget-password")
    public ModelAndView forgetPassword(){
        return new ModelAndView("forget-password");
    }

    @GetMapping("/message")
    public ModelAndView chatRoom(Model model){
        User user = utils.getUser();
        if(user==null){
            return new ModelAndView("login");
        }else{
            ModelAndView mv = new ModelAndView("message");
            mv.addObject("user",user);
            return mv;
        }
    }

    @GetMapping("/forbidden")
    public ModelAndView forbidden(){
        return new ModelAndView("forbidden");
    }
}
