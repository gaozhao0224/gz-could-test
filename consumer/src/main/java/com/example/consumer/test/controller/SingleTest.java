package com.example.consumer.test.controller;

import com.example.consumer.config.SingleDog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/single")
public class SingleTest {

    @Autowired
    private SingleDog singleDog ;

    @RequestMapping("/test1")
    public String test1(){
        return singleDog.toString();
    }

    @RequestMapping("/test2")
    public String test2(SingleDog singleDog){
        this.singleDog.setAge(singleDog.getAge());
        this.singleDog.setSex(singleDog.getSex());
        this.singleDog.setName(singleDog.getName());
        return this.singleDog.toString();
    }

}