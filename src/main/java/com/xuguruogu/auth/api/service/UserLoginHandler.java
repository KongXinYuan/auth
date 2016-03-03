package com.xuguruogu.auth.api.service;

import java.util.Map;

import com.xuguruogu.auth.config.SoftConfigDO;

/**
 * @author benli.lbl 登录
 *
 */
public class UserLoginHandler implements RequestHandler {
	private static String name = "login";

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
