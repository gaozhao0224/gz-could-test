package com.example.production.service.impl;

import cn.hutool.core.util.IdUtil;
import com.common.entity.production.GzPerson;
import com.example.production.mapper.GzPersonMapper;
import com.example.production.service.IGzPersonService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author gz
 * @since 2020-07-27
 */
@Service
public class GzPersonServiceImpl extends ServiceImpl<GzPersonMapper, GzPerson> implements IGzPersonService {

    @Override
    @Transactional
    public Object getSaveOrUpdate(GzPerson gzPerson) {
        System.out.println(gzPerson.toString());
        gzPerson.setId(IdUtil.simpleUUID());
        gzPerson.setUserName("production服务方");
        //int i = 10/0;
        return saveOrUpdate(gzPerson);
    }
}
