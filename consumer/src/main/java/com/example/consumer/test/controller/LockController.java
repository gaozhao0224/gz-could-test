package com.example.consumer.test.controller;

import com.common.controller.BaseController;
import com.feign.production.service.ProductionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/lock")
public class LockController extends BaseController {

    @Autowired
    private ProductionService productionService;
    /*
     * 字符串
     * */
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /*
     * 通过Jmeter 测试并发情况下同步机制
     * */
    @RequestMapping("/concurrence")
    public void concurrence(){
        synchronized (this){
            String gz = stringRedisTemplate.opsForValue().get("gz");
            Integer num = Integer.valueOf(gz);
            if(num>0){
                num--;
                stringRedisTemplate.opsForValue().set("gz",""+num);
                System.out.println("成功 还剩：\t"+num);
            }else{
                System.out.println("库存不足");
            }
        }
        //String s = IdUtil.simpleUUID();
        //System.out.println(s);
        //return s;
    }
    /*
     * 通过Jmeter 测试并发   通过集群测试 和解决办法
     * */
    @RequestMapping("/concurrenceMany/{num}")
    public Object concurrenceMany(@PathVariable("num") String num){
        Object str = productionService.concurrenceMany(num);
        System.out.println(str);
        return str;
    }
    /*
     * 通过Jmeter 测试并发   通过集群测试  通过redis解决
     * */
    @RequestMapping("/concurrenceManyRedis/{num}")
    public Object concurrenceManyRedis(@PathVariable("num") String num){
        Object str = productionService.concurrenceManyRedis(num);
        System.out.println(str);
        return str;
    }
    /*
     * 通过Jmeter 测试并发   通过集群测试  通过redisson解决 最简单  代码最少 最安全的
     * */
    @RequestMapping("/concurrenceManyRedisson/{num}")
    public Object concurrenceManyRedisson(@PathVariable("num") String num){
        Object str = productionService.concurrenceManyRedisson(num);
        System.out.println(str);
        return str;
    }

}