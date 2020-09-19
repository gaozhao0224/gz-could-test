package com.example.consumer.test.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.common.entity.production.GzPerson;
import com.example.consumer.test.service.IGzPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author gz
 * @since 2020-08-06
 */
@RestController
@RequestMapping("/consumer")
public class GzPersonController {


    @Autowired
    private IGzPersonService gzPersonService;

    //--------------测试分布式事务一个方法调用多个服务测试--------------------------------------------------------------------------------------
    //--------------还有一个服务集群情况下--------------------------------------------------------------------------------------

    /*
     * 先调一个服务  但是改服务是一个集群得情况下
     * */
    @RequestMapping("/transactionOneColony")
    public Object transactionOneColony(GzPerson gzPerson){
        boolean b = gzPersonService.transactionOneColony(gzPerson);
        return b;
    }
    /*
    * 数据库同一行是一个对象  指向一个地址
    * 因为mybatis一级缓存 查到的对象放在了缓存中  一次事务没结束 缓存的对象都是一个  如果不需要可以手动关闭
    * */
    @RequestMapping("/list")
    public Object list(){
        List<GzPerson> gzPeoples = new ArrayList<>();

        GzPerson byId = gzPersonService.getById("61a0cacca78e47eea922e182c06b8588");
        GzPerson byId2 = gzPersonService.getById("61a0cacca78e47eea922e182c06b8588");
        QueryWrapper<GzPerson> gzPersonQueryWrapper = new QueryWrapper<>();
        gzPersonQueryWrapper.eq("user_name","production服务方");
        GzPerson byId3 = gzPersonService.getOne(gzPersonQueryWrapper);
        GzPerson byId4 = gzPersonService.getById("121212121");
        System.out.println(byId.toString());
        System.out.println(byId2.toString());
        System.out.println(byId3.toString());
        System.out.println(byId4.toString());
        gzPeoples.add(byId);
        gzPeoples.add(byId2);
        gzPeoples.add(byId3);
        return gzPeoples.toString();
    }

    @RequestMapping("/test")
    public void test(){
        gzPersonService.getTest();
    }

}
