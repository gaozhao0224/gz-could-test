package com.crc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.crc.config.response.AjaxResult;
import com.crc.entity.*;
import com.crc.entity.BsTableType;
import com.crc.mapper.BsTableTypeMapper;
import com.crc.service.IBsExamineTypeService;
import com.crc.service.IBsTableTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crc.service.ICeSubjectTtEtService;
import com.crc.service.ISysExamineDataService;
import com.crc.util.CommonNumberUtil;
import com.crc.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 受试者和调查项类型表 服务实现类
 * </p>
 *
 * @author gz
 * @since 2021-04-22
 */
@Service
@Transactional
@Slf4j
public class BsTableTypeServiceImpl extends ServiceImpl<BsTableTypeMapper, BsTableType> implements IBsTableTypeService {

    @Autowired
    private ISysExamineDataService iSysExamineDataService;
    @Autowired
    private IBsExamineTypeService iBsExamineTypeService;
    @Autowired
    private ICeSubjectTtEtService iCeSubjectTtEtService;


    @Override
    public IPage<BsTableType> getList(IPage<BsTableType> page, String itemId, String searchName) {
        QueryWrapper<BsTableType> bsTableTypeQueryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(itemId)) {
            bsTableTypeQueryWrapper.eq("item_id",itemId);
        }
        if (StringUtils.isNotEmpty(searchName)) {
            bsTableTypeQueryWrapper.and(i->i.like("table_name",searchName));
        }
        IPage<BsTableType> pageList = super.page(page,bsTableTypeQueryWrapper);
        return pageList;
    }

    @Override
    public AjaxResult removeByIdtableType(String id) {

        QueryWrapper<SysExamineData> sysExamineDataQueryWrapper = new QueryWrapper<>();
        sysExamineDataQueryWrapper.eq("table_type_id",id);
        int count = iSysExamineDataService.count(sysExamineDataQueryWrapper);
        if(count>0){
            return AjaxResult.error("该数据下存在调查项类型，无法删除！");
        }else{
            boolean remove = removeById(id);
            return AjaxResult.success(remove);
        }
    }

    @Override
    public AjaxResult updateByIdRelevance(BsTableType bsTableType) {
        //如果发现项目改变 判断
        BsTableType byId = getById(bsTableType);
        if(byId.getItemId().equals(bsTableType.getItemId())){
            boolean update = updateById(bsTableType);
            return AjaxResult.success(update);
        }else{
            QueryWrapper<SysExamineData> sysExamineDataQueryWrapper = new QueryWrapper<>();
            sysExamineDataQueryWrapper.eq("table_type_id",bsTableType.getId());
            int count = iSysExamineDataService.count(sysExamineDataQueryWrapper);
            if(count>0){
                return AjaxResult.error("该数据下存在调查项，无法编辑项目！");
            }else{
                boolean remove = updateById(bsTableType);
                return AjaxResult.success(remove);
            }
        }
    }


    @Override
    public List<BsTableType> inspectList(String itemId, String subjectId) {

        QueryWrapper<BsTableType> bsTableTypeQueryWrapper = new QueryWrapper<>();
        bsTableTypeQueryWrapper.eq("item_id",itemId);
        List<BsTableType> bsTableTypes = list(bsTableTypeQueryWrapper);
        for (BsTableType bsTableType : bsTableTypes) {
            int flag = 0;
            QueryWrapper<BsExamineType> bsExamineTypeQueryWrapper = new QueryWrapper<>();
            bsExamineTypeQueryWrapper.eq("table_type_id",bsTableType.getId());
            List<BsExamineType> bsExamineTypes = iBsExamineTypeService.list(bsExamineTypeQueryWrapper);
            for (BsExamineType bsExamineType : bsExamineTypes) {
                QueryWrapper<CeSubjectTtEt> ceSubjectTtEtQueryWrapper = new QueryWrapper<>();
                ceSubjectTtEtQueryWrapper.eq("examine_type_id",bsExamineType.getId());
                ceSubjectTtEtQueryWrapper.eq("subject_id",subjectId);
                CeSubjectTtEt one = iCeSubjectTtEtService.getOne(ceSubjectTtEtQueryWrapper);
                if(one!=null && CommonNumberUtil.TWOStr.equals(one.getStatus()) ){
                    log.info("当前受试者：{}的：{}种类审核成功，状态为：{}",subjectId,bsExamineType.getId(),one.getStatus());
                }else {
                    //审核失败
                    flag++;
                    break;
                }
            }
            if(flag==0){
                log.info("当前受试者：{}的：{}表类审核成功",subjectId,bsTableType.getId());
                bsTableType.setStatus(CommonNumberUtil.TWOStr);
            }else {
                log.info("当前受试者：{}的：{}表类审核失败",subjectId,bsTableType.getId());
                bsTableType.setStatus(CommonNumberUtil.TWOStr);
            }
        }
        return bsTableTypes;
    }
}
