package com.house.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class Utils {
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
}
