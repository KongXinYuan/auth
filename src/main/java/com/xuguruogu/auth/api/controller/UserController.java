package com.xuguruogu.auth.api.controller;

import java.text.MessageFormat;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xuguruogu.auth.api.param.ReqParam;
import com.xuguruogu.auth.api.service.RequestAdapter;
import com.xuguruogu.auth.web.result.SuccessResult;

@Controller
@RequestMapping(value = { "/api/v1/user.json" })
public class UserController {
	private static Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private RequestAdapter requestAdapter;

	@RequestMapping(method = { RequestMethod.POST })
	public void doRequest(@Valid ReqParam param, Model model) {

		String resp = requestAdapter.doRequestAdapt(param);
		model.addAttribute("msg", resp);
		model.addAllAttributes(new SuccessResult());

		logger.info(MessageFormat.format("request is: {0}\nresposne: {1}", param.getReq(), resp));
	}

}
