package com.xuguruogu.auth.api.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.xuguruogu.auth.dal.dataobject.KssSoftDO;
import com.xuguruogu.auth.dal.dataobject.KssSoftUserDO;
import com.xuguruogu.auth.interceptor.KssException;
import com.xuguruogu.auth.service.SoftUserManager;
import com.xuguruogu.auth.web.result.SuccessResult;

/**
 * @author benli.lbl 校验
 *
 */
public class UserValidHandler implements RequestHandler {
	private static String name = "valid";

	@Autowired
	private SoftUserManager softUserManager;

	@Override
	public Map<String, Object> doRequest(Map<String, Object> param, KssSoftDO soft) {

		String username = (String) param.get("username");
		if (null == username || username.isEmpty()) {
			throw new KssException("用户名为空");
		}

		String linecode = (String) param.get("linecode");
		if (null == linecode || linecode.isEmpty()) {
			throw new KssException("运行校验码为空");
		}

		KssSoftUserDO user = softUserManager.valid(soft.getId(), username, linecode);

		if (null == user) {
			throw new KssException("valid返回用户为空");
		}
		// 返回数据
		Map<String, Object> result = new SuccessResult();
		return result;
	}

	@Override
	public String getName() {
		return name;
	}

}
