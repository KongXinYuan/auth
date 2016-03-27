package com.xuguruogu.auth.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.Assert;

import com.xuguruogu.auth.dal.daointerface.KssAdminDao;
import com.xuguruogu.auth.dal.daointerface.KssFinanceDao;
import com.xuguruogu.auth.dal.daointerface.KssKeySetDao;
import com.xuguruogu.auth.dal.daointerface.KssOrderDao;
import com.xuguruogu.auth.dal.daointerface.KssPowerDao;
import com.xuguruogu.auth.dal.daointerface.KssSoftDao;
import com.xuguruogu.auth.dal.daointerface.KssSoftKeyDao;
import com.xuguruogu.auth.dal.daointerface.KssStatisticsDao;
import com.xuguruogu.auth.dal.dataobject.KssAdminDO;
import com.xuguruogu.auth.dal.dataobject.KssCDKeyStatisticsDO;
import com.xuguruogu.auth.dal.dataobject.KssFinanceDO;
import com.xuguruogu.auth.dal.dataobject.KssKeySetDO;
import com.xuguruogu.auth.dal.dataobject.KssOrderDO;
import com.xuguruogu.auth.dal.dataobject.KssPowerDO;
import com.xuguruogu.auth.dal.dataobject.KssSoftDO;
import com.xuguruogu.auth.dal.dataobject.KssSoftKeyDO;
import com.xuguruogu.auth.dal.dto.KssConverter;
import com.xuguruogu.auth.dal.enums.CDKeyStatusType;
import com.xuguruogu.auth.dal.enums.RoleType;
import com.xuguruogu.auth.dal.querycondition.KssOrderQueryCondition;
import com.xuguruogu.auth.dal.querycondition.KssPowerQueryCondition;
import com.xuguruogu.auth.dal.querycondition.KssSoftKeyQueryCondition;
import com.xuguruogu.auth.security.AdminUserDetails;
import com.xuguruogu.auth.service.SoftKeyManager;
import com.xuguruogu.auth.util.RandomUtil;
import com.xuguruogu.auth.web.param.CDKeySearchParam;

@Component("softKeyManager")
@Secured({ "ROLE_OWNER", "ROLE_ADMIN", "ROLE_SELLER" })
public class SoftKeyManagerImpl implements SoftKeyManager {

	@Autowired
	private KssSoftKeyDao kssSoftKeyDao;

	@Autowired
	private KssKeySetDao kssKeySetDao;

	@Autowired
	private KssOrderDao kssOrderDao;

	@Autowired
	private KssConverter kssConverter;

	@Autowired
	private KssStatisticsDao kssStatisticsDao;

	@Autowired
	private KssPowerDao kssPowerDao;

	@Autowired
	private KssSoftDao kssSoftDao;

	@Autowired
	private KssAdminDao kssAdminDao;

	@Autowired
	private KssFinanceDao kssFinanceDao;

	@Autowired
	private TransactionTemplate txTemplate;

	@Override
	public List<KssSoftKeyDO> create(final long softid, final long keysetid, final String tag, final long num) {

		return txTemplate.execute(new TransactionCallback<List<KssSoftKeyDO>>() {

			@Override
			public List<KssSoftKeyDO> doInTransaction(TransactionStatus status) {
				Assert.state(num >= 0, "数量不小于零");

				AdminUserDetails currentAdmin = (AdminUserDetails) SecurityContextHolder.getContext()
						.getAuthentication().getPrincipal();
				// 管理员
				KssAdminDO admin = kssAdminDao.selectById(currentAdmin.getId());
				Assert.state(null != admin && admin.getStatus().isActive(), "管理员不存在或已被锁定");
				// 软件
				KssSoftDO soft = kssSoftDao.selectById(softid);
				Assert.state(null != soft && soft.getStatus().isActive(), "软件不存在或已被锁定");
				// 卡类
				KssKeySetDO keyset = kssKeySetDao.selectById(keysetid);
				Assert.state(null != keyset && keyset.getStatus().isActive(), "卡类不存在或已被锁定");
				// 权限
				KssPowerDO power = kssPowerDao.selectOneByQueryCondition(
						new KssPowerQueryCondition().putKeysetid(keysetid).putAdminid(currentAdmin.getId()));
				Assert.notNull(power, "没有权限");

				// 创建
				BigDecimal cday = keyset.getCday();
				String prefix = keyset.getPrefix();

				// 创建订单 生成订单号
				String ordernum = UUID.randomUUID().toString().replace("-", "");
				KssOrderDO order = new KssOrderDO();
				order.setAddtime(new Date());
				order.setAdminid(currentAdmin.getId());
				order.setKeycount(num);
				order.setKeysetid(keysetid);
				order.setOrdernum(ordernum);
				order.setSoftid(softid);
				kssOrderDao.insert(order);

				// 生成cdkey
				List<KssSoftKeyDO> cdkeysinsert = new ArrayList<KssSoftKeyDO>();
				for (long i = 0; i < num; i++) {
					KssSoftKeyDO cdkey = new KssSoftKeyDO();
					cdkey.setAddtime(new Date());
					cdkey.setAdminid(currentAdmin.getId());
					cdkey.setCdkey(prefix + RandomUtil.getRandomCharAndNumr(28));
					cdkey.setKeysetid(keysetid);
					cdkey.setTag(tag);
					cdkey.setOrdernum(ordernum);
					cdkey.setCday(cday);
					cdkeysinsert.add(cdkey);
				}
				kssSoftKeyDao.insertList(softid, cdkeysinsert);

				// 查询
				List<KssSoftKeyDO> cdkeys = kssSoftKeyDao.selectListByQueryCondition(softid,
						new KssSoftKeyQueryCondition().putOrdernum(ordernum));
				Assert.state(null != cdkeys && num == cdkeys.size(), "生成失败，请重新尝试");
				// 完成订单
				kssOrderDao.update(ordernum, cdkeys.get(0).getId());

				// 财务
				BigDecimal moneychange = BigDecimal.valueOf(0 - power.getSellprice().intValue() * num);
				BigDecimal moneybefore = admin.getMoney();
				BigDecimal moneynow = moneybefore.add(moneychange);
				Assert.state(currentAdmin.getRole().hasFullPermission() || moneynow.intValue() >= 0, "余额不足");
				// 更新余额
				kssAdminDao.updateMoney(currentAdmin.getId(), moneynow);
				// 财务日志
				KssFinanceDO log = new KssFinanceDO();
				log.setAddtime(new Date());
				log.setAdminid(currentAdmin.getId());
				log.setRelatedid(currentAdmin.getId());
				log.setIn(false);// 出账
				log.setMoneybefore(moneybefore);
				log.setMoneychange(moneychange);
				log.setMoneynow(moneynow);
				log.setDisc("提卡，订单号:" + ordernum);
				kssFinanceDao.insert(log);

				return cdkeys;
			}
		});

	}

