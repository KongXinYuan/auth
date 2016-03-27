package com.xuguruogu.auth.web.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.xuguruogu.auth.dal.dto.KssConverter;
import com.xuguruogu.auth.service.AdminManager;
import com.xuguruogu.auth.service.SoftUserManager;
import com.xuguruogu.auth.web.param.UserSearchParam;
import com.xuguruogu.auth.web.result.SuccessResult;

@Controller
@RequestMapping("/user")
public class UserWebController {

	@Autowired
	private AdminManager adminManager;

	@Autowired
	private SoftUserManager softUserManager;

	@Autowired
	private KssConverter kssConverter;

	// 列表
	@RequestMapping(value = { "/list/{softid}" }, method = { RequestMethod.GET })
	public String listid(@PathVariable(value = "softid") Long softid, UserSearchParam param, Model model) {

		model.addAttribute("admins", kssConverter.convert(adminManager.listAll()));
		model.addAllAttributes(softUserManager.search(softid, param));
		model.addAttribute("softid", softid);
		model.addAttribute("search", param);

		return "/user/list";
	}

	// 锁定
	@RequestMapping(value = { "/lock.json" }, method = { RequestMethod.POST })
	public void lock(@RequestParam(required = true) Long softid,
			@RequestParam(value = "lockids[]", required = true) Long[] lockids, Model model) {
		softUserManager.lockByIds(softid, Arrays.asList(lockids), true);
		model.addAllAttributes(new SuccessResult());
	}

	// 解锁
	@RequestMapping(value = { "/unlock.json" }, method = { RequestMethod.POST })
	public void unlock(@RequestParam(required = true) Long softid,
			@RequestParam(value = "lockids[]", required = true) Long[] lockids, Model model) {

		softUserManager.lockByIds(softid, Arrays.asList(lockids), false);
		model.addAllAttributes(new SuccessResult());
	}

	// 删除
	@RequestMapping(value = { "/del.json" }, method = { RequestMethod.POST })
	public void del(@RequestParam(required = true) Long softid,
			@RequestParam(value = "delids[]", required = true) Long[] delids, Model model) {

		softUserManager.deleteByIds(softid, Arrays.asList(delids));

		model.addAllAttributes(new SuccessResult());
	}

}
