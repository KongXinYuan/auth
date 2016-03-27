package com.xuguruogu.auth.api.service;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xuguruogu.auth.dal.dataobject.KssSoftDO;
import com.xuguruogu.auth.interceptor.KssException;
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

	@PostConstruct
	public void initMethod() {
		handlerMap = new HashMap<String, RequestHandler>();
		handlerMap.put("register", new UserRegisterHandler());
		handlerMap.put("recharge", new UserRechargeHandler());
		handlerMap.put("login", new UserLoginHandler());
		handlerMap.put("valid", new UserValidHandler());
	}

	@SuppressWarnings("unchecked")
	public String doRequestAdapt(long softcode, long ip, String req) {

		KssSoftDO soft = softManager.selectBySoftcode(softcode);
		if (null == soft) {
			throw new KssException("软件编号不存在");
		}

		/*
		 * 解密
		 */
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("ip", ip);
		try {
			// Base64->RSA->Json
			param = objectMapper.readValue(
					new String(RSAUtils.decryptByPrivateKey(Base64.decodeBase64(req), soft.getPrivkey())),
					param.getClass());
		} catch (Exception e) {
			logger.warn("err:" + e.getMessage());
			throw new KssException("请求数据错误");
		}

		/*
		 * 寻找方法
		 */
		String reqMethod = (String) param.get("method");
		if (null == reqMethod || reqMethod.isEmpty()) {
			throw new KssException("未设置请求方法");
		}

		RequestHandler method = handlerMap.get(reqMethod);
		if (null == method) {
			throw new KssException("无效请求方法");
		}

		// 校验字符串
		String reqVerify = (String) param.get("verify");
		if (null == reqVerify || reqVerify.isEmpty()) {
			throw new KssException("未设置校验字符串");
		}

		/*
		 * 调用方法
		 */
		Map<String, Object> resultMap = method.doRequest(param, soft);

		if (null == resultMap) {
			throw new KssException("方法返回null结果");
		}
		// 返回校验字符串
		resultMap.put("verify", reqVerify);

		/*
		 * 加密
		 */
		try {
			StringWriter strWriter = new StringWriter();
			objectMapper.writeValue(strWriter, resultMap);
			// Json->RSA->Base64
			return Base64.encodeBase64URLSafeString(
					RSAUtils.encryptByPrivateKey(strWriter.toString().getBytes("UTF-8"), soft.getPrivkey()));
		} catch (Exception e) {
			throw new KssException("请求结果生成错误");
		}

	}
}