	@Override
	public Map<String, Object> search(long softid, CDKeySearchParam param) {
		AdminUserDetails currentAdmin = (AdminUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();

		KssSoftKeyQueryCondition query = new KssSoftKeyQueryCondition().putParentid(currentAdmin.getId())
				.putAdminid(param.getAdminid()).putCdkey(param.getCdkey()).putKeysetid(param.getKeysetid())
				.putOrdernum(param.getOrdernum()).putTag(param.getTag())
				.putUserid(param.getUserid()).putUsername(param.getUsername()).putStatus(null == param.getStatus()
						? CDKeyStatusType.getNonDelList() : CDKeyStatusType.asList(param.getStatus()))
				.pagination(param.getPageNo(), param.getPageSize());

		Map<String, Object> result = new HashMap<String, Object>();
		result.put("cdkeys", kssConverter.convert(kssSoftKeyDao.selectListByQueryCondition(softid, query)));
		result.put("count", kssSoftKeyDao.selectCountByQueryCondition(softid, query));
		return result;
	}

	@Override
	public Map<String, Object> order(Integer pageNo, Integer pageSize) {
		AdminUserDetails currentAdmin = (AdminUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		KssOrderQueryCondition query = new KssOrderQueryCondition()
				.putAdminid(currentAdmin.getId() == RoleType.OWNER.getLevel() ? null : currentAdmin.getId());

		Map<String, Object> result = new HashMap<String, Object>();
		result.put("count", kssOrderDao.selectCountByQueryCondition(query));
		result.put("orders",
				kssConverter.convert(kssOrderDao.selectListByQueryCondition(query.pagination(pageNo, pageSize))));
		return result;
	}

	@Override
	public void lockByIds(long softid, List<Long> cdkeyids, boolean lock) {
		AdminUserDetails currentAdmin = (AdminUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();

		List<KssSoftKeyDO> cdkeys = kssSoftKeyDao.selectByIds(softid, cdkeyids);
		Assert.state(null != cdkeys, "没有有效目标");
		List<Long> todolist = new ArrayList<>();
		for (KssSoftKeyDO cdkey : cdkeys) {
			// 有效目标检测
			if (cdkey.getStatus().canLock() && cdkey.isOwnedBy(currentAdmin.getId())) {
				todolist.add(cdkey.getId());
			}
		}
		Assert.state(todolist.size() >= 1, "没有有效目标");
		kssSoftKeyDao.updateStatusByIds(softid, todolist, CDKeyStatusType.fromLock(lock));
	}

	@Override
	public void deleteByIds(long softid, List<Long> cdkeyids) {
		AdminUserDetails currentAdmin = (AdminUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();

		List<KssSoftKeyDO> cdkeys = kssSoftKeyDao.selectByIds(softid, cdkeyids);
		Assert.notNull(cdkeys, "没有有效目标");
		List<Long> todolist = new ArrayList<>();
		for (KssSoftKeyDO cdkey : cdkeys) {
			if (cdkey.isOwnedBy(currentAdmin.getId())) {
				todolist.add(cdkey.getId());
			}
		}
		Assert.state(todolist.size() >= 1, "没有有效目标");

		kssSoftKeyDao.updateStatusByIds(softid, todolist, CDKeyStatusType.DELETED);
	}

	@Override
	public List<KssCDKeyStatisticsDO> statistics(long softid) {
		AdminUserDetails currentAdmin = (AdminUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		KssSoftDO soft = kssSoftDao.selectById(softid);
		Assert.state(null != soft, "软件不存在");

		return kssStatisticsDao.selectCDKeyStatistics(softid,
				currentAdmin.getId() == RoleType.OWNER.getLevel() ? null : currentAdmin.getId());
	}

}
