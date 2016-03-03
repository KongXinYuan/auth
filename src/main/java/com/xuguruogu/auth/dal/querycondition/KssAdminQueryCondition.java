package com.xuguruogu.auth.dal.querycondition;

public class KssAdminQueryCondition extends QueryCondition<KssAdminQueryCondition> {

	/**  */
	private static final long serialVersionUID = 2164395703661536854L;

	private static final String parentid = "parentid";
	private static final String username = "username";

	public KssAdminQueryCondition putParentid(Integer parentid) {
		addIfExist(KssAdminQueryCondition.parentid, parentid);
		return this;
	}

	public KssAdminQueryCondition putUsername(String username) {
		addIfExist(KssAdminQueryCondition.username, username);
		return this;
	}

}
