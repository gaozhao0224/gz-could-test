package com.example.consumer.test.controller;

import com.common.entity.production.GzPerson;
import com.feign.production.service.ProductionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feignController")
public class FeignController {

    @Autowired
    private ProductionService productionService ;
    /*
    * 测试feign调用的时候  @ResponseBody 的作用 123
    * */
    @RequestMapping("/getGzPerson")
    public Object getGzPerson(){
        GzPerson gzPerson = productionService.getGzPerson("123");
        //
        System.out.println(gzPerson.toString());
        return gzPerson;
    }
}