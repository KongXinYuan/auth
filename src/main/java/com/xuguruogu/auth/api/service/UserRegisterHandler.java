package com.xuguruogu.auth.api.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.xuguruogu.auth.dal.dataobject.KssSoftDO;
import com.xuguruogu.auth.dal.dataobject.KssSoftUserDO;
import com.xuguruogu.auth.interceptor.KssException;
import com.xuguruogu.auth.service.SoftUserManager;
import com.xuguruogu.auth.web.result.SuccessResult;

/**
 * @author benli.lbl 注册
 *
 */
public class UserRegisterHandler implements RequestHandler {
	private static String name = "register";

	@Autowired
	private SoftUserManager softUserManager;

	@Override
	public Map<String, Object> doRequest(Map<String, Object> param, KssSoftDO soft) {

		// 参数校验
		String username = (String) param.get("username");
		if (null == username || username.isEmpty()) {
			throw new KssException("用户名为空");
		}
		if (username.length() < 4) {
			throw new KssException("用户名太短");
		}

		String password = (String) param.get("password");
		if (null == password || password.isEmpty()) {
			throw new KssException("密码为空");
		}

		String cdkey = (String) param.get("cdkey");
		if (null == cdkey || cdkey.isEmpty() || cdkey.length() != 32) {
			throw new KssException("卡密长度32位");
		}

		KssSoftUserDO user = softUserManager.registerWithCdkey(soft.getId(), username, password, cdkey);

		if (null == user) {
			throw new KssException("registerWithCdkey返回用户为空");
		}

		// 返回数据
		Map<String, Object> result = new SuccessResult();
		result.put("endtime", user.getEndtime());
		return result;
	}

	@Override
	public String getName() {
		return name;
	}

}
