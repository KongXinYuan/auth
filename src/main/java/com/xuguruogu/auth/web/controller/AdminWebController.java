package com.xuguruogu.auth.web.controller;

import java.math.BigDecimal;
import java.util.Arrays;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.xuguruogu.auth.dal.dataobject.KssAdminDO;
import com.xuguruogu.auth.dal.dto.AdminDTO;
import com.xuguruogu.auth.dal.dto.KssConverter;
import com.xuguruogu.auth.service.AdminManager;
import com.xuguruogu.auth.service.KeySetManager;
import com.xuguruogu.auth.service.SoftManager;
import com.xuguruogu.auth.util.Converter;
import com.xuguruogu.auth.web.param.AdminAddParam;
import com.xuguruogu.auth.web.param.PowerAddParam;
import com.xuguruogu.auth.web.param.PowerUpdateParam;
import com.xuguruogu.auth.web.result.SuccessResult;

@Controller
@RequestMapping("/admin")
public class AdminWebController {

	@Autowired
	private AdminManager adminManager;

	@Autowired
	private SoftManager softManager;

	@Autowired
	private KeySetManager keySetManager;

	@Autowired
	private Converter<KssAdminDO, AdminDTO> adminDTOConverter;

	@Autowired
	private KssConverter kssConverter;

	@Secured({ "ROLE_OWNER", "ROLE_ADMIN" })
	@RequestMapping(value = { "/list" }, method = { RequestMethod.GET })
	public String list(Model model) {

		model.addAttribute("admins", adminDTOConverter.converter(adminManager.listAll()));
		model.addAttribute("softs", kssConverter.convert(softManager.listAll()));

		return "/admin/list";
	}

	// 添加
	@Secured({ "ROLE_OWNER", "ROLE_ADMIN" })
	@RequestMapping(value = { "/add.json" }, method = { RequestMethod.POST })
	public void add(@Valid AdminAddParam admin, Model model) {

		model.addAttribute("admin", adminDTOConverter.convert(
				adminManager.register(admin.getRole(), admin.getUsername(), admin.getPassword(), admin.getMoney())));
		model.addAllAttributes(new SuccessResult());
	}

	// 删除单个
	@Secured({ "ROLE_OWNER", "ROLE_ADMIN" })
	@RequestMapping(value = { "/del.json" }, method = { RequestMethod.POST })
	public void del(@RequestParam(value = "delids[]", required = true) Long[] delids, Model model) {

		adminManager.deleteByIds(Arrays.asList(delids));

		model.addAllAttributes(new SuccessResult());
	}

	// 锁定
	@Secured({ "ROLE_OWNER", "ROLE_ADMIN" })
	@RequestMapping(value = { "/lock.json" }, method = { RequestMethod.POST })
	public void lock(@RequestParam(required = true) Long lockid, Model model) {

		adminManager.updateLock(lockid, true);

		model.addAllAttributes(new SuccessResult());
	}

	// 解锁
	@Secured({ "ROLE_OWNER", "ROLE_ADMIN" })
	@RequestMapping(value = { "/unlock.json" }, method = { RequestMethod.POST })
	public void unlock(@RequestParam(required = true) Long lockid, Model model) {

		adminManager.updateLock(lockid, false);

		model.addAllAttributes(new SuccessResult());
	}

	//
	@Secured({ "ROLE_OWNER", "ROLE_ADMIN" })
	@RequestMapping(value = { "/detail.json" }, method = { RequestMethod.POST })
	public void detail(@RequestParam(required = true) Long adminid, Model model) {
		model.addAttribute("admin", kssConverter.convert(adminManager.detail(adminid)));
		model.addAttribute("powers", kssConverter.convert(adminManager.listpower(null, adminid)));
		model.addAllAttributes(new SuccessResult());
	}

	@Secured({ "ROLE_OWNER", "ROLE_ADMIN" })
	@RequestMapping(value = { "/power/list.json" }, method = { RequestMethod.POST })
	public void listkeyset(@RequestParam(required = true) Long softid, Model model) {
		model.addAttribute("powers", kssConverter.convert(keySetManager.listAll(softid)));
		model.addAllAttributes(new SuccessResult());
	}

	@Secured({ "ROLE_OWNER", "ROLE_ADMIN" })
	@RequestMapping(value = { "/power/add.json" }, method = { RequestMethod.POST })
	public void addkeyset(@Valid PowerAddParam param, Model model) {

		long adminid = param.getAdminid();
		long keysetid = param.getKeysetid();
		BigDecimal sellprice = param.getSellprice();

		model.addAttribute("power", kssConverter.convert(adminManager.empower(adminid, keysetid, sellprice)));
		model.addAllAttributes(new SuccessResult());
	}

	@Secured({ "ROLE_OWNER", "ROLE_ADMIN" })
	@RequestMapping(value = { "/power/update.json" }, method = { RequestMethod.POST })
	public void updatekeyset(@Valid PowerUpdateParam param, Model model) {
		long adminid = param.getAdminid();
		long keysetid = param.getKeysetid();
		BigDecimal sellprice = param.getSellprice();

		model.addAttribute("power", kssConverter.convert(adminManager.updatepower(adminid, keysetid, sellprice)));
		model.addAllAttributes(new SuccessResult());
	}

	@Secured({ "ROLE_OWNER", "ROLE_ADMIN" })
	@RequestMapping(value = { "/power/del.json" }, method = { RequestMethod.POST })
	public void delkeyset(@RequestParam(required = true) Long adminid, @RequestParam(required = true) Long keysetid,
			Model model) {

		adminManager.removepower(adminid, keysetid);
		model.addAllAttributes(new SuccessResult());
	}

	@Secured({ "ROLE_OWNER", "ROLE_ADMIN" })
	@RequestMapping(value = { "/updatemoney.json" }, method = { RequestMethod.POST })
	public void updatemoney(@RequestParam(required = true) Long adminid,
			@RequestParam(required = true) BigDecimal money, Model model) {

		adminManager.updateMoney(adminid, money);
		model.addAllAttributes(new SuccessResult());
	}

	@Secured({ "ROLE_OWNER", "ROLE_ADMIN" })
	@RequestMapping(value = { "/updatepassword.json" }, method = { RequestMethod.POST })
	public void updatepassword(@RequestParam(required = true) Long adminid,
			@RequestParam(required = true) String password, Model model) {

		adminManager.updatePwd(adminid, password);
		model.addAllAttributes(new SuccessResult());
	}

	@RequestMapping(value = { "/loglogin" }, method = { RequestMethod.GET })
	public String loglogin(Integer pageNo, Integer pageSize, Model model) {

		model.addAllAttributes(adminManager.listLogLogin(pageNo, pageSize));
		return "/admin/loglogin";
	}

	@RequestMapping(value = { "/finance" }, method = { RequestMethod.GET })
	public String finance(Integer pageNo, Integer pageSize, Model model) {
		model.addAllAttributes(adminManager.listFinance(pageNo, pageSize));

		return "/admin/finance";
	}
}
