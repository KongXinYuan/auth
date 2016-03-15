package com.xuguruogu.auth.web.controller;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xuguruogu.auth.security.AdminUserDetails;
import com.xuguruogu.auth.service.AdminManager;

@Controller
@RequestMapping("/")
public class WebController {
	private static Logger logger = LoggerFactory.getLogger(WebController.class);

	@Autowired
	private AdminManager adminManager;

	@RequestMapping
	public String index(Model model) {

		AdminUserDetails userDetails = (AdminUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();// spring security获取上下文
		long adminid = userDetails.getAdminid();

		model.addAllAttributes(adminManager.profile(adminid));

		if (logger.isInfoEnabled()) {
			logger.info("visit to home");
		}

		return "index";
	}

	@RequestMapping(value = { "/login" }, method = { RequestMethod.GET })
	public String login(Model model, String error) {

		if (StringUtils.isNotBlank(error)) {
			model.addAttribute("error", true);
		}

		if (logger.isInfoEnabled()) {
			logger.info("visit to login");
		}

		return "login";
	}

}
