package com.crc.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.crc.config.response.AjaxResult;
import com.crc.entity.SysCentre;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 项目 服务类
 * </p>
 *
 * @author gz
 * @since 2021-04-06
 */
public interface ISysCentreService extends IService<SysCentre> {

    IPage<SysCentre> getList(IPage<SysCentre> page, String itemId, String searchName);

    AjaxResult removeByIdCentre(String id);
}
