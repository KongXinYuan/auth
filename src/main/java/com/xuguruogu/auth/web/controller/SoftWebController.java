package com.xuguruogu.auth.web.controller;

import java.util.Arrays;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.xuguruogu.auth.dto.SoftDTO;
import com.xuguruogu.auth.service.SoftManager;
import com.xuguruogu.auth.web.param.SoftAddParam;
import com.xuguruogu.auth.web.param.SoftUpdateParam;
import com.xuguruogu.auth.web.result.SuccessResult;

@Controller
@RequestMapping("/soft")
@Secured({ "ROLE_OWNER" })
public class SoftWebController {

	@Autowired
	private SoftManager softManager;

	@RequestMapping(value = { "/list" }, method = { RequestMethod.GET })
	public String list(Integer pageSize, Integer pageNo, Model model) {

		model.addAllAttributes(softManager.list(pageNo, pageSize));

		return "/soft/list";
	}

	@RequestMapping(value = { "/add.json" }, method = { RequestMethod.POST })
	public void add(@Valid SoftAddParam soft, Model model) {

		String softname = soft.getSoftname();
		Long intervaltime = soft.getIntervaltime();
		String clientpubkey = soft.getClientpubkey();
		String serverprivkey = soft.getServerprivkey();

		SoftDTO dto = softManager.create(softname, intervaltime, clientpubkey, serverprivkey);

		model.addAttribute("soft", dto);
		model.addAllAttributes(new SuccessResult());
	}

	// 查询单个
	@RequestMapping(value = { "/detail.json" }, method = { RequestMethod.POST })
	public void detail(@RequestParam(required = true) Long softid, Model model) {

		model.addAttribute("soft", softManager.detail(softid));
		model.addAllAttributes(new SuccessResult());
	}

	// 更新
	@RequestMapping(value = { "/update.json" }, method = { RequestMethod.POST })
	public void update(@Valid SoftUpdateParam soft, Model model) {
		long softid = soft.getSoftid();
		long intervaltime = soft.getIntervaltime();
		String clientpubkey = soft.getClientpubkey();
		String serverprivkey = soft.getServerprivkey();

		model.addAttribute("soft", softManager.update(softid, intervaltime, clientpubkey, serverprivkey));
		model.addAllAttributes(new SuccessResult());
	}

	// 删除
	@RequestMapping(value = { "/del.json" }, method = { RequestMethod.POST })
	public void del(@RequestParam(value = "delids[]", required = true) Long[] delids, Model model) {

		softManager.deleteByIds(Arrays.asList(delids));

		model.addAllAttributes(new SuccessResult());
	}

	// 锁定
	@RequestMapping(value = { "/lock.json" }, method = { RequestMethod.POST })
	public void lock(@RequestParam(required = true) Long softid, Model model) {

		softManager.updateLock(softid, true);

		model.addAllAttributes(new SuccessResult());
	}

	// 解锁
	@RequestMapping(value = { "/unlock.json" }, method = { RequestMethod.POST })
	public void unlock(@RequestParam(required = true) Long softid, Model model) {

		softManager.updateLock(softid, false);

		model.addAllAttributes(new SuccessResult());

	}

}
