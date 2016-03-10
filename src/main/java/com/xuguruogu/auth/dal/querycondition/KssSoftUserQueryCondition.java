package com.xuguruogu.auth.dal.querycondition;

public class KssSoftUserQueryCondition extends QueryCondition<KssSoftUserQueryCondition> {

	/**  */
	private static final long serialVersionUID = 6807986218572200736L;

	private static final String username = "username";
	private static final String adminid = "adminid";

	public KssSoftUserQueryCondition putAdminid(long adminid) {
		addIfExist(KssSoftUserQueryCondition.adminid, adminid);
		return this;
	}

	public KssSoftUserQueryCondition putUsername(String username) {
		addIfExist(KssSoftUserQueryCondition.username, username);
		return this;
	}

}