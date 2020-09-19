package com.example.consumer.test.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.common.entity.production.GzPerson;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author gz
 * @since 2020-08-06
 */
public interface IGzPersonService extends IService<GzPerson> {

    boolean transactionOneColony(GzPerson gzPerson);

    void getTest();

}
