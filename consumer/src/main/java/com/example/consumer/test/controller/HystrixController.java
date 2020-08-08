package com.example.consumer.test.controller;

import com.feign.hystrix.service.HystrixService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/hystrix")
public class HystrixController {

    @Autowired
    private HystrixService hystrixService;

    @GetMapping("/provider/{id}")
    public Object provider_ok(@PathVariable("id") String id){
        String msg = hystrixService.provider_ok(id);
        return msg;
    }
    @GetMapping("/provider/{id}/{timeOut}")
    public Object provider_timeOut(@PathVariable("id") String id,@PathVariable("timeOut") Integer timeOut){
        String msg = hystrixService.provider_timeOut(id,timeOut);
        return msg;
    }


}