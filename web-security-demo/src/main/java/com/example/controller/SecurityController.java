package com.example.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/securityDemo")
public class SecurityController {

    @RequestMapping("/test")
    public String getSecurity(){
        return "123";
    }
    @RequestMapping("/test1")
    public String getSecurity1(){
        return "-------------------------123-----------------------------";
    }

}