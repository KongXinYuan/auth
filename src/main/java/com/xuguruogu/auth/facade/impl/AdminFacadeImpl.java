package com.xuguruogu.auth.facade.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.xuguruogu.auth.dal.dataobject.KssAdminDO;
import com.xuguruogu.auth.dal.dataobject.KssLogLoginDO;
import com.xuguruogu.auth.dto.AdminDTO;
import com.xuguruogu.auth.dto.LogLoginDTO;
import com.xuguruogu.auth.facade.AdminFacade;
import com.xuguruogu.auth.service.AdminManager;
import com.xuguruogu.auth.service.LogLoginManager;
import com.xuguruogu.auth.util.Converter;

/**
 *
 * @author benli.lbl
 * @version $Id: AdminFacadeImpl.java, v 0.1 Aug 29, 2015 11:49:19 AM benli.lbl
 *          Exp $
 */
@Component("adminFacade")
public class AdminFacadeImpl implements AdminFacade {

	@Autowired
	private AdminManager adminManager;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Autowired
	private Converter<KssAdminDO, AdminDTO> AdminDTOConverter;

	@Autowired
	private Converter<KssLogLoginDO, LogLoginDTO> LogLoginDTOConverter;

	@Autowired
	private LogLoginManager logLoginManager;

	@Override
	public void chagePassword(long adminid, String oldPassword, String newPassword) {

		KssAdminDO kssAdminDO = adminManager.queryById(adminid);

		if (bcryptEncoder.matches(oldPassword, kssAdminDO.getPassword())) {
			throw new IllegalArgumentException("密码校验失败");
		}

		adminManager.updatePassword(adminid, newPassword);
	}

	@Override
	public Map<String, Object> querylogLoginByPage(long adminid, int limit, int pageIndex) {

		Map<String, Object> map = new HashMap<String, Object>();

		List<KssLogLoginDO> list = logLoginManager.queryByPage(adminid, limit, pageIndex);

		map.put("rows", LogLoginDTOConverter.converter(list));
		map.put("results", logLoginManager.queryCount(adminid));
		return map;

	}

	@Override
	public void deleteById(long id) {
		adminManager.deleteById(id);
	}

	@Override
	public void deleteByIds(List<Long> ids) {
		adminManager.deleteByIds(ids);
	}

	@Override
	public AdminDTO create(long parentid, long level, String username, String password) {

		adminManager.create(parentid, level, username, password);
		KssAdminDO kssAdminDO = adminManager.queryByUsername(username);
		return AdminDTOConverter.convert(kssAdminDO);
	}

	@Override
	public AdminDTO profile(long adminId) {

		KssAdminDO kssAdminDO = adminManager.queryById(adminId);
		return AdminDTOConverter.convert(kssAdminDO);
	}

	@Override
	public List<LogLoginDTO> queryLatestLogLogin(long adminid) {

		List<KssLogLoginDO> list = logLoginManager.queryLatestLogLogin(adminid);
		return LogLoginDTOConverter.converter(list);
	}

	@Override
	public void resetPassword(long adminid, String password) {
		adminManager.updatePassword(adminid, password);
	}

	@Override
	public Map<String, Object> queryByPage(long parentid, int limit, int pageIndex) {

		Map<String, Object> map = new HashMap<String, Object>();

		List<KssAdminDO> list = adminManager.queryByPage(parentid, limit, pageIndex);

		map.put("rows", AdminDTOConverter.converter(list));
		map.put("results", adminManager.queryCount(parentid));
		return map;
	}

	@Override
	public void updateLock(long adminId, boolean lock) {
		adminManager.updateLock(adminId, lock);
	}

}
