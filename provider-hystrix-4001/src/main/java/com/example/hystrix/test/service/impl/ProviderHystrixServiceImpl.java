package com.example.hystrix.test.service.impl;

import com.example.hystrix.test.service.ProviderHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;
@Service
public class ProviderHystrixServiceImpl implements ProviderHystrixService {

    @Override
    public String provider_ok(String id) {
        long l = System.currentTimeMillis();
        long z = System.currentTimeMillis();
        long x = l - z;
        System.out.println("线程池："+Thread.currentThread().getName()+"\tid= "+id+"方法执行时间= "+(x/1000)+"秒");
        return "线程池："+Thread.currentThread().getName()+"\tid= "+id+"方法执行时间= "+(x/1000)+"秒";
    }

    @Override
    //在单独方法上配置  除了特殊情况 特殊方法  不可能在每个方法做降级 都会通过全局配置来实现
/*    @HystrixCommand(fallbackMethod = "paymentTimeOutFallbackMethod", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })*/
    public String provider_timeOut(String id, Integer timeOut) {
        try {
            //timeOut
            TimeUnit.SECONDS.sleep(timeOut);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("线程池："+Thread.currentThread().getName()+"\tid= "+id+"方法执行时间= "+timeOut+"秒");
        return "线程池："+Thread.currentThread().getName()+"\tid= "+id+"方法执行时间= "+timeOut+"秒";
    }

    //兜底方法  如果不满足后面条件 就返回该方法的结果  不继续做等待
    //设置 fallbackMethod 指定的 返回值方法类型要跟目标方法一致,否则将报错。
   /* public String paymentTimeOutFallbackMethod(String id, Integer timeOut) {
        return "运行超时或者系统繁忙,请稍后再试o(╥﹏╥)o    id = "+ id +"睡眠时间等于 = "+ timeOut;
    }*/
 //在10s中有6次请求失败则触发断路器，10s一个窗口  熔断
    @Override
    @HystrixCommand(fallbackMethod = "timeOutHandler",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),//是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),//请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),//时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60"),//失败率达到多少后触发熔断
    })
    public String errorTimeAwait(Integer num){
        String msg  = "默认值";
        if(num>0){
            msg = "成功";
        }else{
            msg = "异常";
            throw new RuntimeException();
        }
        return msg;
    }
    public String timeOutHandler(Integer num){
        return "走了劳资这个降级的方法  参数是 = "+num ;
    }
}