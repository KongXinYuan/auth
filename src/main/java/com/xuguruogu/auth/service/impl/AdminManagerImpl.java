package com.xuguruogu.auth.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.xuguruogu.auth.dal.daointerface.KssAdminDao;
import com.xuguruogu.auth.dal.daointerface.KssLogLoginDao;
import com.xuguruogu.auth.dal.dataobject.KssAdminDO;
import com.xuguruogu.auth.dal.dataobject.KssLogLoginDO;
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
	private KssLogLoginDao kssLogLoginDao;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Override
	public void onLoginSucess(long adminid, String ip) {
		// 更新最新记录
		int ipint = IPv4Util.ipToIntWithDefault(ip);
		Date now = new Date();
		kssAdminDao.updateLastLogin(adminid, now, ipint);

		// 插入登录日志记录
		KssLogLoginDO logDO = new KssLogLoginDO();
		logDO.setAdminid(adminid);
		logDO.setLoginip(ipint);
		logDO.setLogintime(now);
		kssLogLoginDao.insert(logDO);
	}

	@Override
	public void updatePassword(final long adminId, final String password) {
		kssAdminDao.updatePassword(adminId, bcryptEncoder.encode(password));
	}

	@Override
	public void deleteById(long adminid) {
		kssAdminDao.deleteById(adminid);
	}

	@Override
	public void deleteByIds(List<Long> adminids) {
		kssAdminDao.deleteByIds(adminids);
	}

	@Override
	public void create(long parentid, long level, String username, String password) {
		KssAdminDO kssAdminDO = new KssAdminDO();
		kssAdminDO.setParentid(parentid);
		kssAdminDO.setLevel(level);
		kssAdminDO.setUsername(username);
		kssAdminDO.setPassword(password);
		kssAdminDao.insert(kssAdminDO);
	}

	@Override
	public KssAdminDO queryById(long adminid) {
		return kssAdminDao.selectById(adminid);
	}

	@Override
	public KssAdminDO queryByUsername(String username) {
		KssAdminQueryCondition query = new KssAdminQueryCondition();
		query.putUsername(username);
		return kssAdminDao.selectOneByQueryCondition(query);
	}

	@Override
	public List<KssAdminDO> queryByPage(long parentid, int limit, int pageIndex) {
		KssAdminQueryCondition query = new KssAdminQueryCondition();
		query.putParentid(parentid).pagination(pageIndex, limit);
		return kssAdminDao.selectListByQueryCondition(query);
	}

	@Override
	public long queryCount(long parentid) {
		KssAdminQueryCondition query = new KssAdminQueryCondition();
		query.putParentid(parentid);
		return kssAdminDao.selectCountByQueryCondition(query);
	}

	@Override
	public void updateLock(long adminid, boolean lock) {
		kssAdminDao.updateLock(adminid, lock);
	}

}
