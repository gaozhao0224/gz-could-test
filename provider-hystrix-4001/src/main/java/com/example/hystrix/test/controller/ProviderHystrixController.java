package com.example.hystrix.test.controller;

import com.example.hystrix.test.service.ProviderHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
@RequestMapping("/hystrix")
//@DefaultProperties(defaultFallback = "defaultFallbackMethod")  全局配置  实际操作用不到  都是在feign接口中配置降级
public class ProviderHystrixController {

    @Autowired
    private ProviderHystrixService providerHystrixService;

    @GetMapping("/provider/{id}")
    public Object provider_ok(@PathVariable("id") String id){
        String msg = providerHystrixService.provider_ok(id);
        return msg;
    }
    @GetMapping("/provider/{id}/{timeOut}")
    public Object provider_timeOut(@PathVariable("id") String id,@PathVariable("timeOut") Integer timeOut){
        String msg = providerHystrixService.provider_timeOut(id,timeOut);
        return msg;
    }


    @GetMapping("/provider/defaultFallback/{timeOut}")
    @HystrixCommand  //通过该注解
    public Object provider_defaultFallback(@PathVariable("timeOut") Integer timeOut){
        try {
            TimeUnit.SECONDS.sleep(timeOut);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "成功";
    }
    /*public Object defaultFallbackMethod(){
        return "默认全局的降级方法";
    }*/

    @GetMapping("/errorTimeAwait/{num}")
    public Object errorTimeAwait(@PathVariable("num") Integer num){
        String msg  = providerHystrixService.errorTimeAwait(num);
        return msg;
    }
}