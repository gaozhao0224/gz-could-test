package com.example.contract.service;

import com.example.contract.vo.BsContractSignLog;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author gz
 * @since 2020-07-23
 */
public interface IBsContractSignLogService extends IService<BsContractSignLog> {

    boolean save1(BsContractSignLog bsContractSignLog);
}
