package com.example.controller;

import com.example.demo.ChineseName;
import com.example.service.IUiBotTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/uiBotTest")
public class UiBotTest {

    @Autowired
    private IUiBotTest uiBotTest;

    @GetMapping("/login")
    public Object login(String taxpayerCode){
        System.out.println(taxpayerCode);
        HashMap<String, Object> result = new HashMap<>();
        HashMap<String, Object> map = new HashMap<>();
        String userName = "913713023492282789";
        String password = "Sy514629";
        map.put("userName",userName);
        map.put("userPwd",password);
        result.put("data",map);
        result.put("code","000");
        result.put("message","接口调用成功");
        return result;
    }
    @GetMapping("/getName")
    public Object getName(){
        return ChineseName.randomChineseName();
    }

}