package com.xuguruogu.auth.web.controller;

import java.util.Arrays;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.xuguruogu.auth.dal.dataobject.KssSoftDO;
import com.xuguruogu.auth.dal.dto.KssConverter;
import com.xuguruogu.auth.dal.dto.SoftDTO;
import com.xuguruogu.auth.interceptor.KssException;
import com.xuguruogu.auth.service.SoftManager;
import com.xuguruogu.auth.util.Converter;
import com.xuguruogu.auth.util.RSAUtils;
import com.xuguruogu.auth.web.param.SoftAddParam;
import com.xuguruogu.auth.web.param.SoftUpdateParam;
import com.xuguruogu.auth.web.result.SuccessResult;

@Controller
@RequestMapping("/soft")
public class SoftWebController {

	@Autowired
	private SoftManager softManager;

	@Autowired
	private Converter<KssSoftDO, SoftDTO> softDTOConverter;

	@Autowired
	private KssConverter kssConverter;

	@Secured({ "ROLE_OWNER" })
	@RequestMapping(value = { "/list" }, method = { RequestMethod.GET })
	public String list(Model model) {
		model.addAttribute("softs", kssConverter.convert(softManager.listAll()));
		return "/soft/list";
	}

	@RequestMapping(value = { "/form" }, method = { RequestMethod.GET })
	public String form(@RequestParam(required = true) String path, @RequestParam(required = true) String second,
			Model model) {

		model.addAttribute("softs", kssConverter.convert(softManager.listAll()));
		model.addAttribute("path", path);
		model.addAttribute("second", second);

		return "/soft/form";
	}

	@Secured({ "ROLE_OWNER" })
	@RequestMapping(value = { "/add.json" }, method = { RequestMethod.POST })
	public void add(@Valid SoftAddParam soft, Model model) {

		String softname = soft.getSoftname();
		Long intervaltime = soft.getIntervaltime();
		String privkey = soft.getPrivkey();

		model.addAttribute("soft", softDTOConverter.convert(softManager.create(softname, intervaltime, privkey)));
		model.addAllAttributes(new SuccessResult());
	}

	// 查询单个
	@Secured({ "ROLE_OWNER" })
	@RequestMapping(value = { "/detail.json" }, method = { RequestMethod.POST })
	public void detail(@RequestParam(required = true) Long softid, Model model) {

		model.addAttribute("soft", softDTOConverter.convert(softManager.detail(softid)));
		model.addAllAttributes(new SuccessResult());
	}

	// 更新
	@Secured({ "ROLE_OWNER" })
	@RequestMapping(value = { "/update.json" }, method = { RequestMethod.POST })
	public void update(@Valid SoftUpdateParam soft, Model model) {
		long softid = soft.getSoftid();
		long intervaltime = soft.getIntervaltime();
		String privkey = soft.getPrivkey();

		model.addAttribute("soft", softDTOConverter.convert(softManager.update(softid, intervaltime, privkey)));
		model.addAllAttributes(new SuccessResult());
	}

	// 删除
	@Secured({ "ROLE_OWNER" })
	@RequestMapping(value = { "/del.json" }, method = { RequestMethod.POST })
	public void del(@RequestParam(value = "delids[]", required = true) Long[] delids, Model model) {

		softManager.deleteByIds(Arrays.asList(delids));

		model.addAllAttributes(new SuccessResult());
	}

	// 锁定
	@Secured({ "ROLE_OWNER" })
	@RequestMapping(value = { "/lock.json" }, method = { RequestMethod.POST })
	public void lock(@RequestParam(required = true) Long softid, Model model) {

		softManager.updateLock(softid, true);

		model.addAllAttributes(new SuccessResult());
	}

	// 解锁
	@Secured({ "ROLE_OWNER" })
	@RequestMapping(value = { "/unlock.json" }, method = { RequestMethod.POST })
	public void unlock(@RequestParam(required = true) Long softid, Model model) {

		softManager.updateLock(softid, false);

		model.addAllAttributes(new SuccessResult());

	}

	@RequestMapping(value = { "/rsakey.json" }, method = { RequestMethod.GET })
	public void genRsaKey(Model model) {

		try {
			Map<String, Object> keyMap = RSAUtils.genKeyPair();
			String pubKey = RSAUtils.getPublicKey(keyMap);
			String privKey = RSAUtils.getPrivateKey(keyMap);
			model.addAttribute("pubKey", pubKey);
			model.addAttribute("privKey", privKey);
		} catch (Exception e) {
			throw new KssException("生成rsa密匙错误" + e.getMessage());
		}
		model.addAllAttributes(new SuccessResult());
	}

}
