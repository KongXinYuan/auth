package com.xuguruogu.auth.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.xuguruogu.auth.dal.daointerface.KssAdminDao;
import com.xuguruogu.auth.dal.daointerface.KssLogLoginDao;
import com.xuguruogu.auth.dal.dataobject.KssAdminDO;
import com.xuguruogu.auth.dal.dataobject.KssLogLoginDO;
import com.xuguruogu.auth.dal.enums.RoleType;
import com.xuguruogu.auth.dal.querycondition.KssAdminQueryCondition;
import com.xuguruogu.auth.dal.querycondition.KssLogLoginQueryCondition;
import com.xuguruogu.auth.dto.AdminDTO;
import com.xuguruogu.auth.dto.LogLoginDTO;
import com.xuguruogu.auth.interceptor.KssException;
import com.xuguruogu.auth.service.AdminManager;
import com.xuguruogu.auth.util.Converter;
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

	@Autowired
	private Converter<KssAdminDO, AdminDTO> adminDTOConverter;

	@Autowired
	private Converter<KssLogLoginDO, LogLoginDTO> logLoginDTOConverter;

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
	public void deleteByIds(long parentid, List<Long> adminids) {
		for (long id : adminids) {
			if (this.isOwner(id)) {
				throw new KssException("不能删除作者账号");
			}
		}

		if (!this.isOwner(parentid)) {
			KssAdminQueryCondition query = new KssAdminQueryCondition();
			query.putParentid(parentid);
			query.putIds(adminids);

			long count = kssAdminDao.selectCountByQueryCondition(query);
			if (count != adminids.size()) {
				throw new KssException("没有权限");
			}
		}

		kssAdminDao.deleteByIds(adminids);
	}

	@Override
	public AdminDTO register(long parentid, long level, String username, String password, BigDecimal money) {
		if (!this.isOwner(parentid) && RoleType.ADMIN.getLevel() == level) {
			throw new KssException("没有权限创建一级代理");
		}
		if (null != this.queryByUsername(username)) {
			throw new KssException("用户名已被使用");
		}
		// 创建
		KssAdminDO kssAdminDO = new KssAdminDO();
		kssAdminDO.setParentid(parentid);
		kssAdminDO.setLevel(level);
		kssAdminDO.setUsername(username);
		kssAdminDO.setPassword(password);
		kssAdminDO.setMoney(money);
		kssAdminDO.setAddtime(new Date());
		kssAdminDO.setLastlogintime(new Date());
		kssAdminDao.insert(kssAdminDO);

		// 找到
		return adminDTOConverter.convert(this.queryByUsername(username));
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
	public void updateLock(long adminid, long lockid, boolean lock) {

		if (this.isOwner(lockid)) {
			throw new KssException("不能锁定作者账号");
		}

		KssAdminDO admin = null;
		if (this.isOwner(adminid)) {
			admin = this.queryById(lockid);
		} else {
			// 查找这个人
			KssAdminQueryCondition query = new KssAdminQueryCondition();
			query.putParentid(adminid);
			admin = kssAdminDao.selectOneByQueryCondition(query);
		}

		if (null == admin) {
			throw new KssException("此人不存在或没有权限");
		}

		kssAdminDao.updateLock(admin.getId(), lock);
	}

	@Override
	public Map<String, Object> list(long parentid, Integer pageNo, Integer pageSize) {

		Map<String, Object> result = new HashMap<String, Object>();

		// 默认值设置
		if (null == pageSize || null == pageNo) {
			pageSize = 20;
			pageNo = 1;
		}

		KssAdminQueryCondition query = new KssAdminQueryCondition();
		if (this.isOwner(parentid)) {
			query.putParentid(parentid);
		}

		result.put("count", kssAdminDao.selectCountByQueryCondition(query));
		query.pagination(pageNo, pageSize);
		List<KssAdminDO> list = kssAdminDao.selectListByQueryCondition(query);
		result.put("results", adminDTOConverter.converter(list));

		return result;
	}

	@Override
	public Map<String, Object> profile(long adminid) {

		Map<String, Object> result = new HashMap<String, Object>();

		result.put("admin", adminDTOConverter.convert(this.queryById(adminid)));

		// 查询最近登录记录
		KssLogLoginQueryCondition query = new KssLogLoginQueryCondition();
		query.putAdminid(adminid).pagination(1, 10).desc(true);
		List<KssLogLoginDO> list = kssLogLoginDao.selectListByQueryCondition(query);
		result.put("loglogin", logLoginDTOConverter.converter(list));

		return result;
	}

	private boolean isOwner(long adminid) {
		return 1 == adminid;
	}

}
