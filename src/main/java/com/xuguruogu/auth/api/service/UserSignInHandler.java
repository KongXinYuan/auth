package com.xuguruogu.auth.api.service;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.xuguruogu.auth.dal.dataobject.KssSoftDO;
import com.xuguruogu.auth.dal.dataobject.KssSoftUserDO;
import com.xuguruogu.auth.service.SoftUserManager;
import com.xuguruogu.auth.util.IPv4Util;
import com.xuguruogu.auth.web.result.SuccessResult;

/**
 * @author benli.lbl 登录
 *
 *
 */
@Component("userSignInHandler")
public class UserSignInHandler implements RequestHandler {
	private static String name = "signin";

	@Autowired
	private SoftUserManager softUserManager;

	@Autowired
	private HttpServletRequest request;

	@Override
	public Map<String, Object> doRequest(Map<String, String> param, KssSoftDO soft) {
		// 参数校验
		String username = param.get("username");
		Assert.hasText(username, "用户名为空");

		String password = param.get("password");
		Assert.hasText(password, "密码为空");

		String pccode = param.get("pccode");
		Assert.hasText(pccode, "机器码为空");

		String linecode = param.get("linecode");
		Assert.hasText(linecode, "运行校验码为空");

		// 登录
		KssSoftUserDO user = softUserManager.login(soft.getId(), username, password, getRemoteIp(), pccode, linecode);
		Assert.notNull(user, "登录失败");

		// 返回数据
		Map<String, Object> result = new SuccessResult();
		result.put("remain", user.getEndtime().getTime() - new Date().getTime());
		return result;
	}

	private long getRemoteIp() {

		String ipAddr = request.getHeader("x-forwarded-for");
		if (ipAddr == null || ipAddr.length() == 0 || "unknown".equalsIgnoreCase(ipAddr)) {
			ipAddr = request.getHeader("Proxy-Client-IP");
		}
		if (ipAddr == null || ipAddr.length() == 0 || "unknown".equalsIgnoreCase(ipAddr)) {
			ipAddr = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ipAddr == null || ipAddr.length() == 0 || "unknown".equalsIgnoreCase(ipAddr)) {
			ipAddr = request.getRemoteAddr();
		}
		if (ipAddr == null || ipAddr.length() == 0 || "unknown".equalsIgnoreCase(ipAddr)) {
			ipAddr = request.getHeader("http_client_ip");
		}
		if (ipAddr == null || ipAddr.length() == 0 || "unknown".equalsIgnoreCase(ipAddr)) {
			ipAddr = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		// 如果是多级代理，那么取第一个ip为客户ip
		if (ipAddr != null && ipAddr.indexOf(",") != -1) {
			ipAddr = ipAddr.substring(ipAddr.lastIndexOf(",") + 1, ipAddr.length()).trim();
		}

		return IPv4Util.ipToIntWithDefault(ipAddr);
	}

	@Override
	public String getName() {
		return name;
	}

}
