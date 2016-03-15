package com.xuguruogu.auth.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xuguruogu.auth.dal.daointerface.KssKeySetDao;
import com.xuguruogu.auth.dal.dataobject.KssKeySetDO;
import com.xuguruogu.auth.dal.querycondition.KssKeySetQueryCondition;
import com.xuguruogu.auth.dto.KeySetDTD;
import com.xuguruogu.auth.service.KeySetManager;
import com.xuguruogu.auth.util.Converter;

@Component("keySetManager")
public class KeySetManagerImpl implements KeySetManager {

	@Autowired
	private KssKeySetDao kssKeySetDao;

	@Autowired
	private Converter<KssKeySetDO, KeySetDTD> keySetDTOConverter;

	@Override
	public List<KeySetDTD> listid(long softid) {

		return keySetDTOConverter
				.converter(kssKeySetDao.selectListByQueryCondition(new KssKeySetQueryCondition().putSoftid(softid)));
	}

	@Override
	public KeySetDTD create(long softid, String keyname, BigDecimal cday, String prefix, BigDecimal retailprice) {

		KssKeySetDO kssKeySetDO = new KssKeySetDO();
		kssKeySetDO.setSoftid(softid);
		kssKeySetDO.setKeyname(keyname);
		kssKeySetDO.setCday(cday);
		kssKeySetDO.setPrefix(prefix);
		kssKeySetDO.setRetailprice(retailprice);
		kssKeySetDao.insert(kssKeySetDO);

		return keySetDTOConverter
				.convert(kssKeySetDao.selectOneByQueryCondition(new KssKeySetQueryCondition().putKeyname(keyname)));
	}

	@Override
	public KeySetDTD detail(long keySetId) {
		return keySetDTOConverter.convert(kssKeySetDao.selectById(keySetId));
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
	public KeySetDTD update(long keySetId, BigDecimal cday, BigDecimal retailprice) {
		kssKeySetDao.update(keySetId, cday, retailprice);
		return keySetDTOConverter.convert(kssKeySetDao.selectById(keySetId));
	}

	@Override
	public void updateLock(long keySetId, boolean lock) {
		kssKeySetDao.updateLock(keySetId, lock);
	}

}
