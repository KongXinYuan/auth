package com.xuguruogu.auth.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.appengine.api.cache.CacheService;
import com.alibaba.appengine.api.cache.CacheServiceFactory;
import com.xuguruogu.auth.config.AuthConfigHolder;
import com.xuguruogu.auth.dal.daointerface.KssKeySetDao;
import com.xuguruogu.auth.dal.daointerface.KssSoftDao;
import com.xuguruogu.auth.dal.dataobject.KssSoftDO;
import com.xuguruogu.auth.dal.querycondition.KssKeySetQueryCondition;
import com.xuguruogu.auth.dal.querycondition.KssSoftQueryCondition;
import com.xuguruogu.auth.dto.SoftDTO;
import com.xuguruogu.auth.service.SoftManager;
import com.xuguruogu.auth.util.Converter;
import com.xuguruogu.auth.util.RandomUtil;

@Component("softManager")
public class SoftManagerImpl implements SoftManager {

	@Autowired
	private KssSoftDao kssSoftDao;

	@Autowired
	private KssKeySetDao kssKeySetDao;

	@Autowired
	private Converter<KssSoftDO, SoftDTO> softDTOConverter;

	@Override
	public Map<String, Object> list(Integer pageNo, Integer pageSize) {

		Map<String, Object> result = new HashMap<String, Object>();

		// 默认值设置
		if (null == pageSize || null == pageNo) {
			pageSize = 20;
			pageNo = 1;
		}
		KssSoftQueryCondition query = new KssSoftQueryCondition();

		result.put("count", kssSoftDao.selectCountByQueryCondition(query));
		query.pagination(pageNo, pageSize);
		List<KssSoftDO> list = kssSoftDao.selectListByQueryCondition(query);
		result.put("results", softDTOConverter.converter(list));

		return result;
	}

	@Override
	public List<SoftDTO> listAll() {
		return softDTOConverter.converter(kssSoftDao.selectListByQueryCondition(new KssSoftQueryCondition()));
	}

	@Override
	public SoftDTO create(String softname, long intervaltime, String clientpubkey, String serverprivkey) {

		// 删除未完成的记录
		KssSoftDO badSoftDO = kssSoftDao.selectOneByQueryCondition(new KssSoftQueryCondition().putSoftcode(0L));
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
		kssSoftDO.setIntervaltime(intervaltime);
		kssSoftDO.setSoftcode(0L);
		kssSoftDao.insert(kssSoftDO);

		// 获取id
		KssSoftDO soft = kssSoftDao.selectOneByQueryCondition(new KssSoftQueryCondition().putSoftname(softname));
		long softid = soft.getId();

		try {
			// 清空可能存在的id
			kssSoftDao.dropTableWithSeg(softid);
			kssSoftDao.creatTableWithSeg(softid);
			// 更新softcode,并解锁
			kssSoftDao.updateSoftcode(softid, softid + 1000000);
		} catch (Exception e) {
			kssSoftDao.dropTableWithSeg(softid);
			kssSoftDao.deleteById(softid);
			throw e;
		}

		// 获取DO
		return softDTOConverter.convert(kssSoftDao.selectById(softid));
	}

	@Override
	public SoftDTO detail(long softid) {
		return softDTOConverter.convert(kssSoftDao.selectById(softid));
	}

	// 更新
	// 去缓存
	@Override
	public SoftDTO update(long softid, long intervaltime, String clientpubkey, String serverprivkey) {
		kssSoftDao.update(softid, intervaltime, clientpubkey, serverprivkey);

		KssSoftDO soft = kssSoftDao.selectById(softid);
		emitSoftCache(soft.getSoftcode());
		return softDTOConverter.convert(soft);
	}

	// 锁定
	// 去缓存
	@Override
	public void updateLock(long softid, boolean lock) {
		kssSoftDao.updateLock(softid, lock);
		emitSoftCache(kssSoftDao.selectById(softid).getSoftcode());
	}

	// 删除
	// 去缓存
	@Override
	public void deleteByIds(List<Long> softids) {
		for (long softid : softids) {
			emitSoftCache(kssSoftDao.selectById(softid).getSoftcode());
			kssSoftDao.dropTableWithSeg(softid);
			kssSoftDao.deleteById(softid);
			kssKeySetDao.deleteByQueryCondition(new KssKeySetQueryCondition().putSoftid(softid));
		}
	}

	// 加缓存
	@Override
	public KssSoftDO selectBySoftcode(long softcode) {

		KssSoftDO soft = getSoftCache(softcode);
		if (null != soft) {
			return soft;
		}

		KssSoftQueryCondition query = new KssSoftQueryCondition();
		query.putSoftcode(softcode);

		soft = kssSoftDao.selectOneByQueryCondition(query);
		if (null == soft) {
			return null;
		}

		putSoftCache(soft);
		return soft;
	}

	// 缓存操作
	// 查缓存
	private KssSoftDO getSoftCache(long softcode) {

		CacheService cache = CacheServiceFactory.getCacheService();
		String cacheKey = AuthConfigHolder.getSoftCacheKey(softcode);
		return (KssSoftDO) cache.get(cacheKey);
	}

	// 缓存操作
	// 加缓存
	private void putSoftCache(KssSoftDO kssSoftDO) {
		if (null == kssSoftDO) {
			return;
		}
		CacheService cache = CacheServiceFactory.getCacheService();
		String cacheKey = AuthConfigHolder.getSoftCacheKey(kssSoftDO.getSoftcode());
		cache.put(cacheKey, kssSoftDO);
	}

	// 缓存操作
	// 去缓存
	private void emitSoftCache(long softcode) {
		CacheService cache = CacheServiceFactory.getCacheService();
		String cacheKey = AuthConfigHolder.getSoftCacheKey(softcode);
		cache.delete(cacheKey);
	}

}
