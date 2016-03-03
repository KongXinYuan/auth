package com.xuguruogu.auth.api.service;

import java.util.Map;

import com.xuguruogu.auth.config.SoftConfigDO;

/**
 * @author benli.lbl 校验
 *
 */
public class UserValidHandler implements RequestHandler {
	private static String name = "valid";

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
