package com.xuguruogu.auth.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xuguruogu.auth.dal.daointerface.KssSoftDao;
import com.xuguruogu.auth.dal.dataobject.KssSoftDO;
import com.xuguruogu.auth.dal.querycondition.KssSoftQueryCondition;
import com.xuguruogu.auth.service.SoftManager;
import com.xuguruogu.auth.util.RandomUtil;

@Component("softManager")
public class SoftManagerImpl implements SoftManager {

	@Autowired
	private KssSoftDao kssSoftDao;

	@Override
	public void create(String softname, String clientpubkey, String serverprivkey) {

		// 插入并锁定
		KssSoftDO kssSoftDO = new KssSoftDO();
		kssSoftDO.setServerprivkey(serverprivkey);
		kssSoftDO.setClientpubkey(clientpubkey);
		kssSoftDO.setSoftkey(RandomUtil.getRandomCharAndNumr(8));
		kssSoftDO.setSoftname(softname);
		kssSoftDO.setIslock(true);
		kssSoftDao.insert(kssSoftDO);
		// 获取id
		long softid = kssSoftDao.selectLastId();
		// 更新softcode,并解锁
		kssSoftDao.updateSoftcode(softid, softid + 10000);
	}

	@Override
	public KssSoftDO selectOne(long softcode) {

		KssSoftQueryCondition query = new KssSoftQueryCondition();
		query.putSoftcode(softcode);

		return kssSoftDao.selectOneByQueryCondition(query);
	}

	@Override
	public List<KssSoftDO> selectAll() {

		KssSoftQueryCondition query = new KssSoftQueryCondition();

		return kssSoftDao.selectListByQueryCondition(query);
	}

	@Override
	public int count() {

		KssSoftQueryCondition query = new KssSoftQueryCondition();

		return kssSoftDao.selectCountByQueryCondition(query);
	}

	@Override
	public void update(long softid, boolean islock, int intervaltime, String clientpubkey, String serverprivkey) {
		kssSoftDao.update(softid, islock, intervaltime, clientpubkey, serverprivkey);
	}

	@Override
	public KssSoftDO queryById(long softid) {

		return kssSoftDao.selectById(softid);
	}

}
