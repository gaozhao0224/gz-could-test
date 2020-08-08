package com.example.production.service.impl;

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
        return saveOrUpdate(gzPerson);
    }
}
