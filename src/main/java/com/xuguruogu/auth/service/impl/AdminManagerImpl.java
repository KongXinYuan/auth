package com.xuguruogu.auth.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.xuguruogu.auth.dal.daointerface.KssAdminDao;
import com.xuguruogu.auth.dal.dataobject.KssAdminDO;
import com.xuguruogu.auth.dal.querycondition.KssAdminQueryCondition;
import com.xuguruogu.auth.service.AdminManager;
import com.xuguruogu.auth.util.IPv4Util;

/**
 *
 * @author benli.lbl
 * @version $Id: AdminManagerImpl.java, v 0.1 Aug 29, 2015 10:32:47 AM benli.lbl
 *          Exp $
 */
@Component("adminManager")
public class AdminManagerImpl implements AdminManager {

	@Autowired
	private KssAdminDao kssAdminDao;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Override
	public void updatePassword(final Long adminId, final String password) {
		kssAdminDao.updatePassword(adminId, bcryptEncoder.encode(password));
	}

	@Override
	public void deleteById(Long adminid) {
		kssAdminDao.deleteById(adminid);
	}

	@Override
	public void deleteByIds(List<Long> adminids) {
		kssAdminDao.deleteByIds(adminids);
	}

	@Override
	public void create(Long parentid, Integer level, String username, String password) {
		KssAdminDO kssAdminDO = new KssAdminDO();
		kssAdminDO.setParentid(parentid.intValue());
		kssAdminDO.setLevel(level);
		kssAdminDO.setUsername(username);
		kssAdminDO.setPassword(password);
		kssAdminDao.insert(kssAdminDO);
	}

	@Override
	public KssAdminDO queryById(Long adminid) {
		return kssAdminDao.selectById(adminid);
	}

	@Override
	public KssAdminDO queryByUsername(String username) {
		KssAdminQueryCondition query = new KssAdminQueryCondition();
		query.putUsername(username);
		return kssAdminDao.selectOneByQueryCondition(query);
	}

	@Override
	public void updateLastLogin(Long adminid, String ip) {
		kssAdminDao.updateLastLogin(adminid, new Date(), IPv4Util.ipToIntWithDefault(ip));
	}

	@Override
	public List<KssAdminDO> queryByPage(int limit, int pageIndex, Long parentid) {
		KssAdminQueryCondition query = new KssAdminQueryCondition();
		query.putParentid(parentid.intValue()).pagination(pageIndex, limit);
		return kssAdminDao.selectListByQueryCondition(query);
	}

	@Override
	public int queryCount(Long parentid) {
		KssAdminQueryCondition query = new KssAdminQueryCondition();
		query.putParentid(parentid.intValue());
		return kssAdminDao.selectCountByQueryCondition(query);
	}

	@Override
	public void updateLock(Long adminid, boolean lock) {
		kssAdminDao.updateLock(adminid, lock);
	}
}
