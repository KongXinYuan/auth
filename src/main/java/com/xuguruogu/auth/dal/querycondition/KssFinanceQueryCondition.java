package com.xuguruogu.auth.dal.querycondition;

public class KssFinanceQueryCondition extends QueryCondition<KssFinanceQueryCondition> {

	/**  */
	private static final long serialVersionUID = 2164395703661536854L;

	private static final String adminid = "adminid";

	public KssFinanceQueryCondition putAdminid(long adminid) {
		addIfExist(KssFinanceQueryCondition.adminid, adminid);
		return this;
	}

}
