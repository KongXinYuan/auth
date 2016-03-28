package com.xuguruogu.auth.api.service;

import java.io.IOException;
import java.io.StringWriter;
import java.security.spec.AlgorithmParameterSpec;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xuguruogu.auth.api.param.ReqParam;
import com.xuguruogu.auth.dal.dataobject.KssSoftDO;
import com.xuguruogu.auth.service.SoftManager;
import com.xuguruogu.auth.util.RSAUtils;

/**
 * @author benli.lbl 请求适配器
 */
@Component("requestAdapter")
public class RequestAdapter {

	@Autowired
	ObjectMapper objectMapper;

	@Autowired
	private SoftManager softManager;

	private Map<String, RequestHandler> handlerMap;

	private static Logger logger = LoggerFactory.getLogger(RequestAdapter.class);

	@Autowired
	private RequestHandler userSignUpHandler;
	@Autowired
	private RequestHandler userRechargeHandler;
	@Autowired
	private RequestHandler userSignInHandler;
	@Autowired
	private RequestHandler userValidHandler;
	@Autowired
	private RequestHandler userChangePwdHandler;

	@PostConstruct
	public void initMethod() {
		handlerMap = new HashMap<String, RequestHandler>();
		handlerMap.put("signup", userSignUpHandler);
		handlerMap.put("recharge", userRechargeHandler);
		handlerMap.put("signin", userSignInHandler);
		handlerMap.put("valid", userValidHandler);
		handlerMap.put("changepwd", userChangePwdHandler);
	}

	@SuppressWarnings("unchecked")
	public String doRequestAdapt(ReqParam req) {

		KssSoftDO soft = softManager.detail(req.getSoftid());
		Assert.notNull(soft, "软件编号不存在");
		Assert.isTrue(soft.getSoftkey().equals(req.getSoftkey()), "softkey错误");

		// 解密
		Map<String, String> param = new HashMap<String, String>();

		String decrpttext = null;
		try {
			// Base64->RSA
			decrpttext = new String(RSAUtils.decryptByPrivateKey(Base64.decodeBase64(req.getReq()), soft.getPrivkey()));
		} catch (Exception e) {
			logger.warn("RSA解密失败", e);
			throw new IllegalArgumentException("RSA解密失败", e);
		}

		try {
			param = objectMapper.readValue(decrpttext, param.getClass());
		} catch (IOException e) {
			logger.warn("json格式解析失败", e);
			throw new IllegalArgumentException("json格式解析失败", e);
		}

		// 寻找方法
		String reqMethod = param.get("method");
		Assert.hasText(reqMethod, "请设置method字段");
		RequestHandler method = handlerMap.get(reqMethod);
		Assert.notNull(method, "无效method字段");

		// 校验字符串
		String verify = param.get("verify");
		Assert.hasText(verify, "无效verify字段");

		// key
		String keystr = param.get("key");
		Assert.hasText(keystr, "无效key字段");
		// iv
		String ivstr = param.get("iv");
		Assert.hasText(ivstr, "无效iv字段");

		// 调用方法
		Map<String, Object> resultMap = method.doRequest(param, soft);
		Assert.notNull(resultMap, "调用方法失败");
		// 返回校验字符串
		resultMap.put("verify", verify);

		try {
			StringWriter strWriter = new StringWriter();
			objectMapper.writeValue(strWriter, resultMap);

			SecretKeySpec key = new SecretKeySpec(Base64.decodeBase64(keystr), "AES");
			// 使用iv中的字节作为IV来构造一个 算法参数。
			AlgorithmParameterSpec iv = new IvParameterSpec(Base64.decodeBase64(ivstr));
			// 生成一个实现指定转换的 Cipher 对象
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			// 用密钥和一组算法参数初始化此 cipher
			cipher.init(Cipher.ENCRYPT_MODE, key, iv);
			// 加密并转换成Base64字符串
			return Base64.encodeBase64String(cipher.doFinal(strWriter.toString().getBytes("UTF-8")));
		} catch (Exception e) {
			logger.warn("请求结果生成错误", e);
			throw new IllegalArgumentException("请求结果生成错误", e);
		}
	}
}
