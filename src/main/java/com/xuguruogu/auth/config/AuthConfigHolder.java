package com.xuguruogu.auth.config;

/**
 * @author benli.lbl 配置
 */
public class AuthConfigHolder {

	private static final String SOFT_CACHEKEY_PREFIX = "soft";
	private static final String USER_CACHEKEY_PREFIX = "user";
	private static final String ADMIN_CACHEKEY_PREFIX = "admin";
	private static final String KEYSET_CACHEKEY_PREFIX = "keyset";
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

	/**
	 * subAdminList
	 */
	public static String getSubAdminCacheKey(long adminid) {
		return ADMIN_CACHEKEY_PREFIX + JOIN + adminid + JOIN + "sub";
	}

	/**
	 * Admin
	 */
	public static String getAdminCacheKey(long adminid) {
		return ADMIN_CACHEKEY_PREFIX + JOIN + adminid;
	}

	/**
	 * keyset
	 */
	public static String getKeySetCacheKey(long keysetid) {
		return KEYSET_CACHEKEY_PREFIX + JOIN + keysetid;
	}

	/**
	 * adminKeysetList
	 */
	public static String getAdminKeysetCacheKey(long adminid) {
		return KEYSET_CACHEKEY_PREFIX + JOIN + adminid + JOIN + "admin";
	}
}
