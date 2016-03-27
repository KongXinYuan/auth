package com.xuguruogu.auth.test.log;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xuguruogu.auth.dal.mybatis.KssAdminDaoImpl;

public class TestLog {
	private static Logger logger = LoggerFactory.getLogger(KssAdminDaoImpl.class);

	@Test
	public void tst() {

		Throwable e = new Exception("eee", new Throwable());
		logger.error("{},{}", 1, 2, e);

		logger.error("{}", e.getCause().getMessage());
	}
}
