package com.xuguruogu.auth.api.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.xuguruogu.auth.dal.dataobject.KssSoftDO;
import com.xuguruogu.auth.dal.dataobject.KssSoftUserDO;
import com.xuguruogu.auth.interceptor.KssException;
import com.xuguruogu.auth.service.SoftUserManager;
import com.xuguruogu.auth.web.result.SuccessResult;

/**
 * @author benli.lbl 充值
 */
public class UserRechargeHandler implements RequestHandler {
	private static String name = "recharge";

	@Autowired
	private SoftUserManager softUserManager;

	@Override
	public Map<String, Object> doRequest(Map<String, Object> param, KssSoftDO soft) {

		// 参数校验
		String username = (String) param.get("username");
		if (null == username || username.isEmpty()) {
			throw new KssException("用户名为空");
		}

		// cdkey
		String cdkey = (String) param.get("cdkey");
		if (null == cdkey || cdkey.isEmpty() || cdkey.length() != 32) {
			throw new KssException("卡密长度32位");
		}

		// 充值
		KssSoftUserDO user = softUserManager.recharge(soft.getId(), username, cdkey);

		if (null == user) {
			throw new KssException("recharge返回用户为空");
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
