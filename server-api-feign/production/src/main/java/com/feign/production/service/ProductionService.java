package com.feign.production.service;

import com.common.entity.User;
import com.common.entity.production.GzPerson;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/*
* 服务两端的返回值最好保持一致
* 被调用端如果返回对象那此接口（FeignClient注解的接口）中可以用Obj接收
* 但是如果返回的字符串 只能用String接收 不然序列换的时候会报错
* */
@FeignClient("production")
public interface ProductionService {

    @GetMapping("/getLBPro/{id}")
    String loadBalancerTest(@PathVariable("id") String id);
    @GetMapping("/getLBPro/test")
    Object loadBalancerTest(@RequestBody User user);
    @GetMapping("/concurrenceMany/{num}")
    String concurrenceMany(@PathVariable("num") String num);
    @GetMapping("/concurrenceManyRedis/{num}")
    String concurrenceManyRedis(@PathVariable("num") String num);
    @GetMapping("/concurrenceManyRedisson/{num}")
    String concurrenceManyRedisson(@PathVariable("num") String num);
    @PostMapping("/production/addPerson")
    Object addPerson(@RequestBody GzPerson gzPerson);
    @PostMapping("/production/timeOut")
    String timeOut();

    //@RequestMapping(value = "/getGzPerson", method = RequestMethod.POST)
    @PostMapping("/production/getGzPerson")
    @ResponseBody
    GzPerson getGzPerson(@RequestParam("id") String id);
}
