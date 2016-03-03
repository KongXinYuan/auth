package com.xuguruogu.auth.test.rsa;

import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.junit.Test;

import com.xuguruogu.auth.util.RSAUtils;

public class RSATester {

	static String publicKey;
	static String privateKey;

	@Test
	public void testGenerateKey() throws Exception {
		Map<String, Object> keyMap = RSAUtils.genKeyPair();
		publicKey = RSAUtils.getPublicKey(keyMap);
		privateKey = RSAUtils.getPrivateKey(keyMap);
		System.err.println("lenth:" + publicKey.length() + "\n公钥: \n" + publicKey);
		System.err.println("lenth:" + privateKey.length() + "\n私钥： \n" + privateKey);
		System.out.println();
	}

	@Test
	public void testHello() throws Exception {
		publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCtu6E9nrWLbqO7avJqdGWFRYwvFKpwz1MHAQZgqAJ0Yz5s9OpZL1ZOTveqC1tYplctmD6KWUUsPIWc35LXYJNkL52uQmvkGUR67zPverG_HMQqW-20rwNKpiGHJGOseBfkwSyKT1HK8RJ8-h-cBi8B7KZjbBnlyF_MzpHilSv7FQIDAQAB";
		privateKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAK27oT2etYtuo7tq8mp0ZYVFjC8UqnDPUwcBBmCoAnRjPmz06lkvVk5O96oLW1imVy2YPopZRSw8hZzfktdgk2Qvna5Ca-QZRHrvM-96sb8cxCpb7bSvA0qmIYckY6x4F-TBLIpPUcrxEnz6H5wGLwHspmNsGeXIX8zOkeKVK_sVAgMBAAECgYANVYdMmW_JodZDPjQY45IfJnVZ77YdLmxq7zM0G_EESdGyvyF1cuKm4_yyOmjqZunt_-9c4vkiKgr_PVGh4jGtsAzj54RkYk0H7DDXa0ga4d2H1BOzmgevhEoRiuJBDt7oBzAeg825--5Q9fWo8mY7fiVy4KxiBUJ9oeEMHBiQgQJBAPaZX44LqUe8DuQ-l0AJZ2yb8IpZwi9bHnxg9YVZhpi5bgih-7br2rBBrDlAV14iCoR0O5ayPri2jR6C9Q3uIjECQQC0WyLQDCgn4UXPRV7w4nSzFoe1GdEGDhYtih7BJj6m4LVRA5l9nYa3FFuuhLs86IgHwOnwhLqfdp02ltEsNSolAkEA3OCBu0xsoqkqOXxqo1wI_Dos2O8OxLX793ItV_TZX0Bv82GLgct9xPP93AXqe5FIFF275f3fujkg65xm_MQ4cQJADu9ZE6deD-dcs4xID2SBS287L6kCoSC7f2vnnhD_sSv8d8f3huprhG-gI_SD2Mhcs1-rP6qOFbRp0mJs0d9ZLQJBALc2xEwhJjG-hqHVTPMs-vtPQx99nHrE3DpeBSOlvh8j7jxXH5EayA5Ri9nRwgajTNpY40ySYTWktNvSEr4a6hI";

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

}
