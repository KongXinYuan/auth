package com.xuguruogu.auth.api.controller;

import java.text.MessageFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xuguruogu.auth.api.service.RequestAdapter;

@Controller
public class UserController {
	private static Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private RequestAdapter requestAdapter;

	@RequestMapping(value = { "/api/v1/user" }, method = { RequestMethod.POST })
	public void doRequest(String req, Model model) {

		String resp = requestAdapter.doRequestAdapt(req);
		model.addAttribute("msg", resp);

		logger.info(MessageFormat.format("request is: {0}\nresposne: {1}", req, resp));
	}

}
