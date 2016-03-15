package com.xuguruogu.auth.dal.querycondition;

import java.util.List;

public class KssAdminQueryCondition extends QueryCondition<KssAdminQueryCondition> {

	/**  */
	private static final long serialVersionUID = 2164395703661536854L;

	private static final String parentid = "parentid";
	private static final String adminid = "adminid";
	private static final String username = "username";
	private static final String ids = "ids";

	public KssAdminQueryCondition putParentid(long parentid) {
		addIfExist(KssAdminQueryCondition.parentid, parentid);
		return this;
	}

	public KssAdminQueryCondition putAdminid(long adminid) {
		addIfExist(KssAdminQueryCondition.adminid, adminid);
		return this;
	}

	public KssAdminQueryCondition putUsername(String username) {
		addIfExist(KssAdminQueryCondition.username, username);
		return this;
	}

	public KssAdminQueryCondition putIds(List<Long> ids) {
		addIfExist(KssAdminQueryCondition.ids, ids);
		return this;
	}
}
