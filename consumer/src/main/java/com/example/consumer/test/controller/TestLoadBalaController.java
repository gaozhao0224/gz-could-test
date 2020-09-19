package com.example.consumer.test.controller;

import cn.hutool.core.util.IdUtil;
import com.common.entity.User;
import com.feign.production.service.ProductionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
@RequestMapping("/test")
public class TestLoadBalaController {

    @Autowired
    private ProductionService productionService;

    /*
    * 字符串
    * */
    @Autowired
    private  StringRedisTemplate stringRedisTemplate;


    /*
    * 调用生产者  使用feign默认的负载均衡机制  默认轮询
    * */
    @RequestMapping("/test/{id}")
    public Object test(@PathVariable("id") String id){
        return "输入：\t\t"+id;
    }
    /*
    * 调用生产者  使用feign默认的负载均衡机制  默认轮询
    * */
    @RequestMapping("/getLBPro/{id}")
    public Object getLBPro(@PathVariable("id") String id){
        String datas2 = stringRedisTemplate.opsForValue().get("gz");
        Object o = productionService.loadBalancerTest(id);
        return o.toString()+"\t"+datas2+"\t"+ "\t" + IdUtil.simpleUUID();
    }
    @RequestMapping("/getLBPro/test/{id}")
    public Object getLBProTest(@PathVariable("id") String id){
        User user = new User();
        user.setId(id);
        user.setName("gz");
        Object o = productionService.loadBalancerTest(user);
        return o.toString();
    }


    @RequestMapping("/timeOut")
    public Object timeOut() throws Exception {
        TimeUnit.SECONDS.sleep(4);
        return "网关超时3秒，feign超时5秒，降级10s";
    }
    @RequestMapping("/timeOut1")
    public Object timeOut1() throws Exception {
        //productionService.timeOut();
        try {
            log.info("timeOut1");
            return "s";
        }catch (Exception e){
            return "网关超时3秒，feign超时5秒，降级10s";
        }
    }


}