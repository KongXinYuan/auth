package com.xuguruogu.auth.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.Assert;

import com.xuguruogu.auth.dal.daointerface.KssSoftKeyDao;
import com.xuguruogu.auth.dal.daointerface.KssSoftUserDao;
import com.xuguruogu.auth.dal.dataobject.KssSoftKeyDO;
import com.xuguruogu.auth.dal.dataobject.KssSoftUserDO;
import com.xuguruogu.auth.dal.dto.KssConverter;
import com.xuguruogu.auth.dal.enums.CDKeyStatusType;
import com.xuguruogu.auth.dal.enums.UserStatusType;
import com.xuguruogu.auth.dal.querycondition.KssSoftKeyQueryCondition;
import com.xuguruogu.auth.dal.querycondition.KssSoftUserQueryCondition;
import com.xuguruogu.auth.security.AdminUserDetails;
import com.xuguruogu.auth.service.SoftUserManager;
import com.xuguruogu.auth.web.param.UserSearchParam;

@Component("softUserManager")
public class SoftUserManagerImpl implements SoftUserManager {

	@Autowired
	private KssSoftUserDao kssSoftUserDao;

	@Autowired
	private KssSoftKeyDao kssSoftKeyDao;

	@Autowired
	private KssConverter kssConverter;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Autowired
	private TransactionTemplate txTemplate;

	// 校验cdkey是否有效
	private KssSoftKeyDO validCdkey(long softid, String cdkey) {
		KssSoftKeyDO softKey = kssSoftKeyDao.selectOneByQueryCondition(softid,
				new KssSoftKeyQueryCondition().putCdkey(cdkey));
		Assert.notNull(cdkey, "卡密不存在");
		Assert.isTrue(CDKeyStatusType.DELETED != softKey.getStatus(), "卡密已删除");
		Assert.isTrue(softKey.getStatus() != CDKeyStatusType.USED && softKey.getUserid() == null, "卡密已使用");
		Assert.isTrue(softKey.getStatus().isActive(), "卡密无效");
		return softKey;
	}

	@Override
	public KssSoftUserDO registerWithCdkey(final long softid, final String username, final String password,
			final String cdkey) {
		return txTemplate.execute(new TransactionCallback<KssSoftUserDO>() {

			@Override
			public KssSoftUserDO doInTransaction(TransactionStatus status) {
				// 校验卡密
				KssSoftKeyDO softKey = validCdkey(softid, cdkey);
				Assert.isNull(kssSoftUserDao.selectOneByQueryCondition(softid,
						new KssSoftUserQueryCondition().putUsername(username)), "用户已存在");

				// 创建
				KssSoftUserDO kssSoftUserDO = new KssSoftUserDO();
				kssSoftUserDO.setSoftid(softid);
				kssSoftUserDO.setAdminid(softKey.getAdminid());
				kssSoftUserDO.setUsername(username);
				kssSoftUserDO.setPassword(bcryptEncoder.encode(password));
				kssSoftUserDO.setAddtime(new Date());
				kssSoftUserDO.setRechargetimes(1);
				kssSoftUserDO
						.setEndtime(DateUtils.addMinutes(new Date(), (int) (softKey.getCday().floatValue() * 60 * 24)));
				kssSoftUserDao.insert(kssSoftUserDO);

				KssSoftUserDO user = kssSoftUserDao.selectOneByQueryCondition(softid,
						new KssSoftUserQueryCondition().putUsername(username));
				Assert.notNull(username, "用户注册失败");

				// 更新softkey标记已用
				kssSoftKeyDao.updateRecharge(softid, softKey.getId(), new Date(), user.getId(), BigDecimal.ZERO,
						softKey.getCday(), CDKeyStatusType.USED);

				return user;
			}
		});
	}

	@Override
	public KssSoftUserDO login(long softid, String username, String password, long ip, String pccode, String linecode) {

		// 检查用户
		KssSoftUserDO user = kssSoftUserDao.selectOneByQueryCondition(softid,
				new KssSoftUserQueryCondition().putUsername(username));
		Assert.notNull(user, "用户不存在");
		// 校验密码
		Assert.isTrue(bcryptEncoder.matches(password, user.getPassword()), "密码错误");
		// 校验锁定
		Assert.isTrue(user.getStatus().isActive(), "用户未激活");
		// 校验期限
		Assert.isTrue(user.getEndtime().after(new Date()), "用户已到期");
		//
		kssSoftUserDao.updateLastLogin(softid, user.getId(), new Date(), ip, pccode, linecode);

		return kssSoftUserDao.selectOneByQueryCondition(softid, new KssSoftUserQueryCondition().putUsername(username));
	}

