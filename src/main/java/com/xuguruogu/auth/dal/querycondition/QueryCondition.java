package com.xuguruogu.auth.dal.querycondition;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public abstract class QueryCondition<Q extends QueryCondition<?>> implements Serializable {
	/**  */
	private static final long serialVersionUID = -8241459673022299671L;

	private static final String KEY_PAGINDEX = "startIndex";
	private static final String KEY_PAGESIZE = "pageSize";

	protected Map<String, Object> params = new HashMap<String, Object>();

	private int pageNo;

	private int pageSize;

	@SuppressWarnings("unchecked")
	public final Q pagination(Integer pageNo, Integer pageSize) {

		if (null == pageNo || null == pageSize) {
			pageNo = 1;
			pageSize = 20;
		}

		if (pageSize > 200) {
			pageSize = 200;
		}

		if (pageNo > 0 && pageSize > 0) {
			int startIndex = (pageNo - 1) * pageSize;

			addIfExist(KEY_PAGINDEX, startIndex);
			addIfExist(KEY_PAGESIZE, pageSize);

			this.pageNo = pageNo;
			this.pageSize = pageSize;
		}
		return (Q) this;
	}

	public final Q nextPage() {

		return pagination(pageNo + 1, pageSize);
	}

	protected void addIfNutBlank(String key, String value) {

		if (StringUtils.isNoneBlank(value)) {
			params.put(key, value);
		}
	}

	protected <P extends Object> void addIfExist(String key, P value) {

		if (null != value) {
			params.put(key, value);
		}
	}

	public Map<String, Object> asMap() {
		return params;
	}

	@SuppressWarnings("unchecked")
	public Q reset() {
		params.clear();
		return (Q) this;
	}

}
