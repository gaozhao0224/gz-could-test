package com.crc.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.crc.config.response.AjaxResult;
import com.crc.entity.SysItem;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 项目 服务类
 * </p>
 *
 * @author gz
 * @since 2021-04-05
 */
public interface ISysItemService extends IService<SysItem> {

    IPage<SysItem> getList(IPage<SysItem> page, String searchName);

    AjaxResult removeByIdItem(String id);

    List<SysItem> listAll();

    SysItem getInfoById(String id);

    List<SysItem> getUserItem(String userId);
}
