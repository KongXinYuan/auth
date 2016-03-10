package com.xuguruogu.auth.security;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.xuguruogu.auth.service.AdminManager;

@Component("adminAuthenticationSuccessHandler")
public class AdminAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	@Autowired
	private AdminManager adminManager;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws ServletException, IOException {
		super.onAuthenticationSuccess(request, response, authentication);

		AdminUserDetails userDetails = (AdminUserDetails) authentication.getPrincipal();

		String ipStr = request.getHeader("x-forwarded-for");
		if (ipStr == null || ipStr.length() == 0 || "unknown".equalsIgnoreCase(ipStr)) {
			ipStr = request.getHeader("PRoxy-Client-IP");
		}
		if (ipStr == null || ipStr.length() == 0 || "unknown".equalsIgnoreCase(ipStr)) {
			ipStr = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ipStr == null || ipStr.length() == 0 || "unknown".equalsIgnoreCase(ipStr)) {
			ipStr = request.getRemoteAddr();
		}

		List<String> ips = Arrays.asList(StringUtils.split(ipStr, ','));

		String ip = null;
		if (null != ips && ips.size() > 0) {
			ip = ips.get(0);

		}

		adminManager.onLoginSucess(userDetails.getAdminid(), ip);
	}
}
