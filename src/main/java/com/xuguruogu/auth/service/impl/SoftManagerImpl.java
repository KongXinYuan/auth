package com.xuguruogu.auth.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.appengine.api.cache.CacheService;
import com.alibaba.appengine.api.cache.CacheServiceFactory;
import com.xuguruogu.auth.config.AuthConfigHolder;
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
	public KssSoftDO create(String softname, String clientpubkey, String serverprivkey) {

		// 删除未完成的记录
		KssSoftDO badSoftDO = selectBySoftcode(0L);
		if (null != badSoftDO) {
			kssSoftDao.deleteById(badSoftDO.getId());
		}

		// 插入并锁定
		KssSoftDO kssSoftDO = new KssSoftDO();
		kssSoftDO.setServerprivkey(serverprivkey);
		kssSoftDO.setClientpubkey(clientpubkey);
		kssSoftDO.setSoftkey(RandomUtil.getRandomCharAndNumr(24));
		kssSoftDO.setSoftname(softname);
		kssSoftDO.setLock(true);
		kssSoftDao.insert(kssSoftDO);
		// 获取id
		KssSoftDO soft = selectBySoftname(softname);
		// 更新softcode,并解锁
		kssSoftDao.updateSoftcode(soft.getId(), soft.getId() + 10000000);
		// 获取DO
		return kssSoftDao.selectById(soft.getId());
	}

	// 加缓存
	@Override
	public KssSoftDO selectBySoftcode(long softcode) {

		KssSoftDO kssSoftDO = selectBySoftcodeFromCache(softcode);
		if (null != kssSoftDO) {
			return kssSoftDO;
		}

		KssSoftQueryCondition query = new KssSoftQueryCondition();
		query.putSoftcode(softcode);

		kssSoftDO = kssSoftDao.selectOneByQueryCondition(query);
		if (null == kssSoftDO) {
			return null;
		}

		putSoftCache(kssSoftDO);
		return selectBySoftcodeFromCache(softcode);
	}

	@Override
	public KssSoftDO selectBySoftname(String softname) {

		KssSoftQueryCondition query = new KssSoftQueryCondition();
		query.putSoftname(softname);

		return kssSoftDao.selectOneByQueryCondition(query);
	}

	@Override
	public List<KssSoftDO> selectAll() {

		KssSoftQueryCondition query = new KssSoftQueryCondition();

		return kssSoftDao.selectListByQueryCondition(query);
	}

	@Override
	public long count() {

		KssSoftQueryCondition query = new KssSoftQueryCondition();

		return kssSoftDao.selectCountByQueryCondition(query);
	}

	// 去缓存
	@Override
	public void update(long softid, boolean islock, long intervaltime, String clientpubkey, String serverprivkey) {
		kssSoftDao.update(softid, islock, intervaltime, clientpubkey, serverprivkey);
		emitSoftCache(softid);
	}

	@Override
	public KssSoftDO queryById(long softid) {

		return kssSoftDao.selectById(softid);
	}

	// 去缓存
	@Override
	public void deleteById(long softid) {
		kssSoftDao.deleteById(softid);
		emitSoftCache(softid);
	}

	// 查缓存
	KssSoftDO selectBySoftcodeFromCache(long softcode) {

		CacheService cache = CacheServiceFactory.getCacheService();
		String cacheKey = AuthConfigHolder.getSoftCacheKey(softcode);
		return (KssSoftDO) cache.get(cacheKey);
	}

	// 加缓存
	void putSoftCache(KssSoftDO kssSoftDO) {
		if (null == kssSoftDO) {
			return;
		}
		CacheService cache = CacheServiceFactory.getCacheService();
		String cacheKey = AuthConfigHolder.getSoftCacheKey(kssSoftDO.getSoftcode());
		cache.put(cacheKey, kssSoftDO);
	}

	// 去缓存
	void emitSoftCache(long softid) {
		KssSoftDO kssSoftDO = queryById(softid);
		if (null == kssSoftDO) {
			return;
		}
		CacheService cache = CacheServiceFactory.getCacheService();
		String cacheKey = AuthConfigHolder.getSoftCacheKey(kssSoftDO.getSoftcode());
		cache.delete(cacheKey);
	}
}
