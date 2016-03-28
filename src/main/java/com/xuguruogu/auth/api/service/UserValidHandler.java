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
 * @author benli.lbl 校验
 *
 */
@Component("userValidHandler")
public class UserValidHandler implements RequestHandler {
	private static String name = "valid";

	@Autowired
	private SoftUserManager softUserManager;

	@Override
	public Map<String, Object> doRequest(Map<String, String> param, KssSoftDO soft) {

		String username = param.get("username");
		Assert.hasText(username, "用户名为空");

		String linecode = param.get("linecode");
		Assert.hasText(linecode, "运行校验码为空");

		KssSoftUserDO user = softUserManager.valid(soft.getId(), username, linecode);
		Assert.notNull(user, "校验失败");

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
