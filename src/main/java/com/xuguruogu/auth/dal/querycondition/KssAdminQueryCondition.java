package com.xuguruogu.auth.dal.querycondition;

import java.util.List;

import com.xuguruogu.auth.dal.enums.AdminStatusType;

public class KssAdminQueryCondition extends QueryCondition<KssAdminQueryCondition> {

	/**  */
	private static final long serialVersionUID = 2164395703661536854L;

	private static final String adminid = "adminid";
	private static final String username = "username";
	private static final String status = "status";

	public KssAdminQueryCondition putAdminid(long adminid) {
		addIfExist(KssAdminQueryCondition.adminid, adminid);
		return this;
	}

	public KssAdminQueryCondition putUsername(String username) {
		addIfNutBlank(KssAdminQueryCondition.username, username);
		return this;
	}

	public KssAdminQueryCondition putStatus(List<AdminStatusType> status) {
		addIfExist(KssAdminQueryCondition.status, status);
		return this;
	}
}
