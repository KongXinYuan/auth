package com.xuguruogu.auth.config;

/**
 * @author benli.lbl 配置
 */
public class AuthConfigHolder {

	private static final String SOFT_CACHEKEY_PREFIX = "soft";
	private static final String USER_CACHEKEY_PREFIX = "user";
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

	/**
	 * 获取用户的缓存key
	 *
	 * @param softCode
	 * @return
	 */
	public static String getUserCacheKey(long softid, long userid) {
		return USER_CACHEKEY_PREFIX + JOIN + softid + JOIN + userid;
	}

	/**
	 * 获取公共用户的缓存key
	 *
	 * @param softCode
	 * @return
	 */
	public static String getPublicUserCacheKey(long softid, long userid, String pccode) {
		return USER_CACHEKEY_PREFIX + JOIN + softid + JOIN + userid + JOIN + pccode;
	}
}
