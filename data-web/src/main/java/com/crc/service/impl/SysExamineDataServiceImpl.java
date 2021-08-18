package com.crc.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.crc.config.redis.RedisCache;
import com.crc.config.response.AjaxResult;
import com.crc.entity.CeSubjectExamine;
import com.crc.entity.SysCrcFile;
import com.crc.entity.SysExamineData;
import com.crc.mapper.SysExamineDataMapper;
import com.crc.service.ICeSubjectExamineService;
import com.crc.service.ISysCrcFileService;
import com.crc.service.ISysExamineDataService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crc.util.CommonNumberUtil;
import com.crc.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * <p>
 * 病例审查数据 服务实现类
 * </p>
 *
 * @author gz
 * @since 2021-04-20
 */
@Service
@Transactional
@Slf4j
public class SysExamineDataServiceImpl extends ServiceImpl<SysExamineDataMapper, SysExamineData> implements ISysExamineDataService {

    @Autowired
    private RedisCache redisCache;
    @Autowired
    private ICeSubjectExamineService iCeSubjectExamineService;
    @Autowired
    private ISysCrcFileService iSysCrcFileService;

    @Override
    public IPage<SysExamineData> getList(IPage<SysExamineData> page, String itemId, String tableTypeId, String examineTypeId) {
        QueryWrapper<SysExamineData> sysQueryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(itemId)) {
            sysQueryWrapper.eq("item_id",itemId);
        }
        if (StringUtils.isNotEmpty(itemId)) {
            sysQueryWrapper.eq("table_type_id",tableTypeId);
        }
        if (StringUtils.isNotEmpty(itemId)) {
            sysQueryWrapper.eq("examine_type_id",examineTypeId);
        }
        sysQueryWrapper.eq("ce_level", CommonNumberUtil.ONEStr);
        sysQueryWrapper.orderByAsc("ce_order");
        IPage<SysExamineData> pageList = super.page(page,sysQueryWrapper);
        return pageList;
    }

    @Override
    public AjaxResult removeByIdRelevance(String id) {

        QueryWrapper<SysExamineData> sysExamineDataQueryWrapper = new QueryWrapper<>();
        sysExamineDataQueryWrapper.eq("parent_id",id);
        int count = count(sysExamineDataQueryWrapper);

        QueryWrapper<CeSubjectExamine> bsSubjectExamineQueryWrapper = new QueryWrapper<>();
        bsSubjectExamineQueryWrapper.eq("examine_id",id);
        int countSubject = iCeSubjectExamineService.count(bsSubjectExamineQueryWrapper);
        if(count>0 || countSubject>0){
            return AjaxResult.error("该项目有关联子级或受试者正在使用中，请先进行删除！");
        }else {
            boolean b = removeById(id);
            if (b){
                return AjaxResult.success(b);
            }else {
                return AjaxResult.error("项目不存在或已删除！");
            }

        }
    }

    @Override
    public AjaxResult saveRelevance(List<SysExamineData> sysExamineDatas,String tableTypeId,String examineTypeId,  String subjectId) {

        //先删除所有答案 指定表（页/表一表二）
        for (SysExamineData sysExamineData : sysExamineDatas) {
            recursionFetch(sysExamineData,tableTypeId,examineTypeId,subjectId);
        }
        return AjaxResult.success("保存成功");

    }

    @Override
    public List<SysExamineData> examineSubjectList(@RequestParam("itemId") String itemId,@RequestParam("tableTypeId") String tableTypeId,@RequestParam("examineTypeId") String examineTypeId,@RequestParam("subjectId") String subjectId) {
        QueryWrapper<SysExamineData> sysQueryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(itemId)) {
            sysQueryWrapper.eq("item_id",itemId);
        }
        if (StringUtils.isNotEmpty(itemId)) {
            sysQueryWrapper.eq("table_type_id",tableTypeId);
        }
        if (StringUtils.isNotEmpty(itemId)) {
            sysQueryWrapper.eq("examine_type_id",examineTypeId);
        }
        sysQueryWrapper.eq("ce_level", CommonNumberUtil.ONEStr);
        List<SysExamineData> list = list(sysQueryWrapper);
        list.stream().forEach(i->{
            recursion(i,subjectId);
        });
        return list;
    }

    /*
    * 病例调查项详情
    * */
    @Override
    public SysExamineData getByIdRelevance(String id) {
        SysExamineData byId = getById(id);
        SysExamineData recursion = recursion(byId);
        return recursion;
    }
















    /*
    *
    * 递归获取数据  管理员
    *
    * */
    public SysExamineData recursion(SysExamineData sysExamineData) {
        QueryWrapper<SysExamineData> sysExamineDataQueryWrapper = new QueryWrapper<>();
        sysExamineDataQueryWrapper.eq("parent_id",sysExamineData.getId());
        sysExamineDataQueryWrapper.orderByAsc("ce_order");
        List<SysExamineData> list = list(sysExamineDataQueryWrapper);
        if (list.isEmpty()){
            return sysExamineData;
        }else {
            sysExamineData.setSysExamineDatas(list);
            for (SysExamineData examineData : list) {
                /*//将文件类型的显示地址放入答案中 管理员不需要
                if (CommonNumberUtil.THREEStr.equals(examineData.getType()) || CommonNumberUtil.FOURStr.equals(examineData.getType())||CommonNumberUtil.FIVEStr.equals(examineData.getType())   ){
                    SysCrcFile fileById = iSysCrcFileService.getById(sysExamineData.getAnValue());
                    if(fileById!=null){
                        examineData.setFileUrlValue(fileById.getShowUrl());
                    }
                }*/
                recursion(examineData);
            }
            return sysExamineData;
        }
    }
    /*
    * 递归获取数据  录入人员
    *
    * */
    public SysExamineData recursion(SysExamineData sysExamineData,String subjectId) {

        QueryWrapper<CeSubjectExamine> bsSubjectExamineQueryWrapper = new QueryWrapper<>();
        bsSubjectExamineQueryWrapper.select("an_value","file_url_value","examine_id","subject_id");
        bsSubjectExamineQueryWrapper.eq("examine_id",sysExamineData.getId());
        bsSubjectExamineQueryWrapper.eq("subject_id",subjectId);
        CeSubjectExamine one = iCeSubjectExamineService.getOne(bsSubjectExamineQueryWrapper);
        if(one!=null){
            sysExamineData.setAnValue(one.getAnValue());
            sysExamineData.setFileUrlValue(one.getFileUrlValue());
            log.info("sysExamineData当前层级对象的值：{}；；；；答案对象为：{}",sysExamineData,one);
        }
        QueryWrapper<SysExamineData> sysExamineDataQueryWrapper = new QueryWrapper<>();
        sysExamineDataQueryWrapper.eq("parent_id",sysExamineData.getId());
        sysExamineDataQueryWrapper.orderByAsc("ce_order");
        List<SysExamineData> list = list(sysExamineDataQueryWrapper);
        if (list.isEmpty()){
            return sysExamineData;
        }else {
            sysExamineData.setSysExamineDatas(list);
            for (SysExamineData examineData : list) {
                recursion(examineData,subjectId);
            }
            return sysExamineData;
        }
    }
    /*
     *
     * 递归编辑数据（先删除后新增）
     *
     * */
    public void recursionFetch(SysExamineData sysExamineData,String tableTypeId,String examineTypeId,String subjectId) {

        QueryWrapper<CeSubjectExamine> bsSubjectExamineQueryWrapper = new QueryWrapper<>();
        bsSubjectExamineQueryWrapper.eq("examine_id",sysExamineData.getId());
        bsSubjectExamineQueryWrapper.eq("subject_id",subjectId);
        bsSubjectExamineQueryWrapper.eq("table_type_id",tableTypeId);
        iCeSubjectExamineService.remove(bsSubjectExamineQueryWrapper);

        CeSubjectExamine bsSubjectExamine = new CeSubjectExamine();
        bsSubjectExamine.setExamineId(sysExamineData.getId());
        //将文件类型的显示地址放入答案中
        if (CommonNumberUtil.THREEStr.equals(sysExamineData.getType()) || CommonNumberUtil.FOURStr.equals(sysExamineData.getType())||CommonNumberUtil.FIVEStr.equals(sysExamineData.getType())   ){
            if (StringUtils.isNotEmpty(sysExamineData.getAnValue())){
                String showUrls = "";
                String[] anValueIds = sysExamineData.getAnValue().split(",");
                for (String anValueId : anValueIds) {
                    SysCrcFile fileById = iSysCrcFileService.getById(sysExamineData.getAnValue());
                    if (fileById!=null){
                        showUrls+=","+fileById.getShowUrl();
                    }
                }
                bsSubjectExamine.setFileUrlValue(showUrls.substring(1));
            }
        }
        bsSubjectExamine.setAnValue(sysExamineData.getAnValue());
        bsSubjectExamine.setTableTypeId(tableTypeId);
        bsSubjectExamine.setExamineTypeId(examineTypeId);
        bsSubjectExamine.setSubjectId(subjectId);
        log.info("保存题目和答案传入过来的值：{}",JSONObject.toJSON(sysExamineData).toString());
        log.info("添加答案对象值：{}",JSONObject.toJSON(bsSubjectExamine).toString());
        iCeSubjectExamineService.save(bsSubjectExamine);

        List<SysExamineData> list = sysExamineData.getSysExamineDatas();
        if (list!=null && list.size()>0){
            sysExamineData.setSysExamineDatas(list);
            for (SysExamineData examineData : list) {
                recursionFetch(examineData,tableTypeId,examineTypeId,subjectId);
            }
        }
    }
}
