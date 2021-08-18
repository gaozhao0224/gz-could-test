package com.crc.service.impl;

import com.crc.entity.SysUicRole;
import com.crc.mapper.SysUicRoleMapper;
import com.crc.service.ISysUicRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 用户对应项目中心 服务实现类
 * </p>
 *
 * @author gz
 * @since 2021-04-06
 */
@Service
@Transactional
public class SysUicRoleServiceImpl extends ServiceImpl<SysUicRoleMapper, SysUicRole> implements ISysUicRoleService {

}
