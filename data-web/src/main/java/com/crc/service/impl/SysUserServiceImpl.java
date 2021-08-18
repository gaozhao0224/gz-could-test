package com.crc.service.impl;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crc.config.SysUser;
import com.crc.entity.SysUicRole;
import com.crc.mapper.SysUserMapper;
import com.crc.service.ISysUicRoleService;
import com.crc.service.ISysUserService;
import com.crc.util.CryptoUtil;
import com.crc.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author gz
 * @since 2021-04-01
 */
@Service
@Slf4j
@Transactional
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Autowired
    private ISysUicRoleService iSysUicRoleService ;

    @Override
    public IPage<SysUser> getList(IPage<SysUser> page, String itemId, String centreId, String userName) {
        QueryWrapper<SysUser> sysUserQueryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(itemId)) {
            sysUserQueryWrapper.eq("item_id",itemId);
        }
        if (StringUtils.isNotEmpty(centreId)) {
            sysUserQueryWrapper.eq("centre_id",centreId);
        }
        if (StringUtils.isNotEmpty(userName)) {
            sysUserQueryWrapper.like("user_name",userName);
        }
        IPage<SysUser> pageList = super.page(page,sysUserQueryWrapper);
        return pageList;
    }

    @Override
    public boolean userSave(SysUser sysUser) throws Exception {

        sysUser.setUserCode(IdUtil.simpleUUID().toUpperCase());
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        sysUser.setPwdPlaintext(CryptoUtil.encrypt(sysUser.getPassword()));
        String pwd = passwordEncoder.encode(sysUser.getPassword());
        sysUser.setPassword(pwd);
        sysUser.setDelFlag("0");
        sysUser.setStatus("0");
        save(sysUser);
        //新增项目id和中心id
        List<HashMap<String, List>> itemCenter = sysUser.getItemCenter();
        if (itemCenter!=null && !itemCenter.isEmpty()){
            itemCenter.stream().forEach(item->{
                //获取所有项目
                for (String itemId : item.keySet()) {
                    List<String> list = item.get(itemId);
                    //当前项目下的中心
                    for (String cId : list) {
                        SysUicRole sysUicRole = new SysUicRole();
                        sysUicRole.setUserId(sysUser.getId());
                        sysUicRole.setItemId(itemId);
                        sysUicRole.setCentreId(cId);
                        iSysUicRoleService.save(sysUicRole);
                    }
                }
            });
        }
        return true;
    }

    @Override
    public boolean editUser(SysUser sysUser) {
        SysUser dbSysUser = getById(sysUser.getId());
        dbSysUser.setEmail(sysUser.getEmail());
        dbSysUser.setPhonenumber(sysUser.getPhonenumber());
        dbSysUser.setIdCard(sysUser.getIdCard());
//        UpdateWrapper<SysUser> sysUserUpdateWrapper = new UpdateWrapper<>();
//        sysUserUpdateWrapper.eq("id",sysUser.getId()).set("email",sysUser.getEmail())
//                .set("phonenumber",sysUser.getPhonenumber()).set("id_card",sysUser.getIdCard());
//        boolean update = update(sysUserUpdateWrapper);
        boolean update = updateById(dbSysUser);
//        updateById(sysUser);
//        QueryWrapper<SysUicRole> sysUicRoleQueryWrapper = new QueryWrapper<>();
//        sysUicRoleQueryWrapper.eq("user_id",sysUser.getId());
//        iSysUicRoleService.remove(sysUicRoleQueryWrapper);
//        //新增项目id和中心id
//        List<HashMap<String, List>> itemCenter = sysUser.getItemCenter();
//        itemCenter.stream().forEach(item->{
//            //获取所有项目
//            for (String itemId : item.keySet()) {
//                List<String> list = item.get(itemId);
//                //当前项目下的中心
//                for (String cId : list) {
//                    SysUicRole sysUicRole = new SysUicRole();
//                    sysUicRole.setUserId(sysUser.getId());
//                    sysUicRole.setItemId(itemId);
//                    sysUicRole.setCentreId(cId);
//                    iSysUicRoleService.save(sysUicRole);
//                }
//            }
//        });
        return update;
    }
}
