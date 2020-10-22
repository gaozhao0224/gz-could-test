package com.example.gz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.common.entity.TxLcB;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author gz
 * @since 2020-10-10
 */
public interface ITxLcBService extends IService<TxLcB> {

    boolean saveAdd(TxLcB txLcB);
}
