package com.example.gz.service;

import com.common.entity.TxLcA;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author gz
 * @since 2020-10-10
 */
public interface ITxLcAService extends IService<TxLcA> {

    void saveAdd(TxLcA txLcA) throws CloneNotSupportedException;
}
