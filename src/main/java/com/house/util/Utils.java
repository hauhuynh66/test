package com.house.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.house.model.User;
import com.house.repository.UserRepository;
import com.house.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class Utils {
    @Autowired
    private UserRepository userRepository;
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(5);
    }

    public String mapper(Object e){
        String res = "";
        ObjectMapper mapper = new ObjectMapper();
        try{
            res = mapper.writeValueAsString(e);
        }catch (JsonProcessingException j){
            System.out.println(j.getMessage());
        }
        return res;
    }

    private static String getPrincipal(){
        Object object = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = "";
        if(object instanceof CustomUserDetails){
            email = ((CustomUserDetails) object).getUsername();
        }
        return email;
    }

    public User getUser(){
        String email = getPrincipal();
        return userRepository.findByEmail(email);
    }
}
