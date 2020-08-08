package com.common.exception;

/**
 * 业务异常，给前端的提示信息
 */
public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = -1779707499883635252L;

	public BusinessException(String message) {
		super(message);
	}

	public BusinessException(String message, Throwable t) {
		super(message, t);
	}

}
