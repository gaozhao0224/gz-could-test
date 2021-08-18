package com.crc.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.crc.config.SysUser;
import com.crc.config.response.AjaxResult;
import com.crc.config.security.LoginBody;
import com.crc.config.security.service.SysLoginService;
import com.crc.service.ISysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 *  用户管理
 * @author gz
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class SysUserController extends BaseController {

    @Autowired
    private ISysUserService iSysUserService ;

    @Autowired
    private SysLoginService loginService;

    /**
     * 登录方法
     *
     * @param loginBody 登陆信息
     * @return 结果
     */
    @PostMapping("/login")
    public AjaxResult login(@RequestBody LoginBody loginBody) {
        // 生成令牌
        Map<String, Object> resultMap = loginService.login(loginBody.getUsername(), loginBody.getPassword());
        return AjaxResult.success(resultMap);
    }
    /*
     * 用户列表
     * */
    @GetMapping("/userList")
    public AjaxResult list(String itemId, String centreId, String userName){
        try {
            IPage<SysUser> page = initPage();
            IPage<SysUser> list = iSysUserService.getList(page,itemId,centreId,userName);
            log.info("查询用户信息成功：", list);
            return AjaxResult.success(list);
        }catch (Exception e){
            log.error("查询用户信息失败", e);
            throw e;
        }
    }


    /*
    * 新增用户
    * */
    @PostMapping("/userSave")
    public AjaxResult save(@RequestBody SysUser sysUser) throws Exception {

        try {
            log.info("新增入参用户信息：{}", sysUser);
            boolean save = iSysUserService.userSave(sysUser);
            return AjaxResult.success(save);
        }catch (Exception e){
            log.error("新增用户信息失败", e);
            throw e;
        }
    }
    /*
    * 删除用户
    * */
        @GetMapping("/userDelete")
    public AjaxResult delete(@RequestParam String id){
        try {
            boolean del = iSysUserService.removeById(id);
            return AjaxResult.success(del);
        }catch (Exception e){
            log.error("删除用户信息失败", e);
            throw e;
        }
    }
    /*
    * 修改用户
    * */
    @PostMapping("/userEdit")
    public AjaxResult edit(@RequestBody SysUser sysUser){

        try {
            log.info("编辑入参用户信息：{}", sysUser);
            boolean edit = iSysUserService.editUser(sysUser);
            return AjaxResult.success(edit);
        }catch (Exception e){
            log.error("编辑用户信息失败", e);
            throw e;
        }
    }
    /*
    * 用户详情
    * */
    @GetMapping("/userInfo")
    public AjaxResult userInfo(@RequestParam String id){

        try {
            SysUser byId = iSysUserService.getById(id);
            return AjaxResult.success(byId);
        }catch (Exception e){
            log.error("编辑用户信息失败", e);
            throw e;
        }
    }
}
