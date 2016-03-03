package com.xuguruogu.auth.api.service;

import java.util.Map;

/**
 * @author benli.lbl 请求处理器
 */
public interface RequestHandler {
	/**
	 * @param param
	 * @return 结果映射值
	 */
	public Map<String, Object> doRequest(Map<String, Object> param);

	/**
	 * @return 处理器名称
	 */
	public String getName();
}
