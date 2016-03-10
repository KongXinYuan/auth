package com.xuguruogu.auth.test;

import java.util.UUID;

import org.junit.Test;

public class UUIDTest {

	@Test
	public void TestUUID() {
		UUID uuid = UUID.randomUUID();

		System.out.println(uuid.toString().replace("-", ""));

	}
}
