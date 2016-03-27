package com.xuguruogu.auth.test;

import org.junit.Test;

import com.xuguruogu.auth.dal.enums.RoleType;

public class TestEnum {

	@Test
	public void tst() {
		Enum.valueOf(RoleType.class, "OWNER");

		System.out.println(RoleType.ADMIN.name());

	}

}
