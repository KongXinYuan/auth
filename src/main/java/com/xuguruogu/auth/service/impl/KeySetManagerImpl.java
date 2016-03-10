package com.xuguruogu.auth.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.xuguruogu.auth.dal.daointerface.KssKeySetDao;
import com.xuguruogu.auth.dal.dataobject.KssKeySetDO;
import com.xuguruogu.auth.dal.querycondition.KssKeySetQueryCondition;
import com.xuguruogu.auth.service.KeySetManager;

public class KeySetManagerImpl implements KeySetManager {

	@Autowired
	private KssKeySetDao kssKeySetDao;

	@Override
	public KssKeySetDO create(long softid, String keyname, BigDecimal cday, String prefix, BigDecimal retailprice) {

		KssKeySetDO kssKeySetDO = new KssKeySetDO();
		kssKeySetDO.setSoftid(softid);
		kssKeySetDO.setKeyname(keyname);
		kssKeySetDO.setCday(cday);
		kssKeySetDO.setPrefix(prefix);
		kssKeySetDO.setRetailprice(retailprice);
		kssKeySetDao.insert(kssKeySetDO);

		//
		KssKeySetQueryCondition query = new KssKeySetQueryCondition();
		query.putKeyname(keyname);

		return kssKeySetDao.selectOneByQueryCondition(query);
	}

	@Override
	public KssKeySetDO queryById(long keySetId) {
		return kssKeySetDao.selectById(keySetId);
	}

	@Override
	public List<KssKeySetDO> selectBySoftId(long softid) {
		KssKeySetQueryCondition query = new KssKeySetQueryCondition();
		query.putSoftid(softid);

		return kssKeySetDao.selectListByQueryCondition(query);
	}

	@Override
	public long countBySoftId(long softid) {
		KssKeySetQueryCondition query = new KssKeySetQueryCondition();
		query.putSoftid(softid);

		return kssKeySetDao.selectCountByQueryCondition(query);
	}

	@Override
	public void deleteById(long keySetId) {
		kssKeySetDao.deleteById(keySetId);
	}

	@Override
	public void deleteByIds(List<Long> keySetIds) {
		kssKeySetDao.deleteByIds(keySetIds);
	}

	@Override
	public void update(long keySetId, String keyname, BigDecimal retailprice) {
		kssKeySetDao.update(keySetId, keyname, retailprice);
	}

	@Override
	public void updateLock(long keySetId, boolean lock) {
		kssKeySetDao.updateLock(keySetId, lock);
	}

}
