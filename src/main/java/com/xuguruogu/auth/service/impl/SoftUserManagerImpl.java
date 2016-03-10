package com.xuguruogu.auth.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xuguruogu.auth.dal.daointerface.KssSoftKeyDao;
import com.xuguruogu.auth.dal.daointerface.KssSoftRechargeDao;
import com.xuguruogu.auth.dal.daointerface.KssSoftUserDao;
import com.xuguruogu.auth.dal.dataobject.KssSoftKeyDO;
import com.xuguruogu.auth.dal.dataobject.KssSoftRechargeDO;
import com.xuguruogu.auth.dal.dataobject.KssSoftUserDO;
import com.xuguruogu.auth.dal.querycondition.KssSoftKeyQueryCondition;
import com.xuguruogu.auth.dal.querycondition.KssSoftUserQueryCondition;
import com.xuguruogu.auth.interceptor.KssException;
import com.xuguruogu.auth.service.SoftUserManager;

@Component("softUserManager")
public class SoftUserManagerImpl implements SoftUserManager {

	@Autowired
	private KssSoftUserDao kssSoftUserDao;

	@Autowired
	private KssSoftKeyDao kssSoftKeyDao;

	@Autowired
	private KssSoftRechargeDao kssSoftRechargeDao;

	/**
	 * 创建用户
	 *
	 * @param softid
	 * @param adminid
	 * @param username
	 * @param password
	 * @param cday
	 * @return
	 */
	private KssSoftUserDO createUser(long softid, long adminid, String username, String password, BigDecimal cday) {

		// 创建
		KssSoftUserDO kssSoftUserDO = new KssSoftUserDO();
		kssSoftUserDO.setSoftid(softid);
		kssSoftUserDO.setAdminid(adminid);
		kssSoftUserDO.setUsername(username);
		kssSoftUserDO.setPassword(password);
		kssSoftUserDO.setAddtime(new Date());
		kssSoftUserDO.setStarttime(new Date());
		if (cday.equals(BigDecimal.ZERO)) {
			kssSoftUserDO.setEndtime(new Date());
			kssSoftUserDO.setRechargetimes(0);
		} else {
			kssSoftUserDO.setRechargetimes(1);
			kssSoftUserDO.setEndtime(DateUtils.addMinutes(new Date(), (int) (cday.floatValue() * 60 * 24)));
		}
		kssSoftUserDao.insert(kssSoftUserDO);

		return this.queryByUsername(softid, username);
	}

	/**
	 * 校验cdkey是否有效
	 *
	 * @param softid
	 * @param cdkey
	 * @return
	 */
	private KssSoftKeyDO validCdkey(long softid, String cdkey) {

		// 查找卡密
		KssSoftKeyQueryCondition query = new KssSoftKeyQueryCondition();
		String prefix = cdkey.substring(0, 4);
		String keystr = cdkey.substring(4, 10);
		String keypwd = cdkey.substring(10, 32);
		query.putPrefix(prefix);
		query.putCdkey(keystr);
		query.putPassword(keypwd);
		KssSoftKeyDO softKey = kssSoftKeyDao.selectOneByQueryCondition(softid, query);
		if (null == softKey) {
			throw new KssException("卡密无效");
		}
		if (softKey.isLock()) {
			throw new KssException("卡密被锁定");
		}
		return softKey;
	}

	@Override
	public KssSoftUserDO registerWithCdkey(long softid, String username, String password, String cdkey) {
		// 校验卡密
		KssSoftKeyDO softKey = this.validCdkey(softid, cdkey);

		// 校验用户
		if (null != queryByUsername(softid, username)) {
			throw new KssException("用户已存在");
		}

		// 创建用户
		KssSoftUserDO user = this.createUser(softid, softKey.getAdminid(), username, password, softKey.getCday());

		if (null == user) {
			throw new KssException("创建用户失败");
		}

		// 更新softkey标记已用
		kssSoftKeyDao.updateRecharge(softid, softKey.getId(), new Date(), user.getUsername());

		BigDecimal oldcday = BigDecimal.ZERO;
		BigDecimal newcday = user.getCday();

		// 插入softrecharge表
		this.logRecharge(softid, softKey.getAdminid(), cdkey, oldcday, newcday, username);

		//
		return user;
	}

	@Override
	public KssSoftUserDO queryById(long softid, long userid) {
		return kssSoftUserDao.selectById(softid, userid);
	}

	@Override
	public KssSoftUserDO queryByUsername(long softid, String username) {
		KssSoftUserQueryCondition query = new KssSoftUserQueryCondition();
		query.putUsername(username);
		return kssSoftUserDao.selectOneByQueryCondition(softid, query);
	}

	@Override
	public void updatePassword(long softid, long userid, String password) {
		kssSoftUserDao.updatePassword(softid, userid, password);
	}

