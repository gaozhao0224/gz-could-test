package com.example.gz.service.impl;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.common.entity.TxLcA;
import com.example.gz.mapper.TxLcAMapper;
import com.example.gz.service.ITxLcAService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.gz.service.TxFeign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author gz
 * @since 2020-10-10
 */
@Service
@Slf4j
public class TxLcAServiceImpl extends ServiceImpl<TxLcAMapper, TxLcA> implements ITxLcAService {
    @Autowired
    private TxFeign txFeign;
    @Override
    @LcnTransaction   //LcnTransaction会覆盖Transactional  所有异常都会回滚
    @Transactional(noRollbackFor = ArithmeticException.class)
    public void saveAdd(TxLcA txLcA) throws CloneNotSupportedException {
        TxLcA clone = txLcA.clone();
        txLcA.setLcaName(txLcA.getLcaName()+"调用第二个服务");
        txLcA.setLcaVersion(txLcA.getLcaVersion()+"调用第二个服务");
        boolean b = txFeign.add(txLcA);
        log.info("调用服务新增:"+b);
        //save(clone);
        int a = 10/0;
        save(clone);
    }
}
