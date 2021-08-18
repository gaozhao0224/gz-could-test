package com.crc.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.crc.config.response.AjaxResult;
import com.crc.entity.BsTableType;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 受试者和调查项类型表 服务类
 * </p>
 *
 * @author gz
 * @since 2021-04-22
 */
public interface IBsTableTypeService extends IService<BsTableType> {

    IPage<BsTableType> getList(IPage<BsTableType> page, String itemId, String searchName);

    AjaxResult removeByIdtableType(String id);

    AjaxResult updateByIdRelevance(BsTableType bsTableType);

    List<BsTableType> inspectList(String itemId, String subjectId);
}
