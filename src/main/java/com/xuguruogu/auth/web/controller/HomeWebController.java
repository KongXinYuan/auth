package com.xuguruogu.auth.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/home")
public class HomeWebController {
	private static Logger logger = LoggerFactory.getLogger(HomeWebController.class);

	@RequestMapping(value = { "/welcome" }, method = { RequestMethod.GET })
	public String welcome() {

		if (logger.isInfoEnabled()) {
			logger.info("visit to home");
		}

		return "/home/welcome";
	}
}
