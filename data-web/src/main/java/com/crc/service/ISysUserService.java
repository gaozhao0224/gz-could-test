package com.crc.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.crc.config.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author gz
 * @since 2021-04-01
 */
public interface ISysUserService extends IService<SysUser> {

    IPage<SysUser> getList(IPage<SysUser> page, String itemId, String centreId, String userName);

    boolean userSave(SysUser sysUser) throws Exception;

    boolean editUser(SysUser sysUser);
}
