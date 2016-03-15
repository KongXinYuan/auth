package com.xuguruogu.auth.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xuguruogu.auth.dto.CDKeyDTD;
import com.xuguruogu.auth.interceptor.KssException;
import com.xuguruogu.auth.security.AdminUserDetails;
import com.xuguruogu.auth.service.CDKeyManager;
import com.xuguruogu.auth.service.SoftManager;
import com.xuguruogu.auth.web.param.CDKeyAddParam;
import com.xuguruogu.auth.web.param.CDKeySearchParam;
import com.xuguruogu.auth.web.result.SuccessResult;

@Controller
@RequestMapping("/cdkey")
public class CDKeyWebController {

	@Autowired
	private SoftManager softManager;

	@Autowired
	private CDKeyManager cdkeyManager;

	@RequestMapping(value = { "/softs.form" }, method = { RequestMethod.GET })
	public String listform(Model model) {

		model.addAttribute("softs", softManager.listAll());

		return "/cdkey/softs";

	}

	@RequestMapping(value = { "/add" }, method = { RequestMethod.GET })
	public String addlistform(Model model) {

		model.addAttribute("softs", softManager.listAll());

		return "/cdkey/add";

	}

	@RequestMapping(value = { "/add.json" }, method = { RequestMethod.POST })
	public void addlistform(@Valid CDKeyAddParam param, Model model) {

		AdminUserDetails userDetails = (AdminUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();// spring security获取上下文
		long adminid = userDetails.getAdminid();

		long softid = param.getSoftid();
		long keysetid = param.getKeysetid();
		String tag = param.getTag();
		long num = param.getNum();

		List<CDKeyDTD> cdkeys = cdkeyManager.create(softid, adminid, keysetid, tag, num);

		StringBuilder str = new StringBuilder();
		for (CDKeyDTD cdkey : cdkeys) {
			str.append(cdkey.getCdkey()).append("\n");
		}

		model.addAttribute("cdkeys", str);
		model.addAllAttributes(new SuccessResult());
	}

	// 列表
	@RequestMapping(value = { "/list/{id}" }, method = { RequestMethod.GET })
	public String listid(@PathVariable(value = "id") Long softid, CDKeySearchParam param, Model model) {
		if (null == softid) {
			throw new KssException("id为空");
		}

		AdminUserDetails userDetails = (AdminUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();// spring security获取上下文
		long adminid = userDetails.getAdminid();

		model.addAttribute("soft", softManager.detail(softid));
		model.addAttribute("cdkeys", cdkeyManager.search(softid, adminid, param));

		return "/keyset/list";
	}
}
