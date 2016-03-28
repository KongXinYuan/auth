package com.xuguruogu.auth.api.service;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.xuguruogu.auth.dal.dataobject.KssSoftDO;
import com.xuguruogu.auth.dal.dataobject.KssSoftUserDO;
import com.xuguruogu.auth.service.SoftUserManager;
import com.xuguruogu.auth.web.result.SuccessResult;

/**
 * @author benli.lbl 注册
 *
 */
@Component("userSignUpHandler")
public class UserSignUpHandler implements RequestHandler {
	private static String name = "signup";

	@Autowired
	private SoftUserManager softUserManager;

	@Override
	public Map<String, Object> doRequest(Map<String, String> param, KssSoftDO soft) {

		// 参数校验
		String username = param.get("username");
		Assert.hasText(username, "用户名为空");
		Assert.isTrue(username.length() >= 4, "用户名太短");

		String password = param.get("password");
		Assert.hasText(password, "密码为空");

		String cdkey = param.get("cdkey");
		Assert.hasText(cdkey, "卡密为空");
		Assert.isTrue(cdkey.length() == 32, "卡密长度为32位");

		KssSoftUserDO user = softUserManager.registerWithCdkey(soft.getId(), username, password, cdkey);
		Assert.notNull(user, "注册失败");

		// 返回数据
		Map<String, Object> result = new SuccessResult();
		result.put("remain", user.getEndtime().getTime() - new Date().getTime());
		return result;
	}

	@Override
	public String getName() {
		return name;
	}

}
