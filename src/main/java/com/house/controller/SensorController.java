package com.house.controller;

import com.house.model.SensorValue;
import com.house.repository.SensorValueRepository;
import com.house.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/sensor")
public class SensorController {
    @Autowired
    private SensorValueRepository sensorValueRepository;
    @Autowired
    private Utils utils;


    @GetMapping("/dht")
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

    @GetMapping("/getData")
    @ResponseBody
    public String getData(){
        List<SensorValue> data = sensorValueRepository.findAll(new Sort(Sort.Direction.DESC,"id"));
        return utils.mapper(data.get(0));
    }

}
