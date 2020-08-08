package com.example.config.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/configtest")
@RefreshScope
public class TestController {

    @Value("${person.name}")
    private String name ;
    @Value("${person.age}")
    private String age ;
    @Value("${server.port}")
    private String port ;
    @RequestMapping("/config")
    public Object getConfig(){
        return "姓名是 = "+name+"\t年纪是="+age+"端口号是="+port;
    }
}