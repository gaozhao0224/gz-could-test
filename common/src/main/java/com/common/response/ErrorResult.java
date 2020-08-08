package com.common.response;

import com.common.enums.ResultCode;
import lombok.Data;

/**
 * 返回给调用者的失败结果，需转换为
 * @see Result
 */
@Data
public class ErrorResult {

    private Integer status;

    private String message;

    private Throwable t;

    /**
     * 构造失败结果，消息采用默认消息
     * @param resultCode
     * @param t
     * @return
     */
    public static ErrorResult fail(ResultCode resultCode, Throwable t) {
        return fail(resultCode.status(), resultCode.message(), t);
    }

    /**
     * 构造失败结果，覆盖默认消息
     * @param resultCode
     * @param message
     * @param t
     * @return
     */
    public static ErrorResult fail(ResultCode resultCode, String message, Throwable t) {
        return fail(resultCode.status(), message, t);
    }

    /**
     * 直接构造失败结果
     * @param status
     * @param message
     * @param t
     * @return
     */
    public static ErrorResult fail(Integer status, String message, Throwable t) {
        ErrorResult errorResult = new ErrorResult();
        errorResult.setStatus(status);
        errorResult.setMessage(message);
        errorResult.setT(t);
        return errorResult;
    }

}
