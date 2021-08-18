package com.crc.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.crc.config.response.AjaxResult;
import com.crc.entity.SysExamineData;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 病例审查数据 服务类
 * </p>
 *
 * @author gz
 * @since 2021-04-20
 */
public interface ISysExamineDataService extends IService<SysExamineData> {

    SysExamineData getByIdRelevance(String id);

    IPage<SysExamineData> getList(IPage<SysExamineData> page, String itemId, String tableTypeId, String examineTypeId);

    AjaxResult removeByIdRelevance(String id);

    AjaxResult saveRelevance(List<SysExamineData> sysExamineDatas, String tableTypeId, String examineTypeId, String subjectId);

    List<SysExamineData> examineSubjectList(String itemId, String tableTypeId, String examineTypeId, String subjectId);
}
