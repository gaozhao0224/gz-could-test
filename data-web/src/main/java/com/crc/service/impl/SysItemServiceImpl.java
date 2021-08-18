package com.crc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.crc.config.SysUser;
import com.crc.config.response.AjaxResult;
import com.crc.entity.SysCentre;
import com.crc.entity.SysItem;
import com.crc.entity.SysUicRole;
import com.crc.mapper.SysItemMapper;
import com.crc.service.ISysCentreService;
import com.crc.service.ISysItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crc.service.ISysUicRoleService;
import com.crc.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 项目 服务实现类
 * </p>
 *
 * @author gz
 * @since 2021-04-05
 */
@Service
@Slf4j
@Transactional
public class SysItemServiceImpl extends ServiceImpl<SysItemMapper, SysItem> implements ISysItemService {
    @Autowired
    private ISysCentreService iSysCentreService;
    @Autowired
    private ISysUicRoleService iSysUicRoleService;

    @Override
    public IPage<SysItem> getList(IPage<SysItem> page, String searchName) {
        QueryWrapper<SysItem> sysUserQueryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(searchName)) {
            sysUserQueryWrapper.and(i->i.like("item_name",searchName).or().like("item_code",searchName));
        }
        IPage<SysItem> pageList = super.page(page,sysUserQueryWrapper);
        List<SysItem> collect = pageList.getRecords().stream().map(i -> {
            List<SysCentre> centreList = getCentreList(i.getId());
            i.setCentreList(centreList);
            return i;
        }).collect(Collectors.toList());
        pageList.setRecords(collect);
        return pageList;
    }

    @Override
    public AjaxResult removeByIdItem(String id) {
        QueryWrapper<SysCentre> sysCentreQueryWrapper = new QueryWrapper<>();
        sysCentreQueryWrapper.eq("item_id",id);
        int count = iSysCentreService.count(sysCentreQueryWrapper);
        if(count>0){
            return AjaxResult.error("当前项目下存在中心，无法删除");
        }else {
            boolean b = removeById(id);
            return AjaxResult.success(b);
        }
    }

    @Override
    public List<SysItem> listAll() {
        List<SysItem> collect = list().stream().map(i -> {
            List<SysCentre> centreList = getCentreList(i.getId());
            i.setCentreList(centreList);
            return i;
        }).collect(Collectors.toList());
        return collect;
    }

    @Override
    public SysItem getInfoById(String id) {
        SysItem byId = getById(id);
        byId.setCentreList(getCentreList(id));
        return byId;
    }


    @Override
    public List<SysItem> getUserItem(String userId) {
        List<SysItem> sysItems = new ArrayList<>();
        QueryWrapper<SysUicRole> sysUicRoleQueryWrapper = new QueryWrapper<>();
        sysUicRoleQueryWrapper.eq("user_id",userId);
        List<SysUicRole> list = iSysUicRoleService.list(sysUicRoleQueryWrapper);
        for (SysUicRole sysUicRole : list) {
            SysItem byId = getById(sysUicRole.getItemId());
            sysItems.add(byId);
        }
        return sysItems;
    }

    public List<SysCentre> getCentreList(String itemId){
        QueryWrapper<SysCentre> sysCentreQueryWrapper = new QueryWrapper<>();
        sysCentreQueryWrapper.eq("item_id",itemId);
        List<SysCentre> list = iSysCentreService.list(sysCentreQueryWrapper);
        return list;
    }
}
