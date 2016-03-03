package com.xuguruogu.auth.test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Junit测试基类
 *
 * @author benli.lbl
 * @version $Id: TestBase.java, v 0.1 Jul 28, 2015 8:04:27 PM benli.lbl Exp $
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public abstract class TestBase {

	@Autowired
	protected JdbcTemplate jdbcTemplate;

}
