package com.example.consumer.test.controller;


import com.common.entity.production.GzPerson;
import com.example.consumer.test.service.IGzPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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

}
