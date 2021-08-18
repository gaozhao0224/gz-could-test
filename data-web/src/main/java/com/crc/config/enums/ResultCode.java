package com.crc.config.enums;

import org.springframework.http.HttpStatus;

/**
 * 定义返回给前端的状态码，为了避免业务异常膨胀，业务异常详细信息不在这里定义
 * gz
 */
public enum ResultCode {

    SUCCESS(HttpStatus.OK.value(), "成功！"),

    BADREQUEST(HttpStatus.BAD_REQUEST.value(), "请求失败！"),

    UNAUTHORIZED(HttpStatus.UNAUTHORIZED.value(), "未登录！"),

    FORBIDDEN(HttpStatus.FORBIDDEN.value(), "无访问权限！"),

    SYSERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), "系统异常，请稍后重试！"),

    BUSIERROR(600, "业务异常！"),

    VERSIONERROR(700, "数据版本异常！");

    private final Integer status;

    private final String message;

    ResultCode(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    public Integer status() {
        return this.status;
    }

    public String message() {
        return this.message;
    }

}
