package com.crc.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.crc.config.response.AjaxResult;
import com.crc.entity.SysCentre;
import com.crc.service.ISysCentreService;
import com.crc.util.CommonNumberUtil;
import com.crc.util.SecurityUtils;
import com.crc.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 中心
 * @author gz
 */
@RestController
@RequestMapping("/centre")
@Slf4j
public class SysCentreController extends BaseController {

    @Autowired
    private ISysCentreService iSysCentreService;
    /*
     * 中心列表
     * */
    @GetMapping("/centreList")
    public AjaxResult list(String itemId,String searchName,String type){
        try {
            if(!StringUtils.isEmpty(type) && type.equals(CommonNumberUtil.ONEStr)){
                List<SysCentre> list = null;
                if(StringUtils.isEmpty(itemId)){
                    list = iSysCentreService.list();
                }else {
                    QueryWrapper<SysCentre> sysCentreQueryWrapper = new QueryWrapper<>();
                    sysCentreQueryWrapper.eq("item_id",itemId);
                    list = iSysCentreService.list(sysCentreQueryWrapper);
                }
                log.info("查询中心信息成功：", list);
                return AjaxResult.success(list);
            }else {
                IPage<SysCentre> page = initPage();
                IPage<SysCentre> list = iSysCentreService.getList(page,itemId,searchName);
                log.info("查询中心信息成功：", list);
                return AjaxResult.success(list);
            }
        }catch (Exception e){
            log.error("查询中心信息失败", e);
            throw e;
        }
    }
    /*
     * 新增中心
     * */
    @PostMapping("/centreSave")
    public AjaxResult save(@RequestBody SysCentre sysCentre){

        try {
            log.info("新增入参中心信息：{}，获取用户id：{}", sysCentre, SecurityUtils.getUserId());
            //中心CODE生成
            boolean save = iSysCentreService.save(sysCentre);
            return AjaxResult.success(save);
        }catch (Exception e){
            log.error("新增中心信息失败", e);
            throw e;
        }
    }
    /*
     * 删除中心
     * */
    @GetMapping("/centreDelete")
    public AjaxResult delete(@RequestParam String id){
        try {
            return iSysCentreService.removeByIdCentre(id);
        }catch (Exception e){
            log.error("删除中心信息失败", e);
            throw e;
        }
    }
    /*
     * 修改中心
     * */
    @PostMapping("/centreEdit")
    public AjaxResult edit(@RequestBody SysCentre sysCentre){

        try {
            log.info("编辑入参中心信息：{}", sysCentre);
            boolean edit = iSysCentreService.updateById(sysCentre);
            return AjaxResult.success(edit);
        }catch (Exception e){
            log.error("编辑中心信息失败", e);
            throw e;
        }
    }
    /*
     * 中心详情
     * */
    @GetMapping("/centreInfo")
    public AjaxResult centreInfo(@RequestParam String id){

        try {
            SysCentre byId = iSysCentreService.getById(id);
            return AjaxResult.success(byId);
        }catch (Exception e){
            log.error("编辑中心信息失败", e);
            throw e;
        }
    }
}
