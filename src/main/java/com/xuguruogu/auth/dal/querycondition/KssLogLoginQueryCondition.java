package com.xuguruogu.auth.dal.querycondition;

public class KssLogLoginQueryCondition extends QueryCondition<KssLogLoginQueryCondition> {

	/**  */
	private static final long serialVersionUID = 6807986218572200736L;

	private static final String adminid = "adminid";
	private static final String loginip = "loginip";

	public KssLogLoginQueryCondition putAdminid(Integer adminid) {
		addIfExist(KssLogLoginQueryCondition.adminid, adminid);
		return this;
	}

	public KssLogLoginQueryCondition putLoginip(Long loginip) {
		addIfExist(KssLogLoginQueryCondition.loginip, loginip);
		return this;
	}

}