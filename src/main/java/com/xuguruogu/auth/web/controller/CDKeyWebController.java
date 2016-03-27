package com.xuguruogu.auth.web.controller;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.xuguruogu.auth.dal.dataobject.KssAdminDO;
import com.xuguruogu.auth.dal.dataobject.KssKeySetDO;
import com.xuguruogu.auth.dal.dataobject.KssSoftDO;
import com.xuguruogu.auth.dal.dataobject.KssSoftKeyDO;
import com.xuguruogu.auth.dal.dto.AdminDTO;
import com.xuguruogu.auth.dal.dto.KeySetDTD;
import com.xuguruogu.auth.dal.dto.KssConverter;
import com.xuguruogu.auth.dal.dto.SoftDTO;
import com.xuguruogu.auth.interceptor.KssException;
import com.xuguruogu.auth.service.AdminManager;
import com.xuguruogu.auth.service.KeySetManager;
import com.xuguruogu.auth.service.SoftKeyManager;
import com.xuguruogu.auth.service.SoftManager;
import com.xuguruogu.auth.util.Converter;
import com.xuguruogu.auth.web.param.CDKeyAddParam;
import com.xuguruogu.auth.web.param.CDKeySearchParam;
import com.xuguruogu.auth.web.result.SuccessResult;

@Controller
@RequestMapping("/cdkey")
public class CDKeyWebController {

	@Autowired
	private SoftManager softManager;

	@Autowired
	private KeySetManager keySetManager;

	@Autowired
	private AdminManager adminManager;

	@Autowired
	private SoftKeyManager softKeyManager;

	@Autowired
	private Converter<KssSoftDO, SoftDTO> softDTOConverter;

	@Autowired
	private Converter<KssKeySetDO, KeySetDTD> keySetDTOConverter;

	@Autowired
	private Converter<KssAdminDO, AdminDTO> adminDTOConverter;

	@Autowired
	private KssConverter kssConverter;

	@RequestMapping(value = { "/add" }, method = { RequestMethod.GET })
	public String addlistform(Model model) {

		model.addAttribute("softs", softDTOConverter.converter(softManager.listAll()));

		return "/cdkey/add";

	}

	@RequestMapping(value = { "/add.json" }, method = { RequestMethod.POST })
	public void add(@Valid CDKeyAddParam param, Model model) {

		long softid = param.getSoftid();
		long keysetid = param.getKeysetid();
		String tag = param.getTag();
		long num = param.getNum();

		List<KssSoftKeyDO> cdkeys = softKeyManager.create(softid, keysetid, tag, num);

		StringBuilder str = new StringBuilder();
		for (KssSoftKeyDO cdkey : cdkeys) {
			str.append(cdkey.getCdkey()).append("\n");
		}

		model.addAttribute("cdkeys", str);
		model.addAllAttributes(new SuccessResult());
	}

	// 列表
	@RequestMapping(value = { "/list/{softid}" }, method = { RequestMethod.GET })
	public String listid(@PathVariable(value = "softid") Long softid, CDKeySearchParam param, Model model) {
		if (null == softid) {
			throw new KssException("id为空");
		}

		model.addAttribute("keysets", keySetDTOConverter.converter(keySetManager.listAll(softid)));
		model.addAttribute("admins", adminDTOConverter.converter(adminManager.listAll()));
		model.addAllAttributes(softKeyManager.search(softid, param));
		model.addAttribute("softid", softid);
		model.addAttribute("search", param);

		return "/cdkey/list";
	}

	// 锁定
	@RequestMapping(value = { "/lock.json" }, method = { RequestMethod.POST })
	public void lock(@RequestParam(required = true) Long softid,
			@RequestParam(value = "lockids[]", required = true) Long[] lockids, Model model) {
		softKeyManager.lockByIds(softid, Arrays.asList(lockids), true);
		model.addAllAttributes(new SuccessResult());
	}

	// 解锁
	@RequestMapping(value = { "/unlock.json" }, method = { RequestMethod.POST })
	public void unlock(@RequestParam(required = true) Long softid,
			@RequestParam(value = "lockids[]", required = true) Long[] lockids, Model model) {

		softKeyManager.lockByIds(softid, Arrays.asList(lockids), false);
		model.addAllAttributes(new SuccessResult());
	}

	// 删除
	@RequestMapping(value = { "/del.json" }, method = { RequestMethod.POST })
	public void del(@RequestParam(required = true) Long softid,
			@RequestParam(value = "delids[]", required = true) Long[] delids, Model model) {

		softKeyManager.deleteByIds(softid, Arrays.asList(delids));

		model.addAllAttributes(new SuccessResult());
	}

	@RequestMapping(value = { "/order" }, method = { RequestMethod.GET })
	public String order(Integer pageNo, Integer pageSize, Model model) {
		model.addAllAttributes(softKeyManager.order(pageNo, pageSize));
		return "/cdkey/order";
	}

	@RequestMapping(value = { "/statistics/{softid}" }, method = { RequestMethod.GET })
	public String statistics(@PathVariable(value = "softid") Long softid, Model model) {
		model.addAttribute("statistics", kssConverter.convert(softKeyManager.statistics(softid)));
		return "/cdkey/statistics";
	}

	@RequestMapping(value = { "/batch" }, method = { RequestMethod.GET })
	public String batch(Model model) {
		return "/cdkey/batch";
	}

}
