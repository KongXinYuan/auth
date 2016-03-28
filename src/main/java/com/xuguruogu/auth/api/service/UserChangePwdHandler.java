package com.xuguruogu.auth.api.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.xuguruogu.auth.dal.dataobject.KssSoftDO;
import com.xuguruogu.auth.service.SoftUserManager;
import com.xuguruogu.auth.web.result.SuccessResult;

/**
 * @author benli.lbl 充值
 */
@Component("userChangePwdHandler")
public class UserChangePwdHandler implements RequestHandler {
	private static String name = "changepwd";

	@Autowired
	private SoftUserManager softUserManager;

	@Override
	public Map<String, Object> doRequest(Map<String, String> param, KssSoftDO soft) {

		// 参数校验
		String username = param.get("username");
		Assert.hasText(username, "用户名为空");

		// password
		String password = param.get("password");
		Assert.hasText(password, "原密码为空");

		// password
		String newPassword = param.get("newPassword");
		Assert.hasText(newPassword, "密码为空");

		softUserManager.changePassword(soft.getId(), username, password, newPassword);

		// 返回数据
		Map<String, Object> result = new SuccessResult();
		return result;
	}

	@Override
	public String getName() {
		return name;
	}

}
