package com.xuguruogu.auth.config;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.alibaba.appengine.api.cache.CacheService;
import com.alibaba.appengine.api.cache.CacheServiceFactory;
import com.xuguruogu.auth.dal.dataobject.KssSoftDO;
import com.xuguruogu.auth.service.SoftManager;

@Component("authInst")
public class AuthInst implements ApplicationListener<ContextRefreshedEvent> {

	private static Logger logger = LoggerFactory.getLogger(AuthInst.class);

	@Autowired
	private SoftManager softManager;

	@Autowired
	private SoftConfigDOConverter softConfigDOConverter;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {

		// 确保是最后一个容器
		if (event.getApplicationContext().getParent() == null) {

			if (logger.isInfoEnabled()) {
				logger.info("start refresh all cache");
			}

			refreshSoftAll();

			if (logger.isInfoEnabled()) {
				logger.info("finish refresh all cache");
			}

		}

	}

	public void refreshSoft(long softid) {
		KssSoftDO kssSoftDO = softManager.queryById(softid);

		CacheService cache = CacheServiceFactory.getCacheService();

		String cacheKey = AuthConfigHolder.getSoftCacheKey(kssSoftDO.getSoftcode());

		SoftConfigDO softConfigDO = softConfigDOConverter.convert(kssSoftDO);
		cache.put(cacheKey, softConfigDO);
	}

	public void refreshSoftAll() {
		List<KssSoftDO> kssSoftDOList = softManager.selectAll();
		CacheService cache = CacheServiceFactory.getCacheService();
		for (KssSoftDO kssSoftDO : kssSoftDOList) {
			String cacheKey = AuthConfigHolder.getSoftCacheKey(kssSoftDO.getSoftcode());
			SoftConfigDO softConfigDO = softConfigDOConverter.convert(kssSoftDO);
			cache.put(cacheKey, softConfigDO);
		}
	}

}
