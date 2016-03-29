package com.xuguruogu.auth.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.Assert;

import com.xuguruogu.auth.dal.daointerface.KssAdminDao;
import com.xuguruogu.auth.dal.daointerface.KssFinanceDao;
import com.xuguruogu.auth.dal.daointerface.KssKeySetDao;
import com.xuguruogu.auth.dal.daointerface.KssLogLoginDao;
import com.xuguruogu.auth.dal.daointerface.KssPowerDao;
import com.xuguruogu.auth.dal.dataobject.KssAdminDO;
import com.xuguruogu.auth.dal.dataobject.KssFinanceDO;
import com.xuguruogu.auth.dal.dataobject.KssKeySetDO;
import com.xuguruogu.auth.dal.dataobject.KssLogLoginDO;
import com.xuguruogu.auth.dal.dataobject.KssPowerDO;
import com.xuguruogu.auth.dal.dto.KssConverter;
import com.xuguruogu.auth.dal.enums.AdminStatusType;
import com.xuguruogu.auth.dal.enums.RoleType;
import com.xuguruogu.auth.dal.querycondition.KssAdminQueryCondition;
import com.xuguruogu.auth.dal.querycondition.KssFinanceQueryCondition;
import com.xuguruogu.auth.dal.querycondition.KssLogLoginQueryCondition;
import com.xuguruogu.auth.dal.querycondition.KssPowerQueryCondition;
import com.xuguruogu.auth.interceptor.KssException;
import com.xuguruogu.auth.security.AdminUserDetails;
import com.xuguruogu.auth.service.AdminManager;
import com.xuguruogu.auth.util.IPv4Util;

/**
 *
 * @author benli.lbl
 * @version $Id: AdminManagerImpl.java, v 0.1 Aug 29, 2015 10:32:47 AM benli.lbl
 *          Exp $
 */
@Component("adminManager")
@Secured({ "ROLE_OWNER", "ROLE_ADMIN", "ROLE_SELLER" })
public class AdminManagerImpl implements AdminManager {

	@Autowired
	private KssAdminDao kssAdminDao;
	@Autowired
	private KssLogLoginDao kssLogLoginDao;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Autowired
	private KssKeySetDao kssKeySetDao;

	@Autowired
	private KssFinanceDao kssFinanceDao;

	@Autowired
	private KssPowerDao kssPowerDao;
	@Autowired
	private KssConverter kssConverter;

	@Autowired
	private TransactionTemplate txTemplate;

