package com.feign.hystrix.service;

import org.springframework.stereotype.Component;

/*
* 统一服务降级类  请求超时 返回该方法内容  在yml里配置
* 异常处理 会把异常信息返回给客户端
* */
@Component
public class HystrixServiceImpl implements HystrixService {
    @Override
    public String provider_ok(String id) {
        return "方法 provider_ok 的服务降级";
    }

    @Override
    public String provider_timeOut(String id, Integer timeOut) {
        return "方法 provider_timeOut 的服务降级";
    }
}