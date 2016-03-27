package com.xuguruogu.auth.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.Assert;

import com.xuguruogu.auth.dal.daointerface.KssKeySetDao;
import com.xuguruogu.auth.dal.daointerface.KssPowerDao;
import com.xuguruogu.auth.dal.dataobject.KssKeySetDO;
import com.xuguruogu.auth.dal.dataobject.KssPowerDO;
import com.xuguruogu.auth.dal.enums.KeySetStatusType;
import com.xuguruogu.auth.dal.querycondition.KssKeySetQueryCondition;
import com.xuguruogu.auth.security.AdminUserDetails;
import com.xuguruogu.auth.service.KeySetManager;

@Component("keySetManager")
public class KeySetManagerImpl implements KeySetManager {

	@Autowired
	private KssKeySetDao kssKeySetDao;

	@Autowired
	private KssPowerDao kssPowerDao;

	@Autowired
	private TransactionTemplate txTemplate;

	@Override
	@Secured({ "ROLE_OWNER", "ROLE_ADMIN", "ROLE_SELLER" })
	public List<KssKeySetDO> listAll() {
		AdminUserDetails currentAdmin = (AdminUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();

		return kssKeySetDao.selectListByQueryCondition(new KssKeySetQueryCondition().putAdminid(currentAdmin.getId())
				.putStatus(KeySetStatusType.getNonDelList()));
	}

	@Override
	@Secured({ "ROLE_OWNER", "ROLE_ADMIN", "ROLE_SELLER" })
	public List<KssKeySetDO> listAll(final long softid) {
		AdminUserDetails currentAdmin = (AdminUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();

		return kssKeySetDao.selectListByQueryCondition(new KssKeySetQueryCondition().putAdminid(currentAdmin.getId())
				.putSoftid(softid).putStatus(KeySetStatusType.getNonDelList()));
	}

	@Override
	@Secured({ "ROLE_OWNER" })
	public KssKeySetDO create(final long softid, final String keyname, final BigDecimal cday, final String prefix,
			final BigDecimal retailprice) {

		// 检测是否存在
		return txTemplate.execute(new TransactionCallback<KssKeySetDO>() {
			@Override
			public KssKeySetDO doInTransaction(TransactionStatus arg0) {

				Assert.isNull(kssKeySetDao.selectOneByQueryCondition(new KssKeySetQueryCondition().putKeyname(keyname)),
						"卡类名已存在");
				// 创建卡类
				KssKeySetDO kssKeySetDO = new KssKeySetDO();
				kssKeySetDO.setSoftid(softid);
				kssKeySetDO.setKeyname(keyname);
				kssKeySetDO.setCday(cday);
				kssKeySetDO.setPrefix(prefix);
				kssKeySetDO.setRetailprice(retailprice);
				kssKeySetDao.insert(kssKeySetDO);
				KssKeySetDO keyset = kssKeySetDao
						.selectOneByQueryCondition(new KssKeySetQueryCondition().putKeyname(keyname));
				Assert.notNull(keyset, "卡类创建失败");
				// 授权给当前用户owner
				KssPowerDO power = new KssPowerDO();
				AdminUserDetails user = (AdminUserDetails) SecurityContextHolder.getContext().getAuthentication()
						.getPrincipal();
				power.setAdminid(user.getId());
				power.setKeysetid(keyset.getId());
				power.setSellprice(BigDecimal.ZERO);
				kssPowerDao.insert(power);
				return keyset;
			}
		});

	}

	@Override
	@Secured({ "ROLE_OWNER" })
	public KssKeySetDO detail(final long keySetId) {
		return kssKeySetDao.selectById(keySetId);
	}

	@Override
	@Secured({ "ROLE_OWNER" })
	public void deleteByIds(final List<Long> keySetIds) {
		// 开启事务
		txTemplate.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {

				List<KssKeySetDO> keysetlist = kssKeySetDao.selectByIds(keySetIds);
				Assert.state(null != keysetlist && keysetlist.size() == keySetIds.size(), "部分卡类不存在");
				List<Long> ids = new ArrayList<>();
				for (KssKeySetDO keyset : keysetlist) {
					ids.add(keyset.getId());
				}
				// 标记删除
				kssKeySetDao.updateStatusByIds(ids, KeySetStatusType.DELETED);
				// 删除所有授权
				kssPowerDao.deleteByKeySetIds(ids);
			}
		});

	}

	@Override
	@Secured({ "ROLE_OWNER" })
	public KssKeySetDO update(final long keySetId, final BigDecimal cday, final BigDecimal retailprice) {

		Assert.notNull(kssKeySetDao.selectById(keySetId), "卡类不存在");
		kssKeySetDao.update(keySetId, cday, retailprice);
		return kssKeySetDao.selectById(keySetId);
	}

	@Override
	@Secured({ "ROLE_OWNER" })
	public void lockByIds(final long keySetId, final boolean lock) {
		KssKeySetDO keyset = kssKeySetDao.selectById(keySetId);
		Assert.notNull(keyset, "卡类不存在");
		Assert.isTrue(keyset.getStatus().canLock(), "卡类不能锁定");
		kssKeySetDao.updateStatusById(keySetId, true == lock ? KeySetStatusType.LOCKED : KeySetStatusType.ACTIVE);
	}

}
