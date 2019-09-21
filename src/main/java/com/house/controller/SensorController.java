package com.house.controller;

import com.house.model.SensorValue;
import com.house.repository.SensorValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.Date;

@Controller
@RequestMapping("/sensor")
public class SensorController {
    @Autowired
    private SensorValueRepository sensorValueRepository;

    @PostMapping("/dht")
    @ResponseBody
    public String processTemperature(@RequestParam(name = "temp")double t,
                                   @RequestParam(name = "humid")double humid){
        SensorValue value = new SensorValue();
        value.setTemp(t);
        value.setHumid(humid);
        value.setTime(new Date());
        sensorValueRepository.save(value);
        return "Success";
    }
    @GetMapping("/test")
    @ResponseBody
    public String test(){
        return "OI BRUV";
    }
}
