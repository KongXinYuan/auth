package com.xuguruogu.auth.test.rsa;

import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.junit.Test;

public class TestAES {

	@Test
	public void tst() throws Exception {

		SecretKeySpec key = new SecretKeySpec(Base64.decodeBase64("XScjv/NQhyFpfxc152UiPw=="), "AES");
		// 使用iv中的字节作为IV来构造一个 算法参数。
		AlgorithmParameterSpec iv = new IvParameterSpec(Base64.decodeBase64("SjuKzVKJQR9nevcnnilXZw=="));
		// 生成一个实现指定转换的 Cipher 对象
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		// 用密钥和一组算法参数初始化此 cipher
		cipher.init(Cipher.ENCRYPT_MODE, key, iv);
		// 加密并转换成Base64字符串
		String result = Base64.encodeBase64String(cipher.doFinal("12345678".getBytes()));

		System.out.println(result);

	}
}