	@Override
	public void onLoginSucess(String ip) {
		AdminUserDetails currentAdmin = (AdminUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();

		// 更新最新记录
		long ipint = IPv4Util.ipToLongWithDefault(ip);
		Date now = new Date();
		kssAdminDao.updateLastLogin(currentAdmin.getId(), now, ipint);

		// 插入登录日志记录
		KssLogLoginDO logDO = new KssLogLoginDO();
		logDO.setAdminid(currentAdmin.getId());
		logDO.setLoginip(ipint);
		logDO.setLogintime(now);
		kssLogLoginDao.insert(logDO);
	}

	@Override
	public void deleteByIds(final List<Long> adminids) {
		AdminUserDetails currentAdmin = (AdminUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();

		List<KssAdminDO> list = kssAdminDao.selectByIds(adminids);
		Assert.state(list.size() == adminids.size(), "部分管理员不存在");
		for (KssAdminDO admin : list) {
			Assert.isTrue(admin.isOwnedBy(currentAdmin.getId()), "没有权限");
		}

		// 事务
		txTemplate.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				kssAdminDao.updateStatusByIds(adminids, AdminStatusType.DELETED);
				kssPowerDao.deleteByAdminIds(adminids);
			}
		});

	}

	@Override
	public KssAdminDO register(RoleType role, String username, String password, BigDecimal money) {
		AdminUserDetails currentAdmin = (AdminUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();

		Assert.state(role.getLevel() > currentAdmin.getRole().getLevel(), "没有权限");
		Assert.isNull(kssAdminDao.selectOneByQueryCondition(new KssAdminQueryCondition().putUsername(username)),
				"用户名已被使用");
		// 创建
		KssAdminDO kssAdminDO = new KssAdminDO();
		kssAdminDO.setParentid(currentAdmin.getId());
		kssAdminDO.setRole(role);
		kssAdminDO.setUsername(username);
		kssAdminDO.setPassword(bcryptEncoder.encode(password));
		kssAdminDO.setMoney(money);
		kssAdminDO.setAddtime(new Date());
		kssAdminDO.setLastlogintime(new Date());
		kssAdminDao.insert(kssAdminDO);

		// 找到
		KssAdminDO admin = kssAdminDao.selectOneByQueryCondition(new KssAdminQueryCondition().putUsername(username));
		Assert.notNull(admin, "创建失败");
		return admin;
	}

	@Override
	public KssAdminDO detail(long adminid) {
		KssAdminDO admin = kssAdminDao.selectById(adminid);
		Assert.notNull(admin, "管理员不存在");
		Assert.isTrue(admin.getStatus() != AdminStatusType.DELETED, "用户已删除");

		AdminUserDetails currentAdmin = (AdminUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		Assert.isTrue(admin.isOwnedBy(currentAdmin.getId()), "没有权限");

		return admin;
	}

	@Override
	public KssAdminDO detail() {
		AdminUserDetails currentAdmin = (AdminUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();

		return kssAdminDao.selectById(currentAdmin.getId());
	}

	@Override
	public void updateLock(long adminid, boolean lock) {
		AdminUserDetails currentAdmin = (AdminUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();

		KssAdminDO admin = kssAdminDao.selectById(adminid);
		Assert.notNull(admin, "管理员不存在");
		Assert.isTrue(admin.getRole() != RoleType.OWNER, "不能锁定作者账号");
		Assert.isTrue(admin.isOwnedBy(currentAdmin.getId()), "没有权限");
		Assert.isTrue(admin.getStatus().canLock(), "无法操作");
		kssAdminDao.updateStatusById(adminid, true == lock ? AdminStatusType.LOCKED : AdminStatusType.ACTIVE);
	}

	@Override
	public List<KssAdminDO> listAll() {
		AdminUserDetails currentAdmin = (AdminUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		return kssAdminDao.selectListByQueryCondition(new KssAdminQueryCondition().putAdminid(currentAdmin.getId())
				.putStatus(AdminStatusType.getNonDelList()));
	}

	@Override
	public Map<String, Object> listLogLogin(Integer pageNo, Integer pageSize) {
		AdminUserDetails currentAdmin = (AdminUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();

		Map<String, Object> map = new HashMap<String, Object>();

		KssLogLoginQueryCondition query = new KssLogLoginQueryCondition().putAdminid(currentAdmin.getId());

		long count = kssLogLoginDao.selectCountByQueryCondition(query);
		List<KssLogLoginDO> logs = kssLogLoginDao.selectListByQueryCondition(query.pagination(pageNo, pageSize));
		map.put("loginlogs", kssConverter.convert(logs));
		map.put("count", count);

		return map;
	}

	// 授权
	@Override
	public KssPowerDO empower(long adminid, long keysetid, BigDecimal sellprice) {

		AdminUserDetails currentAdmin = (AdminUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();

		// 检查卡类是否存在
		KssKeySetDO keyset = kssKeySetDao.selectById(keysetid);
		Assert.notNull(keyset, "卡类不存在");
		// 检查管理员是否存在
		KssAdminDO admin = kssAdminDao.selectById(adminid);
		Assert.notNull(admin, "管理员不存在");
		// 权限检查
		Assert.state(admin.isOwnedBy(currentAdmin.getId()), "没有权限");
		// 检查父账号权限
		KssPowerDO ppower = kssPowerDao.selectOneByQueryCondition(
				new KssPowerQueryCondition().putAdminid(currentAdmin.getId()).putKeysetid(keysetid));
		Assert.notNull(ppower, "没有权限");
		// 检查售价
		Assert.state(
				currentAdmin.getRole().hasFullPermission() || sellprice.intValue() >= ppower.getSellprice().intValue(),
				"售价不符");
		// 是否已授权
		if (kssPowerDao.selectOneByQueryCondition(
				new KssPowerQueryCondition().putAdminid(adminid).putKeysetid(keysetid)) != null) {
			throw new KssException("重复授权");
		}

		//
		KssPowerDO power = new KssPowerDO();
		power.setAdminid(adminid);
		power.setKeysetid(keysetid);
		power.setSellprice(sellprice);
		kssPowerDao.insert(power);

		return kssPowerDao
				.selectOneByQueryCondition(new KssPowerQueryCondition().putAdminid(adminid).putKeysetid(keysetid));
	}

	@Override
	public KssPowerDO updatepower(long adminid, long keysetid, BigDecimal sellprice) {

		AdminUserDetails currentAdmin = (AdminUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();

		// 检查管理员是否存在
		KssAdminDO admin = kssAdminDao.selectById(adminid);
		Assert.notNull(admin, "管理员不存在");
		Assert.isTrue(admin.getStatus().isActive(), "管理员已锁定");
		// 权限
		KssPowerDO power = kssPowerDao
				.selectOneByQueryCondition(new KssPowerQueryCondition().putAdminid(adminid).putKeysetid(keysetid));
		Assert.notNull(power, "没有授权");
		Assert.state(admin.isOwnedBy(currentAdmin.getId()), "没有权限");
		// 检查售价
		Assert.state(
				currentAdmin.getRole().hasFullPermission() || sellprice.intValue() >= power.getSellprice().intValue(),
				"售价不符");

		// update
		kssPowerDao.update(power.getId(), sellprice);

		return kssPowerDao
				.selectOneByQueryCondition(new KssPowerQueryCondition().putAdminid(adminid).putKeysetid(keysetid));
	}

	@Override
	public void removepower(long adminid, long keysetid) {

		AdminUserDetails currentAdmin = (AdminUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		// 检查管理员是否存在
		KssAdminDO admin = kssAdminDao.selectById(adminid);
		Assert.notNull(admin, "管理员不存在");
		// 权限检查
		Assert.isTrue(!admin.getRole().hasFullPermission(), "不能操作作者账号");
		Assert.isTrue(admin.isOwnedBy(currentAdmin.getId()), "没有权限");

		kssPowerDao.deleteByKeysetIdAndAdminid(keysetid, adminid);
	}

	@Override
	public List<KssPowerDO> listpower(Long softid, Long adminid) {
		AdminUserDetails currentAdmin = (AdminUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		if (adminid != null) {
			// 检查管理员是否存在
			KssAdminDO admin = kssAdminDao.selectById(adminid);
			Assert.notNull(admin, "管理员不存在");
			Assert.isTrue(admin.getStatus().isActive(), "管理员已锁定");
			// 权限检查
			Assert.state(admin.isOwnedByOrEqual(currentAdmin.getId()), "没有权限");
		} else {
			adminid = currentAdmin.getId();
		}
		//
		return kssPowerDao
				.selectListByQueryCondition(new KssPowerQueryCondition().putSoftid(softid).putAdminid(adminid));
	}

	@Override
	public void updatePwd(long adminid, String pwd) {
		AdminUserDetails currentAdmin = (AdminUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		// 检查管理员是否存在
		KssAdminDO admin = kssAdminDao.selectById(adminid);
		Assert.notNull(admin, "管理员不存在");
		Assert.isTrue(admin.getStatus().isActive(), "管理员已锁定");
		// 权限检查
		Assert.isTrue(admin.isOwnedByOrEqual(currentAdmin.getId()), "没有权限");

		kssAdminDao.updatePassword(adminid, bcryptEncoder.encode(pwd));
	}

	@Override
	@Transactional
	public void updateMoney(final long adminid, final BigDecimal money) {
		txTemplate.execute(new TransactionCallbackWithoutResult() {

			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				AdminUserDetails currentAdmin = (AdminUserDetails) SecurityContextHolder.getContext()
						.getAuthentication().getPrincipal();
				// 检查管理员是否存在
				KssAdminDO admin = kssAdminDao.selectById(adminid);
				Assert.notNull(admin, "管理员不存在");
				Assert.isTrue(admin.getStatus().isActive(), "管理员已锁定");
				// 权限检查
				Assert.isTrue(admin.isOwnedBy(currentAdmin.getId()) && admin.getId() != currentAdmin.getId(), "没有权限");

				BigDecimal moneychange = money.subtract(admin.getMoney());
				BigDecimal moneybefore = admin.getMoney();
				BigDecimal moneynow = money;

				KssAdminDO parent = kssAdminDao.selectById(currentAdmin.getId());
				Assert.notNull(parent, "上级账号不存在");
				BigDecimal pmoneychange = BigDecimal.ZERO.subtract(moneychange);
				BigDecimal pmoneybefore = parent.getMoney();
				BigDecimal pmoneynow = pmoneybefore.add(pmoneychange);
				// 余额检查
				Assert.state(currentAdmin.getRole().hasFullPermission() || pmoneynow.intValue() >= 0, "余额不足");
				// 更新父账号余额
				kssAdminDao.updateMoney(currentAdmin.getId(), pmoneynow);
				// 现账号余额
				kssAdminDao.updateMoney(adminid, moneynow);

				// admin记录
				KssFinanceDO log = new KssFinanceDO();
				log.setAddtime(new Date());
				log.setAdminid(adminid);
				log.setRelatedid(currentAdmin.getId());
				log.setIn(true);
				log.setMoneybefore(moneybefore);
				log.setMoneychange(moneychange);
				log.setMoneynow(moneynow);
				log.setDisc("充值来自:" + currentAdmin.getUsername());
				// 财务日志
				kssFinanceDao.insert(log);

				// parent记录
				KssFinanceDO plog = new KssFinanceDO();
				plog.setAddtime(new Date());
				plog.setAdminid(currentAdmin.getId());
				plog.setRelatedid(adminid);
				plog.setIn(false);
				plog.setMoneybefore(pmoneybefore);
				plog.setMoneychange(pmoneychange);
				plog.setMoneynow(pmoneynow);
				plog.setDisc("充值给:" + admin.getUsername());
				kssFinanceDao.insert(plog);
			}
		});
	}

	@Override
	public Map<String, Object> listFinance(Integer pageNo, Integer pageSize) {
		AdminUserDetails currentAdmin = (AdminUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();

		KssFinanceQueryCondition query = new KssFinanceQueryCondition().putAdminid(currentAdmin.getId());
		long count = kssFinanceDao.selectCountByQueryCondition(query);
		List<KssFinanceDO> finances = kssFinanceDao.selectListByQueryCondition(query.pagination(pageNo, pageSize));

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("finances", kssConverter.convert(finances));
		map.put("count", count);
		return map;
	}

}
