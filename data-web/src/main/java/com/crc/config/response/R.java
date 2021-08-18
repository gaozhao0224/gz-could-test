package com.crc.config.response;

import java.io.Serializable;

public class R<T> implements Serializable {
    public static final String CODE_SUCCESS = "0000";
    public static final String CODE_FAILURE = "9999";
    private final String code;
    private final String msg;
    private final T resultObject;

    /** @deprecated */
    @Deprecated
    public R() {
        this("9999", "default failure", (T) null);
    }

    public R(String code, String msg, T resultObject) {
        this.code = code;
        this.msg = msg;
        this.resultObject = resultObject;
    }

    public static <T> R<T> success() {
        return new R("0000", "default success", (Object)null);
    }

    public static <T> R<T> success(T resultObject) {
        return new R("0000", "default success", resultObject);
    }

    public static <T> R<T> success(String code, String msg, T resultObject) {
        return new R(code, msg, resultObject);
    }

    public static <T> R<T> failure() {
        return new R("9999", "default failure", (Object)null);
    }

    public static <T> R<T> failure(String msg) {
        return new R("9999", msg, (Object)null);
    }

    public static <T> R<T> failure(String code, String msg) {
        return new R(code, msg, (Object)null);
    }

    public String getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }

    public T getResultObject() {
        return this.resultObject;
    }
}
