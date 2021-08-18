package com.crc.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.crc.config.response.AjaxResult;
import com.crc.entity.BsExamineType;
import com.crc.entity.CeSubjectTtEt;
import com.crc.entity.SysCentre;
import com.crc.service.ICeSubjectTtEtService;
import com.crc.util.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 检查员对该受试者项目种类意见和表类意见  统一关联 前端控制器
 * </p>
 *
 * @author gz
 * @since 2021-04-25
 */
@RestController
@RequestMapping("/subjectTtEt")
@Slf4j
public class CeSubjectTtEtController {

    @Autowired
    private ICeSubjectTtEtService iCeSubjectTtEtService;


    /*
     * 调查项种类审核和意见 核查员
     * */
    @GetMapping("/subjectTtEtInfo")
    public AjaxResult inspectList(@RequestParam("examineTypeId") String examineTypeId,@RequestParam("subjectId") String subjectId){
        try {
            QueryWrapper<CeSubjectTtEt> ceSubjectTtEtQueryWrapper = new QueryWrapper<>();
            ceSubjectTtEtQueryWrapper.eq("examine_type_id",examineTypeId);
            ceSubjectTtEtQueryWrapper.eq("subject_id",subjectId);
            CeSubjectTtEt one = iCeSubjectTtEtService.getOne(ceSubjectTtEtQueryWrapper);
            log.info("查询调查项种类信息成功：", one);
            return AjaxResult.success(one);
        }catch (Exception e){
            log.error("查询调查项种类信息失败", e);
            throw e;
        }
    }


    /*
     * 新增审核和意见
     * */
    @PostMapping("/subjectTtEtSave")
    public AjaxResult save(@RequestBody CeSubjectTtEt ceSubjectTtEt){

        try {
            log.info("新增入参审核和意见信息：{}，获取用户id：{}", ceSubjectTtEt, SecurityUtils.getUserId());
            //审核和意见CODE生成
            boolean save = iCeSubjectTtEtService.save(ceSubjectTtEt);
            return AjaxResult.success(save);
        }catch (Exception e){
            log.error("新增审核和意见信息失败", e);
            throw e;
        }
    }
    /*
     * 编辑审核和意见
     * */
    @PostMapping("/subjectTtEtEdit")
    public AjaxResult update(@RequestBody CeSubjectTtEt ceSubjectTtEt){

        try {
            log.info("编辑入参审核和意见信息：{}，获取用户id：{}", ceSubjectTtEt, SecurityUtils.getUserId());
            CeSubjectTtEt byId = iCeSubjectTtEtService.getById(ceSubjectTtEt.getId());
            if(byId==null){
                log.info("需要编辑信息不存在，对象为：{}", byId);
                AjaxResult.error("信息不存在");
            }
            byId.setOpinion(ceSubjectTtEt.getOpinion());
            byId.setStatus(ceSubjectTtEt.getStatus());
            //审核和意见CODE生成
            boolean update = iCeSubjectTtEtService.updateById(byId);
            return AjaxResult.success(update);
        }catch (Exception e){
            log.error("编辑审核和意见信息失败", e);
            throw e;
        }
    }


}
