package com.crc.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.crc.entity.BsSubject;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 受试者信息 服务类
 * </p>
 *
 * @author gz
 * @since 2021-04-18
 */
public interface IBsSubjectService extends IService<BsSubject> {

    IPage<BsSubject> getList(IPage<BsSubject> page, String itemId, String centreId, String search);
}
