package com.xuguruogu.auth.config;

/**
 * @author benli.lbl 配置
 */
public class AuthConfigHolder {
	private static String publicKey;
	private static String privateKey;

	public static String getPublicKey() {
		return publicKey;
	}

	public static void setPublicKey(String publicKey) {
		AuthConfigHolder.publicKey = publicKey;
	}

	public static String getPrivateKey() {
		return privateKey;
	}

	public static void setPrivateKey(String privateKey) {
		AuthConfigHolder.privateKey = privateKey;
	}

}
