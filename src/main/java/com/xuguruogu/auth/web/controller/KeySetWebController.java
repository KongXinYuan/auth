package com.xuguruogu.auth.web.controller;

import java.math.BigDecimal;
import java.util.Arrays;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.xuguruogu.auth.dal.dto.KssConverter;
import com.xuguruogu.auth.service.KeySetManager;
import com.xuguruogu.auth.service.SoftManager;
import com.xuguruogu.auth.web.param.KeySetAddParam;
import com.xuguruogu.auth.web.param.KeySetUpdateParam;
import com.xuguruogu.auth.web.result.SuccessResult;

@Controller
@RequestMapping("/keyset")
public class KeySetWebController {

	@Autowired
	private KeySetManager keySetManager;

	@Autowired
	private SoftManager softManager;

	// dto转换
	@Autowired
	private KssConverter kssConverter;

	// 列表
	@RequestMapping(value = { "/list/{id}" }, method = { RequestMethod.GET })
	public String listid(@PathVariable(value = "id") Long softid, Model model) {

		model.addAttribute("soft", kssConverter.convert(softManager.detail(softid)));
		model.addAttribute("keysets", kssConverter.convert(keySetManager.listAll(softid)));
		return "/keyset/list";
	}

	// 更新下拉框
	@RequestMapping(value = { "/option.json" }, method = { RequestMethod.POST })
	public void option(@RequestParam(required = true) Long softid, Model model) {

		model.addAttribute("keysets", kssConverter.convert(keySetManager.listAll(softid)));
		model.addAllAttributes(new SuccessResult());

	}

	@RequestMapping(value = { "/add.json" }, method = { RequestMethod.POST })
	public void add(@Valid KeySetAddParam keyset, Model model) {

		long softid = keyset.getSoftid();
		String keyname = keyset.getKeyname();
		BigDecimal cday = keyset.getCday();
		String prefix = keyset.getPrefix();
		BigDecimal retailprice = keyset.getRetailprice();

		model.addAttribute("keyset",
				kssConverter.convert(keySetManager.create(softid, keyname, cday, prefix, retailprice)));
		model.addAllAttributes(new SuccessResult());
	}

	// 查询单个
	@RequestMapping(value = { "/detail.json" }, method = { RequestMethod.POST })
	public void detail(@RequestParam(required = true) Long keysetid, Model model) {

		model.addAttribute("keyset", kssConverter.convert(keySetManager.detail(keysetid)));
		model.addAllAttributes(new SuccessResult());
	}

	// 更新
	@RequestMapping(value = { "/update.json" }, method = { RequestMethod.POST })
	public void update(@Valid KeySetUpdateParam keyset, Model model) {
		long keysetid = keyset.getKeysetid();
		BigDecimal retailprice = keyset.getRetailprice();
		BigDecimal cday = keyset.getCday();

		model.addAttribute("keyset", kssConverter.convert(keySetManager.update(keysetid, cday, retailprice)));
		model.addAllAttributes(new SuccessResult());
	}

	// 删除
	@RequestMapping(value = { "/del.json" }, method = { RequestMethod.POST })
	public void del(@RequestParam(value = "delids[]", required = true) Long[] delids, Model model) {

		keySetManager.deleteByIds(Arrays.asList(delids));

		model.addAllAttributes(new SuccessResult());
	}

	// 锁定
	@RequestMapping(value = { "/lock.json" }, method = { RequestMethod.POST })
	public void lock(@RequestParam(required = true) Long keysetid, Model model) {

		keySetManager.lockByIds(keysetid, true);

		model.addAllAttributes(new SuccessResult());
	}

	// 解锁
	@RequestMapping(value = { "/unlock.json" }, method = { RequestMethod.POST })
	public void unlock(@RequestParam(required = true) Long keysetid, Model model) {

		keySetManager.lockByIds(keysetid, false);

		model.addAllAttributes(new SuccessResult());

	}

}
