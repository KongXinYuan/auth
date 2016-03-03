package com.xuguruogu.auth.api.service;

import java.util.Map;

import com.xuguruogu.auth.config.SoftConfigDO;

/**
 * @author benli.lbl 注册
 *
 */
public class UserRegisterHandler implements RequestHandler {
	private static String name = "register";

	@Override
	public Map<String, Object> doRequest(Map<String, Object> param, SoftConfigDO config) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		return name;
	}

}
