package com.xuguruogu.auth.dal.mybatis;

import com.xuguruogu.auth.interceptor.KssException;

public class KssSqlException extends KssException {

	/**
	 *
	 */
	private static final long serialVersionUID = 663631321963289765L;

	public KssSqlException() {
		super("访问数据库异常");
	}

	public KssSqlException(Throwable cause) {
		super("访问数据库异常", cause);
	}

}
