package com.example.gz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.example.gz.mapper.TxLcBMapper;
import com.example.gz.service.ITxLcBService;
import com.common.entity.TxLcB;
import lombok.extern.slf4j.Slf4j;
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
public class TxLcBServiceImpl extends ServiceImpl<TxLcBMapper, TxLcB> implements ITxLcBService {

    @Override
    @LcnTransaction//分布式事务
    @Transactional //本地事务
    public boolean saveAdd(TxLcB txLcB) {
        boolean save = save(txLcB);
        log.info("第二个服务得新增状态:"+save);
        return save;
    }
}
