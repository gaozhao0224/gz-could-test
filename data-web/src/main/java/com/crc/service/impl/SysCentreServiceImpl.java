package com.crc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.crc.config.response.AjaxResult;
import com.crc.entity.SysCentre;
import com.crc.entity.SysUicRole;
import com.crc.mapper.SysCentreMapper;
import com.crc.service.ISysCentreService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crc.service.ISysUicRoleService;
import com.crc.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 项目 服务实现类
 * </p>
 *
 * @author gz
 * @since 2021-04-06
 */
@Service
@Transactional
public class SysCentreServiceImpl extends ServiceImpl<SysCentreMapper, SysCentre> implements ISysCentreService {
    @Autowired
    private ISysUicRoleService iSysUicRoleService ;

    @Override
    public IPage<SysCentre> getList(IPage<SysCentre> page, String itemId, String searchName) {
        QueryWrapper<SysCentre> sysUserQueryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(itemId)) {
            sysUserQueryWrapper.eq("item_id",itemId);
        }
        if (StringUtils.isNotEmpty(searchName)) {
            sysUserQueryWrapper.and(i->i.like("centre_code",searchName).or().like("centre_name",searchName)) ;
        }
        IPage<SysCentre> pageList = super.page(page,sysUserQueryWrapper);
        return pageList;
    }

    @Override
    public AjaxResult removeByIdCentre(String id) {
        QueryWrapper<SysUicRole> sysUicRoleQueryWrapper = new QueryWrapper<>();
        sysUicRoleQueryWrapper.eq("centre_id",id);
        int count = iSysUicRoleService.count(sysUicRoleQueryWrapper);
        if (count>0){
            return AjaxResult.error("当前中心下存在用户，无法删除");
        }else {
            boolean b = removeById(id);
            return AjaxResult.success(b);
        }
    }
}