	@Override
	public KssSoftUserDO recharge(final long softid, final String username, final String cdkey) {
		return txTemplate.execute(new TransactionCallback<KssSoftUserDO>() {

			@Override
			public KssSoftUserDO doInTransaction(TransactionStatus status) {
				// 检查cdkey
				KssSoftKeyDO softKey = validCdkey(softid, cdkey);
				// 检查用户
				KssSoftUserDO user = kssSoftUserDao.selectOneByQueryCondition(softid,
						new KssSoftUserQueryCondition().putUsername(username));
				Assert.notNull(user, "用户不存在");

				BigDecimal oldcday = user.getCday();
				BigDecimal newcday = user.getCday().add(softKey.getCday());
				long userid = user.getId();
				Date endtime = DateUtils.addMinutes(
						user.getEndtime().after(new Date()) ? user.getEndtime() : new Date(),
						(int) (softKey.getCday().floatValue() * 60 * 24));
				// 更新softuser增加时间
				kssSoftUserDao.updateRecharge(softid, userid, newcday, endtime);
				// 更新softkey标记已用
				kssSoftKeyDao.updateRecharge(softid, softKey.getId(), new Date(), user.getId(), oldcday, newcday,
						CDKeyStatusType.USED);

				return kssSoftUserDao.selectOneByQueryCondition(softid,
						new KssSoftUserQueryCondition().putUsername(username));
			}

		});
	}

	@Override
	public KssSoftUserDO valid(long softid, String username, String linecode) {

		// 检查用户
		KssSoftUserDO user = kssSoftUserDao.selectOneByQueryCondition(softid,
				new KssSoftUserQueryCondition().putUsername(username).putStatus(UserStatusType.getNonDelList()));
		Assert.notNull(user, "用户不存在");
		// 校验锁定
		Assert.isTrue(UserStatusType.DELETED != user.getStatus(), "用户已删除");
		Assert.isTrue(UserStatusType.LOCKED != user.getStatus(), "用户已锁定");
		Assert.isTrue(UserStatusType.ACTIVE == user.getStatus(), "用户未激活");
		Assert.isTrue(user.getEndtime().after(new Date()), "用户已到期");
		Assert.isTrue(user.getLinecode().equals(linecode), "用户被挤掉线");
		return user;
	}

	@Override
	public KssSoftUserDO queryBygUserId(long softid, long userid) {

		return kssSoftUserDao.selectById(softid, userid);
	}

	@Override
	public void deleteByIds(long softid, List<Long> userids) {
		AdminUserDetails currentAdmin = (AdminUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();

		List<KssSoftUserDO> users = kssSoftUserDao.selectByIds(softid, userids);
		Assert.notNull(users, "没有有效选择");
		List<Long> todolist = new ArrayList<>();
		for (KssSoftUserDO user : users) {
			// 有效目标检测
			if (user.getStatus().canLock() && user.isOwnedBy(currentAdmin.getId())) {
				todolist.add(user.getId());
			}
		}
		Assert.state(todolist.size() >= 1, "没有有效目标");

		kssSoftUserDao.updateStatusByIds(softid, userids, UserStatusType.DELETED);
	}

	@Override
	public void lockByIds(long softid, List<Long> userids, boolean lock) {
		AdminUserDetails currentAdmin = (AdminUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		List<KssSoftUserDO> users = kssSoftUserDao.selectByIds(softid, userids);
		Assert.notNull(users, "没有有效选择");
		List<Long> todolist = new ArrayList<>();
		for (KssSoftUserDO user : users) {
			// 有效目标检测
			if (user.getStatus().canLock() && user.isOwnedBy(currentAdmin.getId())) {
				todolist.add(user.getId());
			}
		}
		Assert.isTrue(todolist.size() >= 1, "没有有效目标");
		kssSoftUserDao.updateStatusByIds(softid, userids, UserStatusType.fromLock(lock));
	}

	@Override
	public Map<String, Object> search(long softid, UserSearchParam param) {
		AdminUserDetails currentAdmin = (AdminUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();

		KssSoftUserQueryCondition query = new KssSoftUserQueryCondition().putParentid(currentAdmin.getId())
				.putAdminid(param.getAdminid()).putTag(param.getTag()).putUsername(param.getUsername())
				.putStatus(null == param.getStatus() ? UserStatusType.getNonDelList() : param.getStatus())
				.pagination(param.getPageNo(), param.getPageSize());

		Map<String, Object> result = new HashMap<String, Object>();
		result.put("users", kssConverter.convert(kssSoftUserDao.selectListByQueryCondition(softid, query)));
		result.put("count", kssSoftUserDao.selectCountByQueryCondition(softid, query));
		return result;
	}

}
