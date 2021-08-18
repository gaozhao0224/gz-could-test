package com.crc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.crc.config.response.AjaxResult;
import com.crc.entity.BsExamineType;
import com.crc.entity.BsExamineType;
import com.crc.entity.CeSubjectTtEt;
import com.crc.entity.SysExamineData;
import com.crc.mapper.BsExamineTypeMapper;
import com.crc.service.IBsExamineTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crc.service.ICeSubjectTtEtService;
import com.crc.service.ISysExamineDataService;
import com.crc.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 调查项种类表 服务实现类
 * </p>
 *
 * @author gz
 * @since 2021-04-23
 */
@Service
public class BsExamineTypeServiceImpl extends ServiceImpl<BsExamineTypeMapper, BsExamineType> implements IBsExamineTypeService {


    @Autowired
    private ISysExamineDataService iSysExamineDataService;

    @Autowired
    private ICeSubjectTtEtService iCeSubjectTtEtService;

    @Override
    public IPage<BsExamineType> getList(IPage<BsExamineType> page, String itemId, String tableTypeId, String searchName) {
        QueryWrapper<BsExamineType> bsSubjectQueryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(itemId)) {
            bsSubjectQueryWrapper.eq("item_id",itemId);
        }
        if (StringUtils.isNotEmpty(tableTypeId)) {
            bsSubjectQueryWrapper.eq("table_type_id",tableTypeId);
        }
        if (StringUtils.isNotEmpty(searchName)) {
            bsSubjectQueryWrapper.and(i->i.like("examine_type_name",searchName));
        }
        IPage<BsExamineType> pageList = super.page(page,bsSubjectQueryWrapper);
        return pageList;
    }


    @Override
    public List<BsExamineType> inspectList(String subjectId, String tableTypeId) {
        QueryWrapper<BsExamineType> bsSubjectQueryWrapper = new QueryWrapper<>();
        bsSubjectQueryWrapper.eq("table_type_id",tableTypeId);
        List<BsExamineType> list = list(bsSubjectQueryWrapper);
//        list.stream().forEach(i->{
//            QueryWrapper<CeSubjectTtEt> ceSubjectTtEtQueryWrapper = new QueryWrapper<>();
//            ceSubjectTtEtQueryWrapper.eq("subject_id",subjectId);
//            ceSubjectTtEtQueryWrapper.eq("examine_type_id",i.getId());
//            CeSubjectTtEt one = iCeSubjectTtEtService.getOne(ceSubjectTtEtQueryWrapper);
//            i.setStatus(one.getStatus());
//            i.setOpinion(one.getOpinion());
//        });

        list = list.stream().map(i -> {
            QueryWrapper<CeSubjectTtEt> ceSubjectTtEtQueryWrapper = new QueryWrapper<>();
            ceSubjectTtEtQueryWrapper.eq("subject_id", subjectId);
            ceSubjectTtEtQueryWrapper.eq("examine_type_id", i.getId());
            CeSubjectTtEt one = iCeSubjectTtEtService.getOne(ceSubjectTtEtQueryWrapper);
            if(one!=null){
                i.setStatus(one.getStatus());
                i.setOpinion(one.getOpinion());
            }
            return i;
        }).collect(Collectors.toList());
        return list;
    }


    @Override
    public AjaxResult removeByIdexamineType(String id) {
        QueryWrapper<SysExamineData> sysExamineDataQueryWrapper = new QueryWrapper<>();
        sysExamineDataQueryWrapper.eq("examine_type_id",id);
        int count = iSysExamineDataService.count(sysExamineDataQueryWrapper);
        if(count>0){
            return AjaxResult.error("该数据下存在调查项，无法删除！");
        }else{
            boolean remove = removeById(id);
            return AjaxResult.success(remove);
        }
    }


}
