package com.common.exception;

/**
 * 把不明确处理的非运行时异常封装为运行时异常
 */
public class RuntimeExceptionWrapper extends RuntimeException {

    private static final long serialVersionUID = -5028648674245119604L;

    public RuntimeExceptionWrapper(String message) {
        super(message);
    }

    public RuntimeExceptionWrapper(Throwable t) {
        super(t);
    }

    public RuntimeExceptionWrapper(String message, Throwable t) {
        super(message, t);
    }

}
