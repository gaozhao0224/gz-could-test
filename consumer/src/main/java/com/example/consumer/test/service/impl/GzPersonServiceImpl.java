package com.example.consumer.test.service.impl;

import cn.hutool.core.util.IdUtil;
import com.common.entity.production.GzPerson;
import com.example.consumer.test.mapper.GzPersonMapper;
import com.example.consumer.test.service.IGzPersonService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.feign.production.service.ProductionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author gz
 * @since 2020-08-06
 */
@Service
@Transactional
public class GzPersonServiceImpl extends ServiceImpl<GzPersonMapper, GzPerson> implements IGzPersonService {

    @Autowired
    private ProductionService productionService;

    @Override
    public boolean transactionOneColony(GzPerson gzPerson) {

        boolean falg = false;
        try {
            /*gzPerson.setId(IdUtil.simpleUUID());
            Object o = productionService.addPerson(gzPerson);
            int a = 10/0;
            gzPerson.setId(IdUtil.simpleUUID());
            Object o1 = productionService.addPerson(gzPerson);*/

           //super.saveOrUpdate(gzPerson);
           productionService.addPerson(gzPerson);
           int a = 10/0;
           gzPerson.setId(IdUtil.simpleUUID());
           gzPerson.setUserName("consumer服务方");
           gzPerson.setId(IdUtil.simpleUUID());
           super.saveOrUpdate(gzPerson);

            falg = true;
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }
        return falg;
    }
}
