package com.xuguruogu.auth.web.controller;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xuguruogu.auth.dal.dataobject.KssAdminDO;
import com.xuguruogu.auth.dal.dto.AdminDTO;
import com.xuguruogu.auth.service.AdminManager;
import com.xuguruogu.auth.util.Converter;

@Controller
@RequestMapping("/")
public class WebController {
	private static Logger logger = LoggerFactory.getLogger(WebController.class);

	@Autowired
	private AdminManager adminManager;

	@Autowired
	private Converter<KssAdminDO, AdminDTO> adminDTOConverter;

	@RequestMapping
	public String index(Model model) {

		model.addAttribute("admin", adminDTOConverter.convert(adminManager.detail()));
		model.addAllAttributes(adminManager.listLogLogin(1, 10));

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
