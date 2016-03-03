package com.xuguruogu.auth.test.rsa;

import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.junit.Test;

import com.xuguruogu.auth.util.RSAUtils;

public class RSATester {

	static String publicKey;
	static String privateKey;

	@Test
	public void testGenerateKey() {
		try {
			Map<String, Object> keyMap = RSAUtils.genKeyPair();
			publicKey = RSAUtils.getPublicKey(keyMap);
			privateKey = RSAUtils.getPrivateKey(keyMap);
			System.err.println("公钥: \n\r" + publicKey);
			System.err.println("私钥： \n\r" + privateKey);
			System.out.println();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testHello() throws Exception {
		publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCdPEpa9tVnGYtfYj+aHFviF9sliQlNfJVbX7x7HZciZpZnbd9ryE7Zop2x77p8495KevcdNbf/SR+tcrkIrB/9RaJofuPIE7/nwDgZEpOrBn08TAAZm8XCpITbqS4ipMobUvp5MHW9wtY0AGI82omf1T7TuyvoI/qlEkPJ8alHFQIDAQAB";
		privateKey = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAJ08Slr21WcZi19iP5ocW+IX2yWJCU18lVtfvHsdlyJmlmdt32vITtminbHvunzj3kp69x01t/9JH61yuQisH/1Fomh+48gTv+fAOBkSk6sGfTxMABmbxcKkhNupLiKkyhtS+nkwdb3C1jQAYjzaiZ/VPtO7K+gj+qUSQ8nxqUcVAgMBAAECgYBdqgyhI2j8gbsElwJMS5hwlsV1Fqzz+8L9K4qbzIS7eWEfO9Rb4HcYLkbCOIxe2V8R/y41uEpjSKwWJsES6XYj+kXKJ/cf/C+1dN2E8oBSTz5YZH8C5zlaI64E0VEFTMAHKlkR3tcW2XkUUwGng5/zTTP4gz9AU2BcYdFX09MmAQJBANaIfr9BE22YASx54ru9HRDvI8zeLiY2OM0Uabo6tfKrabF9v7Po0nYDMKQbVTzSebFXtRd5keMWZO8BQFMQJQcCQQC7oJlKieSTKu67EVkRI1Ku8ODfFX2sj/L9J/7ylbB8yV6tckHu2jTYDQwYJV3t0ugnAK6lAniLYP/p4nltomgDAkEAtRxwXEe0/h2ISCLx7epegnBQueCL90hpNYDZLRXpit95EBWH5HtWSbixtKk6kWliwPu5bwL7zUmXlkppInaLuwJBAKtIbSgywcvnwZBcweZ3TKAPnfmJcPT7ZGPfA0sJg9gnLJtCVxZpdRxWrNfOJPVbODuCz+wrWUhEANvOt/Nx82kCQQChTUI3IcoWbdkpGDx7RhIKlm1zTTWSnygOhMHIwdsBJOgcIdbrIxjmZPw3Q8qmAB7/5R3EC5HARVD0CBt6bg31";

		String source = "hello";
		byte[] data = source.getBytes("UTF-8");
		String encodedData = Base64.encodeBase64String(RSAUtils.encryptByPublicKey(data, publicKey));
		System.out.println("加密后文字：\r\n" + encodedData);

		// encodedData =
		// "jRoEuy2XeeZrZFeLtsiEyAdW1bZw3OYReVEmL6Fg9CWdPyI1WLzbvQXqA8PrWnlrAaT3G6uJQc8eoJCPUxvTh9hIhcKuUvR0ceemUPICsb8P70mGYlQ+QG2x127eBqOptN4ANrNAXGiaWXSZFfg82Mx7/YbuZQKI0oKmJmR+qbw=";

		byte[] decodedData = RSAUtils.decryptByPrivateKey(Base64.decodeBase64(encodedData), privateKey);
		String target = new String(decodedData);
		System.out.println("解密后文字: \r\n" + target);

		System.out.println(Base64.encodeBase64(data));

	}

	@Test
	public void test() throws Exception {
		System.err.println("公钥加密——私钥解密");
		String source = "这是一行没有任何意义的文字，你看完了等于没看，不是吗？";
		System.out.println("\r加密前文字：\r\n" + source);
		byte[] data = source.getBytes();
		byte[] encodedData = RSAUtils.encryptByPublicKey(data, publicKey);
		System.out.println("加密后文字：\r\n" + new String(encodedData));
		byte[] decodedData = RSAUtils.decryptByPrivateKey(encodedData, privateKey);
		String target = new String(decodedData);
		System.out.println("解密后文字: \r\n" + target);
	}

	@Test
	public void testSign() throws Exception {
		System.err.println("私钥加密——公钥解密");
		String source = "这是一行测试RSA数字签名的无意义文字";
		System.out.println("原文字：\r\n" + source);
		byte[] data = source.getBytes();
		byte[] encodedData = RSAUtils.encryptByPrivateKey(data, privateKey);
		System.out.println("加密后：\r\n" + new String(encodedData));
		byte[] decodedData = RSAUtils.decryptByPublicKey(encodedData, publicKey);
		String target = new String(decodedData);
		System.out.println("解密后: \r\n" + target);
		System.err.println("私钥签名——公钥验证签名");
		String sign = RSAUtils.sign(encodedData, privateKey);
		System.err.println("签名:\r" + sign);
		boolean status = RSAUtils.verify(encodedData, publicKey, sign);
		System.err.println("验证结果:\r" + status);
	}
}
