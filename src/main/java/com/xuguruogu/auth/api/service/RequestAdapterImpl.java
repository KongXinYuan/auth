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
import com.xuguruogu.auth.config.AuthConfigHolder;
import com.xuguruogu.auth.interceptor.ApiException;
import com.xuguruogu.auth.util.RSAUtils;

@Component("requestAdapter")
public class RequestAdapterImpl implements RequestAdapter {

	@Autowired
	ObjectMapper objectMapper;

	private Map<String, RequestHandler> handlerMap;

	private static Logger logger = LoggerFactory.getLogger(RequestAdapterImpl.class);

	@PostConstruct
	public void initMethod() {
		handlerMap = new HashMap<String, RequestHandler>();
		handlerMap.put("register", new UserRegisterHandler());
		handlerMap.put("recharge", new UserRechargeHandler());
		handlerMap.put("login", new UserLoginHandler());
		handlerMap.put("valid", new UserValidHandler());
	}

	@SuppressWarnings("unchecked")
	@Override
	public String doRequestAdapt(String req) {

		Map<String, Object> param = new HashMap<String, Object>();

		/*
		 * 解密
		 */
		try {
			param = objectMapper
					.readValue(
							new String(RSAUtils.decryptByPrivateKey(Base64.decodeBase64(req),
									AuthConfigHolder.getPrivateKey())), param.getClass());
		} catch (Exception e) {
			logger.warn("err:" + e.getMessage());
			throw new ApiException("请求数据错误");
		}

		/*
		 * 寻找方法
		 */
		RequestHandler method = handlerMap.get("method");

		if (null == method) {
			logger.warn("无效请求方法: " + method);
			throw new ApiException("无效请求");
		}

		/*
		 * 调用方法
		 */
		Map<String, Object> resultMap = method.doRequest(param);

		/*
		 * 加密
		 */
		try {
			StringWriter strWriter = new StringWriter();
			objectMapper.writeValue(strWriter, resultMap);
			// Json->RSA->Base64
			return Base64.encodeBase64String(RSAUtils.encryptByPublicKey(strWriter.toString().getBytes("UTF-8"),
					AuthConfigHolder.getPublicKey()));
		} catch (Exception e) {
			logger.warn("err:" + e.getMessage());
			throw new ApiException("请求结果生成错误");
		}

	}

}
