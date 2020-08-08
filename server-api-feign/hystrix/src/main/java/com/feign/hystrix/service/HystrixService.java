package com.feign.hystrix.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/*
* 服务两端的返回值最好保持一致
* 被调用端如果返回对象那此接口（FeignClient注解的接口）中可以用Obj接收
* 但是如果返回的字符串 只能用String接收 不然序列换的时候会报错
* */
@Component
@FeignClient(value = "privider-hystrix",fallback = HystrixServiceImpl.class)
public interface HystrixService {

    @GetMapping("/hystrix/provider/{id}")
    String provider_ok(@PathVariable("id") String id);
    @GetMapping("/hystrix/provider/{id}/{timeOut}")
    String provider_timeOut(@PathVariable("id") String id,@PathVariable("timeOut") Integer timeOut);
}

