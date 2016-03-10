package com.xuguruogu.auth.dal.querycondition;

public class KssLogLoginQueryCondition extends QueryCondition<KssLogLoginQueryCondition> {

	/**  */
	private static final long serialVersionUID = 6807986218572200736L;

	private static final String adminid = "adminid";
	private static final String loginip = "loginip";
	private static final String desc = "desc";

	public KssLogLoginQueryCondition putAdminid(long adminid) {
		addIfExist(KssLogLoginQueryCondition.adminid, adminid);
		return this;
	}

	public KssLogLoginQueryCondition putLoginip(long loginip) {
		addIfExist(KssLogLoginQueryCondition.loginip, loginip);
		return this;
	}

	public KssLogLoginQueryCondition desc(boolean desc) {
		if (desc) {
			addIfExist(KssLogLoginQueryCondition.desc, "desc");
		}
		return this;
	}

}