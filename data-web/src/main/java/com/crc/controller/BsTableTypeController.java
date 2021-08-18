package com.crc.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.crc.config.response.AjaxResult;
import com.crc.entity.BsTableType;
import com.crc.entity.BsTableType;
import com.crc.service.IBsTableTypeService;
import com.crc.util.CommonNumberUtil;
import com.crc.util.SecurityUtils;
import com.crc.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 受试者和调查项类型表 前端控制器
 * </p>
 *
 * @author gz
 * @since 2021-04-22
 */
@RestController
@RequestMapping("/tableType")
@Slf4j
public class BsTableTypeController extends BaseController{
    @Autowired
    private IBsTableTypeService iBsTableTypeService;
    /*
     * 调查分类表类型列表
     * */
    @GetMapping("/tableTypeList")
    public AjaxResult list(String itemId, String searchName, String type){
        try {
            if(!StringUtils.isEmpty(type) && type.equals(CommonNumberUtil.ONEStr)){
                List<BsTableType> list = null;
                if(StringUtils.isEmpty(itemId)){
                    list = iBsTableTypeService.list();
                }else {
                    QueryWrapper<BsTableType> bsTableTypeQueryWrapper = new QueryWrapper<>();
                    bsTableTypeQueryWrapper.eq("item_id",itemId);
                    list = iBsTableTypeService.list(bsTableTypeQueryWrapper);
                }
                log.info("查询调查分类表类型信息成功：", list);
                return AjaxResult.success(list);
            }else {
                IPage<BsTableType> page = initPage();
                IPage<BsTableType> list = iBsTableTypeService.getList(page,itemId,searchName);
                log.info("查询调查分类表类型信息成功：", list);
                return AjaxResult.success(list);
            }
        }catch (Exception e){
            log.error("查询调查分类表类型信息失败", e);
            throw e;
        }
    }
    /*
     * 调查项表类列表 核查员
     * */
    @GetMapping("/tableTypeInspectList")
    public AjaxResult inspectList(@RequestParam("itemId") String itemId,@RequestParam("subjectId") String subjectId){
        try {
            List<BsTableType> list = iBsTableTypeService.inspectList(itemId,subjectId);
            log.info("查询调查项种类信息成功：{}", list);
            return AjaxResult.success(list);
        }catch (Exception e){
            log.error("查询调查项种类信息失败", e);
            throw e;
        }
    }


    /*
     * 新增调查分类表类型
     * */
    @PostMapping("/tableTypeSave")
    public AjaxResult save(@RequestBody BsTableType bsTableType){

        try {
            log.info("新增入参调查分类表类型信息：{}，获取用户id：{}", bsTableType, SecurityUtils.getUserId());
            boolean save = iBsTableTypeService.save(bsTableType);
            return AjaxResult.success(save);
        }catch (Exception e){
            log.error("新增调查分类表类型信息失败", e);
            throw e;
        }
    }
    /*
     * 删除调查分类表类型
     * */
    @GetMapping("/tableTypeDelete")
    public AjaxResult delete(@RequestParam String id){
        try {
            return iBsTableTypeService.removeByIdtableType(id);
        }catch (Exception e){
            log.error("删除调查分类表类型信息失败", e);
            throw e;
        }
    }
    /*
     * 修改调查分类表类型
     * */
    @PostMapping("/tableTypeEdit")
    public AjaxResult edit(@RequestBody BsTableType bsTableType){

        try {
            log.info("编辑入参调查分类表类型信息：{}", bsTableType);
            BsTableType byId = iBsTableTypeService.getById(bsTableType.getId());
            byId.setTableName(bsTableType.getTableName());
//            UpdateWrapper<BsTableType> bsTableTypeUpdateWrapper = new UpdateWrapper<>();
//            bsTableTypeUpdateWrapper.eq("id",bsTableType.getId()).set("table_name",bsTableType.getTableName());
//            boolean update = iBsTableTypeService.update(bsTableTypeUpdateWrapper);
            boolean update = iBsTableTypeService.updateById(byId);
            return AjaxResult.success(update);
            //级联修改
            //iBsTableTypeService.updateByIdRelevance(bsTableType);
        }catch (Exception e){
            log.error("编辑调查分类表类型信息失败", e);
            throw e;
        }
    }
    /*
     * 调查分类表类型详情
     * */
    @GetMapping("/tableTypeInfo")
    public AjaxResult tableTypeInfo(@RequestParam String id){

        try {
            BsTableType byId = iBsTableTypeService.getById(id);
            return AjaxResult.success(byId);
        }catch (Exception e){
            log.error("编辑调查分类表类型信息失败", e);
            throw e;
        }
    }
}
