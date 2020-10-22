package com.example.gz.controller;


import com.common.entity.TxLcA;
import com.example.gz.service.ITxLcAService;
import com.example.gz.service.TxFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author gz
 * @since 2020-10-10
 */
@RestController
@RequestMapping("/gz/txLcA")
/*
* 关于分布式事务注解   在调用端用注解 被调用端不用 没有作用 。  被调用端会入库
*                   在被调用端用注解 调用端不用 没有作用 。  被调用端会入库
*               结论：必须在两边都要加上@LcnTransaction注解
*                    LcnTransaction会覆盖Transactional  所有异常都会回滚
*
* */
public class TxLcAController {

    @Autowired
    private ITxLcAService txLcAService;
    @RequestMapping("/add")
    public void add(TxLcA txLcA) throws CloneNotSupportedException {

        //TxLcA txLcA = new TxLcA();
        txLcAService.saveAdd(txLcA);
    }
}
