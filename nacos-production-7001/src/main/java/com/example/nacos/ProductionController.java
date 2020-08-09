package com.example.nacos;

import cn.hutool.core.util.IdUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/nacosProduction")
public class ProductionController {

    @Value("${server.port}")
    private String server ;
    @GetMapping("/LBProduction/{id}")
    public Object LBProduction(@PathVariable("id") String id){
        return "唯一号"+ IdUtil.simpleUUID() +"负载均衡测试调用项目端口是=="+server+"\t\t\tid="+id;
    }
}