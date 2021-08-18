package com.crc.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.crc.config.response.AjaxResult;
import com.crc.entity.BsExamineType;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 调查项种类表 服务类
 * </p>
 *
 * @author gz
 * @since 2021-04-23
 */
public interface IBsExamineTypeService extends IService<BsExamineType> {

    AjaxResult removeByIdexamineType(String id);

    IPage<BsExamineType> getList(IPage<BsExamineType> page, String itemId, String tableTypeId, String searchName);

    List<BsExamineType> inspectList(String subjectId, String tableTypeId);
}
