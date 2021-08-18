package com.crc.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.crc.config.SysUser;
import com.crc.config.response.AjaxResult;
import com.crc.entity.SysItem;
import com.crc.service.ISysItemService;
import com.crc.util.CommonNumberUtil;
import com.crc.util.SecurityUtils;
import com.crc.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 项目
 * @author gz
 */
@RestController
@RequestMapping("/item")
@Slf4j
public class SysItemController extends BaseController {

    @Autowired
    private ISysItemService iSysItemService;
    /*
     * 项目列表
     * */
    @GetMapping("/itemList")
    public AjaxResult list(String searchName,String type){
        try {
            if(!StringUtils.isEmpty(type) && type.equals(CommonNumberUtil.ONEStr)){
                List<SysItem> list = iSysItemService.listAll();
                log.info("无分页查询项目信息成功：", list);
                return AjaxResult.success(list);
            }else {
                IPage<SysItem> page = initPage();
                IPage<SysItem> list = iSysItemService.getList(page,searchName);
                log.info("查询项目信息成功：", list);
                return AjaxResult.success(list);
            }
        }catch (Exception e){
            log.error("查询项目信息失败", e);
            throw e;
        }
    }


    /*
     * 新增项目
     * */
    @PostMapping("/itemSave")
    public AjaxResult save(@RequestBody SysItem sysItem){

        try {
            log.info("新增入参项目信息：{}，获取用户id：{}", sysItem, SecurityUtils.getUserId());
            //项目CODE生成
            boolean save = iSysItemService.save(sysItem);
            return AjaxResult.success(save);
        }catch (Exception e){
            log.error("新增项目信息失败", e);
            throw e;
        }
    }
    /*
     * 删除项目
     * */
    @GetMapping("/itemDelete")
    public AjaxResult delete(@RequestParam String id){
        try {
            //如果项目下有中心不能删除
            return iSysItemService.removeByIdItem(id);
        }catch (Exception e){
            log.error("删除项目信息失败", e);
            throw e;
        }
    }
    /*
     * 修改项目
     * */
    @PostMapping("/itemEdit")
    public AjaxResult edit(@RequestBody SysItem sysItem){

        try {
            log.info("编辑入参项目信息：{}", sysItem);
            boolean edit = iSysItemService.updateById(sysItem);
            return AjaxResult.success(edit);
        }catch (Exception e){
            log.error("编辑项目信息失败", e);
            throw e;
        }
    }
    /*
     * 项目详情
     * */
    @GetMapping("/itemInfo")
    public AjaxResult itemInfo(@RequestParam String id){

        try {
            SysItem byId = iSysItemService.getInfoById(id);
            return AjaxResult.success(byId);
        }catch (Exception e){
            log.error("编辑项目信息失败", e);
            throw e;
        }
    }
    /*
     * 获取用户下的项目
     * */
    @GetMapping("/userItem")
    public AjaxResult userItemCenter(@RequestParam String userId){
        try {
            List<SysItem> SysItems = iSysItemService.getUserItem(userId);
            return AjaxResult.success(SysItems);
        }catch (Exception e){
            log.error("编辑用户信息失败", e);
            throw e;
        }
    }
}
