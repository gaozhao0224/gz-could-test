package com.crc.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.crc.config.response.AjaxResult;
import com.crc.entity.SysExamineData;
import com.crc.service.ISysExamineDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 病例审查数据 前端控制器
 * </p>
 *
 * @author gz
 * @since 2021-04-20
 */
@RestController
@RequestMapping("/examine")
@Slf4j
public class SysExamineDataController extends BaseController {

    @Autowired
    private ISysExamineDataService iSysExamineDataService;

    /*
     * 调查项目题目列表 超级管理员查看
     * */
    @GetMapping("/examineList")
    public AjaxResult list(String itemId,String tableTypeId,String examineTypeId){
        try {
            /*
            * 只给一级的题
            * */
            IPage<SysExamineData> page = initPage();
            IPage<SysExamineData> list = iSysExamineDataService.getList(page,itemId,tableTypeId,examineTypeId);
            log.info("查询调查项目题目信息成功：", list);
            return AjaxResult.success(list);
        }catch (Exception e){
            log.error("查询调查项目题目信息失败", e);
            throw e;
        }
    }


    /*
     * 新增调查项目题目 超级管理员
     * */
    @PostMapping("/examineSaveAdmin")
    public AjaxResult examineSaveAdmin(@RequestBody SysExamineData sysExamineData) {

        try {
            log.info("新增入参调查项目题目信息：{}", sysExamineData);
            boolean save = iSysExamineDataService.save(sysExamineData);
            return AjaxResult.success(save);
        }catch (Exception e){
            log.error("新增调查项目题目信息失败", e);
            throw e;
        }
    }

    /*
     * 删除调查项目题目 超级管理员操作
     * */
    @GetMapping("/examineDelete")
    public AjaxResult delete(@RequestParam String id){
        try {
            return iSysExamineDataService.removeByIdRelevance(id);
        }catch (Exception e){
            log.error("删除调查项目题目信息失败", e);
            throw e;
        }
    }

    /*
     * 调查项目题目详情 超级管理员操作
     * */
    @GetMapping("/examineInfo")
    public AjaxResult examineInfo(@RequestParam String id){

        try {
            SysExamineData byId = iSysExamineDataService.getByIdRelevance(id);
            return AjaxResult.success(byId);
        }catch (Exception e){
            log.error("编辑调查项目题目信息失败", e);
            throw e;
        }
    }













    //--------------------------------------上面超级员操作-----下面录入人员操作-----------------------------------------------------------------

    /*
     * 调查项目题目列表 录入员  参数全部必填
     * */
    @GetMapping("/examineSubjectList")
    public AjaxResult examineSubjectList(@RequestParam("itemId") String itemId,@RequestParam("tableTypeId") String tableTypeId,@RequestParam("examineTypeId") String examineTypeId, @RequestParam("subjectId") String subjectId){
        try {
            //通过模板题关联该受试者获取答案
            List<SysExamineData> list = iSysExamineDataService.examineSubjectList(itemId,tableTypeId,examineTypeId,subjectId);
            log.info("受试者对应查询调查项目题目信息成功：", list);
            return AjaxResult.success(list);
        }catch (Exception e){
            log.error("受试者对应查询调查项目题目信息失败", e);
            throw e;
        }
    }

    /*
     * 新增和编辑调查项目题目 录入人员  删除重新添加
     * */
    @PostMapping("/examineSaveSubject")
    public AjaxResult examineSaveSubject(@RequestBody List<SysExamineData> sysExamineDatas,@RequestParam("tableTypeId") String tableTypeId,@RequestParam("examineTypeId") String examineTypeId, @RequestParam("subjectId") String subjectId) {
        try {
            log.info("新增入参调查项目题目信息：{}", sysExamineDatas);
            return iSysExamineDataService.saveRelevance(sysExamineDatas,tableTypeId,examineTypeId,subjectId);
        }catch (Exception e){
            log.error("新增调查项目题目信息失败", e);
            throw e;
        }
    }

}
