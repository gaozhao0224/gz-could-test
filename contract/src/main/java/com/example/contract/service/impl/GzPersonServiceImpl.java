package com.example.contract.service.impl;

import com.example.contract.vo.GzPerson;
import com.example.contract.mapper.GzPersonMapper;
import com.example.contract.service.IGzPersonService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author gz
 * @since 2020-11-27
 */
@Service
@Transactional
public class GzPersonServiceImpl extends ServiceImpl<GzPersonMapper, GzPerson> implements IGzPersonService {
    
    @Override
    public void getTst() {
        GzPerson gzPerson = new GzPerson();
        gzPerson.setUserName("name");
        save(gzPerson);

        List<GzPerson> list = list();
        System.out.println(list);
        throw new RuntimeException();
    }
}
