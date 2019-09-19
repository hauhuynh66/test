package com.house.controller;

import com.house.model.SensorValue;
import com.house.repository.SensorValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@RequestMapping("/sensor")
public class SensorController {
    @Autowired
    private SensorValueRepository sensorValueRepository;

    @PostMapping("/dht")
    public void processTemperature(@RequestParam(name = "temp")double t,
                                   @RequestParam(name = "humid")double humid){
        SensorValue value = new SensorValue();
        value.setTemp(t);
        value.setHumid(humid);
        value.setTime(new Date());
        sensorValueRepository.save(value);
    }
}
