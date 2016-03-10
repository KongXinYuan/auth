package com.xuguruogu.auth.api.controller;

import java.text.MessageFormat;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xuguruogu.auth.api.service.RequestAdapter;
import com.xuguruogu.auth.interceptor.KssException;
import com.xuguruogu.auth.util.IPv4Util;

@Controller
public class UserController {
	private static Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private RequestAdapter requestAdapter;

	@RequestMapping(value = { "/api/v1/user" }, method = { RequestMethod.POST })
	public void doRequest(Long softcode, String req, HttpServletRequest request, Model model) {

		if (null == softcode || softcode.equals(0L)) {
			throw new KssException("软件编号未注明");
		}

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

		long ip = IPv4Util.ipToIntWithDefault(ipAddr);

		String resp = requestAdapter.doRequestAdapt(softcode, ip, req);
		model.addAttribute("msg", resp);

		logger.info(MessageFormat.format("request is: {0}\nresposne: {1}", req, resp));
	}

}
