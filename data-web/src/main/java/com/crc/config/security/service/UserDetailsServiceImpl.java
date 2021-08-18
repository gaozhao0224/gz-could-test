package com.crc.config.security.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.crc.config.SysUser;
import com.crc.config.security.LoginUser;
import com.crc.service.ISysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 用户验证处理
 * gz
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService
{
    private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private SysPermissionService permissionService;
    @Autowired
    private ISysUserService iSysUserService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        try {
            QueryWrapper<SysUser> sysUserQueryWrapper = new QueryWrapper<SysUser>();
            sysUserQueryWrapper.eq("user_name",username);
            SysUser user = iSysUserService.getOne(sysUserQueryWrapper);
            log.info("登录用户信息：{}",user);
            log.info("用户信息：{}",createLoginUser(user));
            return createLoginUser(user);
        } catch (Exception e) {
            log.error("获取用户信息失败", e);
            throw e;
        }

    }
    public UserDetails createLoginUser(SysUser user)
    {

        LoginUser loginUser = new LoginUser(user, permissionService.getMenuPermission(user));
        return loginUser;
    }
}
