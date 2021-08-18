package com.example.contract.service.impl;

import com.example.contract.vo.BsContractSignLog;
import com.example.contract.mapper.BsContractSignLogMapper;
import com.example.contract.service.IBsContractSignLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author gz
 * @since 2020-07-23
 */
@Service
public class BsContractSignLogServiceImpl extends ServiceImpl<BsContractSignLogMapper, BsContractSignLog> implements IBsContractSignLogService {

    @Override
    public boolean save1(BsContractSignLog bsContractSignLog) {
        return save(bsContractSignLog);
    }

}
