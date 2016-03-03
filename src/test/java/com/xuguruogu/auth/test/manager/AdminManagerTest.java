package com.xuguruogu.auth.test.manager;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.xuguruogu.auth.service.AdminManager;
import com.xuguruogu.auth.test.TestBase;

public class AdminManagerTest extends TestBase {

	@Autowired
	private AdminManager adminManager;

	@Test
	public void testAdminManager() {

		adminManager.updatePassword(1L, "libenli09");
	}
}