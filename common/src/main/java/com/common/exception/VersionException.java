package com.common.exception;

/**
 * 乐观锁异常
 */
public class VersionException extends RuntimeException {

	private static final long serialVersionUID = 3857929059081061938L;

	public VersionException(String message) {
		super(message);
	}

	public VersionException(String message, Throwable t) {
		super(message, t);
	}

}
