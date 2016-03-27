package com.xuguruogu.auth.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.xuguruogu.auth.dal.daointerface.KssKeySetDao;
import com.xuguruogu.auth.dal.daointerface.KssPowerDao;
import com.xuguruogu.auth.dal.daointerface.KssSoftDao;
import com.xuguruogu.auth.dal.dataobject.KssSoftDO;
import com.xuguruogu.auth.dal.enums.KeySetStatusType;
import com.xuguruogu.auth.dal.enums.SoftStatusType;
import com.xuguruogu.auth.dal.querycondition.KssSoftQueryCondition;
import com.xuguruogu.auth.security.AdminUserDetails;
import com.xuguruogu.auth.service.SoftManager;
import com.xuguruogu.auth.util.RandomUtil;

@Component("softManager")
public class SoftManagerImpl implements SoftManager {

	@Autowired
	private KssSoftDao kssSoftDao;

	@Autowired
	private KssKeySetDao kssKeySetDao;

	@Autowired
	private KssPowerDao kssPowerDao;

	@Autowired
	private TransactionTemplate txTemplate;

	@Override
	@Secured({ "ROLE_OWNER", "ROLE_ADMIN", "ROLE_SELLER" })
	public List<KssSoftDO> listAll() {

		AdminUserDetails currentAdmin = (AdminUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();

		return kssSoftDao.selectListByQueryCondition(new KssSoftQueryCondition()
				.putAdminid(currentAdmin.getRole().hasFullPermission() ? null : currentAdmin.getId())
				.putStatus(SoftStatusType.getNonDelList()));
	}

	@Override
	@Secured({ "ROLE_OWNER" })
	public KssSoftDO create(final String softname, final long intervaltime, final String privkey) {

		return txTemplate.execute(new TransactionCallback<KssSoftDO>() {
			@Override
			public KssSoftDO doInTransaction(TransactionStatus status) {
				// 删除未完成的记录
				KssSoftDO badSoftDO = kssSoftDao.selectOneByQueryCondition(new KssSoftQueryCondition().putSoftcode(0L));
				if (null != badSoftDO) {
					kssSoftDao.deleteById(badSoftDO.getId());
				}

				// 插入
				KssSoftDO kssSoftDO = new KssSoftDO();
				kssSoftDO.setPrivkey(privkey);
				kssSoftDO.setSoftkey(RandomUtil.getRandomCharAndNumr(24));
				kssSoftDO.setSoftname(softname);
				kssSoftDO.setIntervaltime(intervaltime);
				kssSoftDO.setSoftcode(0L);
				kssSoftDao.insert(kssSoftDO);

				// 获取id
				KssSoftDO soft = kssSoftDao
						.selectOneByQueryCondition(new KssSoftQueryCondition().putSoftname(softname));
				long softid = soft.getId();

				kssSoftDao.creatTableWithSeg(softid);
				// 更新softcode
				kssSoftDao.updateSoftcode(softid, softid + 1000000);
				// 获取DO
				return kssSoftDao.selectById(softid);
			}
		});
	}

	@Override
	@Secured({ "ROLE_OWNER" })
	public KssSoftDO detail(long softid) {
		return kssSoftDao.selectById(softid);
	}

	@Override
	@Secured({ "ROLE_OWNER" })
	public KssSoftDO update(long softid, long intervaltime, String privkey) {
		kssSoftDao.update(softid, intervaltime, privkey);
		KssSoftDO soft = kssSoftDao.selectById(softid);
		return soft;
	}

	@Override
	@Secured({ "ROLE_OWNER" })
	public void updateLock(long softid, boolean lock) {
		kssSoftDao.updateStatusById(softid, SoftStatusType.fromLock(lock));
	}

	@Override
	@Secured({ "ROLE_OWNER" })
	public void deleteByIds(final List<Long> softids) {

		txTemplate.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				kssSoftDao.updateStatusByIds(softids, SoftStatusType.DELETED);
				kssKeySetDao.updateStatusBySoftIds(softids, KeySetStatusType.DELETED);
				kssPowerDao.deleteBySoftIds(softids);
			}
		});

	}

	@Override
	public KssSoftDO selectBySoftcode(long softcode) {
		KssSoftQueryCondition query = new KssSoftQueryCondition();
		query.putSoftcode(softcode);

		KssSoftDO soft = kssSoftDao.selectOneByQueryCondition(query);
		if (null == soft) {
			return null;
		}

		return soft;
	}
}
