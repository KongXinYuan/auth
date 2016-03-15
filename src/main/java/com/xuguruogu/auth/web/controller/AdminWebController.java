package com.xuguruogu.auth.web.controller;

import java.text.MessageFormat;
import java.util.Arrays;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.xuguruogu.auth.dto.AdminDTO;
import com.xuguruogu.auth.security.AdminUserDetails;
import com.xuguruogu.auth.service.AdminManager;
import com.xuguruogu.auth.web.param.AdminAddParam;
import com.xuguruogu.auth.web.result.SuccessResult;

@Controller
@RequestMapping("/admin")
public class AdminWebController {
	private static Logger logger = LoggerFactory.getLogger(AdminWebController.class);

	@Autowired
	private AdminManager adminManager;

	@Secured({ "ROLE_OWNER", "ROLE_ADMIN" })
	@RequestMapping(value = { "/list" }, method = { RequestMethod.GET })
	public String list(Integer pageSize, Integer pageNo, Model model) {

		AdminUserDetails userDetails = (AdminUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();// spring security获取上下文
		long adminid = userDetails.getAdminid();

		model.addAllAttributes(adminManager.list(adminid, pageNo, pageSize));

		if (logger.isInfoEnabled()) {
			logger.info("visit to admin/list");
			logger.info(MessageFormat.format("visit to admin/list:{0}", userDetails.getUsername()));
		}

		return "/admin/list";
	}

	// 添加
	@Secured({ "ROLE_OWNER", "ROLE_ADMIN" })
	@RequestMapping(value = { "/add.json" }, method = { RequestMethod.POST })
	public void add(@Valid AdminAddParam admin, Model model) {

		AdminUserDetails userDetails = (AdminUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();// spring security获取上下文

		long adminid = userDetails.getAdminid();

		AdminDTO dto = adminManager.register(adminid, admin.getLevel(), admin.getUsername(), admin.getPassword(),
				admin.getMoney());
		model.addAttribute("admin", dto);
		model.addAllAttributes(new SuccessResult());
	}

	// 删除单个
	@Secured({ "ROLE_OWNER", "ROLE_ADMIN" })
	@RequestMapping(value = { "/del.json" }, method = { RequestMethod.POST })
	public void del(@RequestParam(value = "delids[]", required = true) Long[] delids, Model model) {

		AdminUserDetails userDetails = (AdminUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();// spring security获取上下文

		long adminid = userDetails.getAdminid();

		adminManager.deleteByIds(adminid, Arrays.asList(delids));

		model.addAllAttributes(new SuccessResult());
	}

	// 锁定
	@Secured({ "ROLE_OWNER", "ROLE_ADMIN" })
	@RequestMapping(value = { "/lock.json" }, method = { RequestMethod.POST })
	public void lock(@RequestParam(required = true) Long lockid, Model model) {

		AdminUserDetails userDetails = (AdminUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();// spring security获取上下文

		long adminid = userDetails.getAdminid();

		adminManager.updateLock(adminid, lockid, true);

		model.addAllAttributes(new SuccessResult());
	}

	// 解锁
	@Secured({ "ROLE_OWNER", "ROLE_ADMIN" })
	@RequestMapping(value = { "/unlock.json" }, method = { RequestMethod.POST })
	public void unlock(@RequestParam(required = true) Long unlockid, Model model) {

		AdminUserDetails userDetails = (AdminUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();// spring security获取上下文
		long adminid = userDetails.getAdminid();

		adminManager.updateLock(adminid, unlockid, false);

		model.addAllAttributes(new SuccessResult());

	}
}
