package com.crc.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.crc.config.response.AjaxResult;
import com.crc.entity.BsExamineType;
import com.crc.service.IBsExamineTypeService;
import com.crc.util.CommonNumberUtil;
import com.crc.util.SecurityUtils;
import com.crc.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 调查项种类表 前端控制器
 * </p>
 *
 * @author gz
 * @since 2021-04-23
 */
@RestController
@RequestMapping("/examineType")
@Slf4j
public class BsExamineTypeController extends BaseController{
    @Autowired
    private IBsExamineTypeService iBsExamineTypeService;
    /*
     * 调查项种类列表 超级管理员和录入员
     * */
    @GetMapping("/examineTypeList")
    public AjaxResult list(String itemId,String tableTypeId, String searchName, String type){
        try {
            if(!StringUtils.isEmpty(type) && type.equals(CommonNumberUtil.ONEStr)){
                if(StringUtils.isEmpty(tableTypeId)){
                    return AjaxResult.error("请选择正确的项目表类！");
                }
                QueryWrapper<BsExamineType> bsExamineTypeQueryWrapper = new QueryWrapper<>();
                bsExamineTypeQueryWrapper.eq("table_type_id",tableTypeId);
                List<BsExamineType> list = iBsExamineTypeService.list(bsExamineTypeQueryWrapper);
                log.info("查询调查项种类信息成功：{}", list);
                return AjaxResult.success(list);
            }else {
                IPage<BsExamineType> page = initPage();
                IPage<BsExamineType> list = iBsExamineTypeService.getList(page,itemId,tableTypeId,searchName);
                log.info("查询调查项种类信息成功：{}", list);
                return AjaxResult.success(list);
            }
        }catch (Exception e){
            log.error("查询调查项种类信息失败", e);
            throw e;
        }
    }

    /*
     * 调查项种类列表 核查员
     * */
    @GetMapping("/examineTypeInspectList")
    public AjaxResult inspectList(@RequestParam("subjectId") String subjectId,@RequestParam("tableTypeId") String tableTypeId){
        try {
            List<BsExamineType> list = iBsExamineTypeService.inspectList(subjectId,tableTypeId);
            log.info("查询调查项种类信息成功：{}", list);
            return AjaxResult.success(list);
        }catch (Exception e){
            log.error("查询调查项种类信息失败", e);
            throw e;
        }
    }
    /*
     * 新增调查项种类
     * */
    @PostMapping("/examineTypeSave")
    public AjaxResult save(@RequestBody BsExamineType bsExamineType){

        try {
            log.info("新增入参调查项种类信息：{}，获取用户id：{}", bsExamineType, SecurityUtils.getUserId());
            boolean save = iBsExamineTypeService.save(bsExamineType);
            return AjaxResult.success(save);
        }catch (Exception e){
            log.error("新增调查项种类信息失败", e);
            throw e;
        }
    }
    /*
     * 删除调查项种类
     * */
    @GetMapping("/examineTypeDelete")
    public AjaxResult delete(@RequestParam("id") String id){
        try {
            return iBsExamineTypeService.removeByIdexamineType(id);
        }catch (Exception e){
            log.error("删除调查项种类信息失败", e);
            throw e;
        }
    }
    /*
     * 修改调查项种类 只让修改姓名
     * */
    @PostMapping("/examineTypeEdit")
    public AjaxResult edit(@RequestBody BsExamineType bsExamineType){
        try {
            log.info("编辑入参调查项种类信息：{}", bsExamineType);
            BsExamineType byId = iBsExamineTypeService.getById(bsExamineType.getId());
            byId.setExamineTypeName(bsExamineType.getExamineTypeName());
//            UpdateWrapper<BsExamineType> bsExamineTypeUpdateWrapper = new UpdateWrapper<>();
//            bsExamineTypeUpdateWrapper.eq("id",bsExamineType.getId()).set("examine_type_name",bsExamineType.getExamineTypeName());
//            boolean update = iBsExamineTypeService.update(bsExamineTypeUpdateWrapper);
            boolean update = iBsExamineTypeService.updateById(bsExamineType);
            return AjaxResult.success(update);
        }catch (Exception e){
            log.error("编辑调查项种类信息失败", e);
            throw e;
        }
    }


    /*
     * 调查项种类详情   获取下面所有试题
     * */
    @GetMapping("/examineTypeInfo")
    public AjaxResult examineTypeInfo(@RequestParam String id){

        try {
            BsExamineType byId = iBsExamineTypeService.getById(id);
            return AjaxResult.success(byId);
        }catch (Exception e){
            log.error("编辑调查项种类信息失败", e);
            throw e;
        }
    }
}
