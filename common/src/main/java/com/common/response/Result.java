package com.common.response;

import com.common.enums.ResultCode;
import lombok.Data;

/**
 * 返回给调用者的封装结果
 */
@Data
public class Result {

    private Integer status;

    private String message;

    private Object data;

    /**
     * 构造成功结果
     * @param data
     * @return
     */
    public static Result suc(Object data) {
        return result(ResultCode.SUCCESS.status(), ResultCode.SUCCESS.message(), data);
    }

    /**
     * 根据失败结果构造返回结果
     * @param errorResult
     * @return
     */
    public static Result fail(ErrorResult errorResult) {
        return result(errorResult.getStatus(), errorResult.getMessage(), null);
    }

    /**
     * 快速构造失败的返回结果，消息采用默认消息
     * @param resultCode
     * @return
     */
    public static Result fail(ResultCode resultCode) {
        return result(resultCode.status(), resultCode.message(), null);
    }

    /**
     * 快速构造失败的返回结果，覆盖默认消息
     * @param resultCode
     * @param message
     * @return
     */
    public static Result fail(ResultCode resultCode, String message) {
        return result(resultCode.status(), message, null);
    }

    /**
     * 直接构造返回结果
     * @param status
     * @param message
     * @param data
     * @return
     */
    public static Result result(Integer status, String message, Object data) {
        Result result = new Result();
        result.setStatus(status);
        result.setMessage(message);
        result.setData(data);
        return result;
    }

}
