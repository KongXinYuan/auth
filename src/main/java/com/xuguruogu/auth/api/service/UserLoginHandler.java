package com.xuguruogu.auth.api.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.xuguruogu.auth.dal.dataobject.KssSoftDO;
import com.xuguruogu.auth.dal.dataobject.KssSoftUserDO;
import com.xuguruogu.auth.interceptor.KssException;
import com.xuguruogu.auth.service.SoftUserManager;
import com.xuguruogu.auth.web.result.SuccessResult;

/**
 * @author benli.lbl 登录
 *
 *
 */
public class UserLoginHandler implements RequestHandler {
	private static String name = "login";

	@Autowired
	private SoftUserManager softUserManager;

	@Override
	public Map<String, Object> doRequest(Map<String, Object> param, KssSoftDO soft) {
		// 参数校验
		String username = (String) param.get("username");
		if (null == username || username.isEmpty()) {
			throw new KssException("用户名为空");
		}

		String password = (String) param.get("password");
		if (null == password || password.isEmpty()) {
			throw new KssException("密码为空");
		}

		String pccode = (String) param.get("pccode");
		if (null == pccode || pccode.isEmpty()) {
			throw new KssException("机器码为空");
		}

		String linecode = (String) param.get("linecode");
		if (null == linecode || linecode.isEmpty()) {
			throw new KssException("运行校验码为空");
		}

		// ip地址
		long ip = (long) param.get("ip");

		// 登录
		KssSoftUserDO user = softUserManager.login(soft.getId(), username, password, ip, pccode, linecode);

		if (null == user) {
			throw new KssException("login返回用户为空");
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
