package com.xuguruogu.auth.web.controller;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class WebController {
	private static Logger logger = LoggerFactory.getLogger(WebController.class);

	@RequestMapping
	public String index(Model model) {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();// spring
		// security获取上下文

		model.addAttribute("admin", userDetails.getUsername());

		if (logger.isInfoEnabled()) {
			logger.info("visit to index");
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
