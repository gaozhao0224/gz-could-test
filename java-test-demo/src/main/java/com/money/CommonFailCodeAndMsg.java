package com.money;

import lombok.Getter;

/**
 * @version:0.0.1
 * @author: Lianyutao
 * @create: 2020-08-19 17:35
 * @description:
 **/

@Getter
public enum CommonFailCodeAndMsg {

    FAIL_LOGIN_NOT("100101", "用户未登录"),

    FAIL_LOGIN_EXCEPTION("100102", "登录异常"),

    FAIL_LOGIN_ERROR("100103", "用户名或密码错误"),

    FAIL_LOGIN_USER_NOT_FIND("100104", "用户不存在或用户已冻结"),

    FAIL_LOGIN_ROLE_NOT_FIND("100105", "用户未配置角色"),

    FAIL_LOGIN_USER_FREEZE("100106", "用户已冻结"),

    FAIL_REGISTER_ERROR("100107", "用户注册失败"),

    FAIL_TOKEN_EXPIRED("100108", "token已过期"),

    FAIL_TOKEN_ERROR("100109", "token验证失败"),

    FAIL_TOKEN_NOT_FIND("100110", "token不存在或非法"),

    FAIL_MENU_UNAUTH("100111", "菜单未授权,请联系管理员"),

    FAIL_METHOD_UNAUTH("100112", "方法未授权,请联系管理员"),

    FAIL_PERMISSION_ERROR("100113", "权限验证失败"),

    FAIL_ROLE_EXIST("100114", "角色标识已存在");

    private String failCode;

    private String failMsg;

    CommonFailCodeAndMsg(String failCode, String failMsg) {
        this.failCode = failCode;
        this.failMsg = failMsg;
    }
}
