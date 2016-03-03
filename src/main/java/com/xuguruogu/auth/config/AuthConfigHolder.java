package com.xuguruogu.auth.config;

import com.alibaba.appengine.api.cache.CacheService;
import com.alibaba.appengine.api.cache.CacheServiceFactory;

/**
 * @author benli.lbl 配置
 */
public class AuthConfigHolder {

	private static final String SOFT_CACHEKEY_PREFIX = "soft";
	private static final String JOIN = "-";

	/**
	 * 获取软件配置的缓存key
	 *
	 * @param softCode
	 * @return
	 */
	public static String getSoftCacheKey(long softCode) {
		return SOFT_CACHEKEY_PREFIX + JOIN + softCode;
	}

	public static SoftConfigDO getSoftConfig(long softCode) {

		CacheService cache = CacheServiceFactory.getCacheService();
		return (SoftConfigDO) cache.get(getSoftCacheKey(softCode));
	}

}
