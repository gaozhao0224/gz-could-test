package com.example.consumer.config;

import com.example.consumer.test.controller.SingleTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SingleConfig {

    @Bean
    public SingleDog singleDog(){
        SingleDog singleDog = new SingleDog("辉狗", "男", "24");
        System.out.println("-------------------------"+singleDog.toString());
        return singleDog;
    }

}