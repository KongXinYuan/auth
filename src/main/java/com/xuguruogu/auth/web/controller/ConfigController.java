package com.xuguruogu.auth.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.xuguruogu.auth.dal.dto.KssConverter;
import com.xuguruogu.auth.service.OptionManager;
import com.xuguruogu.auth.service.SoftManager;

@Controller
@RequestMapping("/config")
@Secured({ "ROLE_OWNER" })
public class ConfigController {

	@Autowired
	private SoftManager softManager;

	@Autowired
	private KssConverter kssConverter;

	@Autowired
	private OptionManager optionManager;

	@RequestMapping(method = { RequestMethod.GET })
	public String sys(Model model) {
		model.addAttribute("softs", kssConverter.convert(softManager.listAll()));
		return "config/index";
	}

	@RequestMapping(value = { "/sys.form" }, method = { RequestMethod.GET })
	public String getsys(Model model) {

		model.addAttribute("ICP", optionManager.get("ICP"));
		model.addAttribute("title", optionManager.get("title"));
		return "config/sys";
	}

	@RequestMapping(method = { RequestMethod.POST })
	public String putsys(String ICP, String title, Model model) {

		optionManager.put("ICP", ICP);
		optionManager.put("title", title);

		return this.sys(model);
	}

	@RequestMapping(value = { "/{softid}" }, method = { RequestMethod.GET })
	public String soft(@PathVariable(value = "softid") Long softid, Model model) {

		model.addAttribute("softs", kssConverter.convert(softManager.listAll()));
		model.addAttribute("softid", softid);
		return "config/index";
	}

	@RequestMapping(value = { "/soft.form" }, method = { RequestMethod.POST })
	public String getsoft(@RequestParam(required = true) Long softid, Model model) {

		model.addAttribute("pubtime", optionManager.getBySoftId(softid, "pubtime"));
		model.addAttribute("softid", softid);
		return "config/soft";
	}

	@RequestMapping(value = { "/{softid}" }, method = { RequestMethod.POST })
	public String putsoft(@PathVariable(value = "softid") Long softid, Long pubtime, Model model) {
		if (null != pubtime) {
			optionManager.putBySoftId(softid, "pubtime", Long.toString(pubtime));
		}
		return this.soft(softid, model);
	}

}
