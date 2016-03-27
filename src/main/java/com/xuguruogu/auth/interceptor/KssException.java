package com.xuguruogu.auth.interceptor;

public class KssException extends RuntimeException {

	/**  */
	private static final long serialVersionUID = 3456375511900395518L;

	public KssException(String message) {
		super(message);
	}

	public KssException(String message, Throwable cause) {
		super(message, cause);
	}

	public KssException(Throwable cause) {
		super(cause);
	}
}
