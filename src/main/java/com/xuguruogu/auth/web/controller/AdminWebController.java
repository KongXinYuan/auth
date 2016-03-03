package com.xuguruogu.auth.web.controller;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.xuguruogu.auth.facade.AdminFacade;
import com.xuguruogu.auth.security.AdminUserDetails;
import com.xuguruogu.auth.web.result.SuccessResult;

@Controller
@RequestMapping("/admin")
public class AdminWebController {
	private static Logger logger = LoggerFactory.getLogger(AdminWebController.class);

	@Autowired
	private AdminFacade adminFacade;

	@RequestMapping(value = { "/detail" }, method = { RequestMethod.GET })
	public String detail(Model model) {

		AdminUserDetails userDetails = (AdminUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();// spring security获取上下文

		model.addAttribute("admin", adminFacade.profile(userDetails.getAdminid()));

		return "/admin/detail";
	}

	@RequestMapping(value = { "/detail/{adminId}" }, method = { RequestMethod.GET })
	public String detailById(Model model, @PathVariable Long adminid) {

		model.addAttribute("admin", adminFacade.profile(adminid));

		return "/admin/detail";
	}

	@RequestMapping(value = { "/loglogin.json" }, method = { RequestMethod.GET })
	public void loglogin(Model model, Long adminid, Long start, int limit, int pageIndex) {

		model.addAllAttributes(adminFacade.querylogLoginByPage(adminid, limit, pageIndex));

	}

	@RequestMapping(value = { "/list" }, method = { RequestMethod.GET })
	public String list() {
		return "/admin/list";
	}

	@RequestMapping(value = { "/page.json" }, method = { RequestMethod.POST })
	public void page(int limit, int pageIndex, Long parentid, Model model) {

		model.addAllAttributes(adminFacade.queryByPage(limit, pageIndex, parentid));
	}

	@RequestMapping(value = { "/doEdit.json" }, method = { RequestMethod.POST })
	public void doEdit(Long id, Boolean lock, Model model) {

		AdminUserDetails userDetails = (AdminUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();// spring security获取上下文

		adminFacade.updateLock(id, lock);

		model.addAllAttributes(new SuccessResult());
	}

	@RequestMapping(value = { "/doAdd.json" }, method = { RequestMethod.POST })
	public void doAdd(Long parentid, Integer level, String username, String password, Model model) {

		adminFacade.create(parentid, level, username, password);

		model.addAllAttributes(new SuccessResult());
	}

	@RequestMapping(value = { "/doDel.json" }, method = { RequestMethod.POST })
	public void doDel(@RequestParam(value = "ids[]") Long[] ids, Model model) {
		adminFacade.deleteByIds(Arrays.asList(ids));
		model.addAllAttributes(new SuccessResult());
	}
}
