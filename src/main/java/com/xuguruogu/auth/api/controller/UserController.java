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
import com.xuguruogu.auth.config.AuthConfigHolder;
import com.xuguruogu.auth.config.SoftConfigDO;
import com.xuguruogu.auth.interceptor.ApiException;

@Controller
public class UserController {
	private static Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private RequestAdapter requestAdapter;

	@RequestMapping(value = { "/api/v1/user" }, method = { RequestMethod.POST })
	public void doRequest(long softcode, String req, Model model) {
		if (0 == softcode) {
			throw new ApiException("软件编号未注明");
		}

		SoftConfigDO config = AuthConfigHolder.getSoftConfig(softcode);
		if (null == config) {
			throw new ApiException("软件编号不存在");
		}

		String resp = requestAdapter.doRequestAdapt(config, req);
		model.addAttribute("msg", resp);

		logger.info(MessageFormat.format("request is: {0}\nresposne: {1}", req, resp));
	}

}
