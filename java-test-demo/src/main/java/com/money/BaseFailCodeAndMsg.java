package com.money;


import lombok.Getter;

/**
 * @version: 1.0.1
 * @author: zhangsan
 * @date: 2020/05/01 22:33
 * @description: FailCodeAndMsg
 **/
@Getter
public enum BaseFailCodeAndMsg {

    FAIL_DEFAULT("100000", "未知错误"),

    FAIL_PARAMS("100101", "参数为空或类型错误"),

    FAIL_BEAN_COPY_ERROR("100102", "属性复制异常"),

    FAIL_PARAMS_CONVERT("100103", "请求参数转换异常"),

    FAIL_AUTH_ERROR("100104", "服务端鉴权失败"),

    FAIL_AUTH_TOKEN_ERROR("100105", "Token不合法"),

    FAIL_REQUEST_HEADER_ERROR("100106", "请求头设置不完整"),

    FAIL_SDK_ERROR("100107", "SDK响应异常"),

    FAIL_OP("100201", "操作失败"),

    FAIL_ADD("100202", "创建失败"),

    FAIL_SELECT("100203", "查询失败"),

    FAIL_UPDATE("100204", "修改失败"),

    FAIL_DELETE("100205", "删除失败"),

    FAIL_CONNECTION("100206", "连接失败"),

    FAIL_EXIST("100207", "该记录已存在或重复"),

    FAIL_USED("100208", "已使用"),

    FAIL_LOGIN("100209","登录验证异常");


    private String failCode;

    private String failMsg;

    BaseFailCodeAndMsg(String failCode, String failMsg) {
        this.failCode = failCode;
        this.failMsg = failMsg;
    }
}