	@Override
	public KssSoftUserDO login(long softid, String username, String password, long ip, String pccode, String linecode) {

		// 检查用户
		KssSoftUserDO user = queryByUsername(softid, username);
		if (null == user) {
			throw new KssException("用户不存在");
		}
		// 校验密码
		if (!user.getPassword().equals(password)) {
			throw new KssException("密码错误");
		}
		// 校验锁定
		if (user.isLock()) {
			throw new KssException("用户被锁定");
		}
		// 校验期限
		if (user.getEndtime().before(new Date())) {
			throw new KssException("用户已到期");
		}

		//
		kssSoftUserDao.updateLastLogin(softid, user.getId(), new Date(), ip, pccode, linecode);

		return queryByUsername(softid, username);

	}

	/**
	 * 插入recharge表记录
	 *
	 * @param softid
	 * @param adminid
	 * @param cdkey
	 * @param oldcday
	 * @param newcday
	 * @param username
	 */
	private void logRecharge(long softid, long adminid, String cdkey, BigDecimal oldcday, BigDecimal newcday,
			String username) {

		KssSoftRechargeDO rechargeDO = new KssSoftRechargeDO();
		rechargeDO.setSoftid(softid);
		rechargeDO.setAdminid(adminid);
		rechargeDO.setCdkey(cdkey);
		rechargeDO.setNewcday(newcday);
		rechargeDO.setOldcday(oldcday);
		rechargeDO.setUsername(username);
		kssSoftRechargeDao.insert(rechargeDO);
	}

	@Override
	public KssSoftUserDO recharge(long softid, String username, String cdkey) {
		// 检查cdkey
		KssSoftKeyDO softKey = this.validCdkey(softid, cdkey);

		// 检查用户
		KssSoftUserDO user = queryByUsername(softid, username);
		if (null == user) {
			throw new KssException("用户不存在");
		}

		BigDecimal oldcday = user.getCday();
		BigDecimal newcday = user.getCday().add(softKey.getCday());
		long userid = user.getId();
		Date starttime = null, endtime = null;

		if (user.getEndtime().after(new Date())) {
			starttime = new Date();
			endtime = DateUtils.addMinutes(new Date(), (int) (softKey.getCday().floatValue() * 60 * 24));
		} else {
			starttime = user.getStarttime();
			endtime = DateUtils.addMinutes(user.getEndtime(), (int) (softKey.getCday().floatValue() * 60 * 24));
		}

		// 此处应有事务。。。应该有。。。
		// 更新softkey标记已用
		kssSoftKeyDao.updateRecharge(softid, softKey.getId(), new Date(), user.getUsername());

		// 更新softuser增加时间
		kssSoftUserDao.updateRecharge(softid, userid, newcday, starttime, endtime);

		// 插入softrecharge表
		this.logRecharge(softid, softKey.getAdminid(), cdkey, oldcday, newcday, username);

		return this.queryByUsername(softid, username);
	}

	@Override
	public KssSoftUserDO valid(long softid, String username, String linecode) {

		// 检查用户
		KssSoftUserDO user = queryByUsername(softid, username);
		if (null == user) {
			throw new KssException("用户不存在");
		}
		// 校验锁定
		if (user.isLock()) {
			throw new KssException("用户被锁定");
		}
		// 校验期限
		if (user.getEndtime().before(new Date())) {
			throw new KssException("用户已到期");
		}
		// 掉线
		if (!user.getLinecode().equals(linecode)) {
			throw new KssException("用户被挤掉线");
		}
		return user;
	}

	@Override
	public void updateLock(long softid, long userid, boolean islock, BigDecimal cday) {
		kssSoftUserDao.updateLock(softid, userid, islock,
				DateUtils.addMinutes(new Date(), (int) (cday.floatValue() * 60 * 24)));
	}

	@Override
	public void updatePublic(long softid, long userid, boolean ispublic) {
		kssSoftUserDao.updatePublic(softid, userid, ispublic);
	}

	@Override
	public List<KssSoftUserDO> queryByPage(long softid, long adminid, int limit, int pageIndex) {

		KssSoftUserQueryCondition query = new KssSoftUserQueryCondition();
		query.putAdminid(adminid).pagination(pageIndex, limit);
		return kssSoftUserDao.selectListByQueryCondition(softid, query);
	}

	@Override
	public long queryCount(long softid, long adminid) {
		KssSoftUserQueryCondition query = new KssSoftUserQueryCondition();
		query.putAdminid(adminid);
		return kssSoftUserDao.selectCountByQueryCondition(softid, query);
	}

	@Override
	public void deleteById(long softid, long userid) {
		kssSoftUserDao.deleteById(softid, userid);
	}

	@Override
	public void deleteByIds(long softid, List<Long> userids) {
		kssSoftUserDao.deleteByIds(softid, userids);
	}

}
