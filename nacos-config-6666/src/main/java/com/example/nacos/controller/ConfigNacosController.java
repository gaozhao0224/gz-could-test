package com.example.nacos.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RefreshScope
public class ConfigNacosController {

    @Value("${configInfo.data}")
    private String configInfo ;
    @Value("${server.port}")
    private String server ;

    @GetMapping("/config")
    public Object configInfo(){
        return "配置文件中的值是=\t\t\t"+configInfo+"\t\t\t端口号是="+server;
    }
}