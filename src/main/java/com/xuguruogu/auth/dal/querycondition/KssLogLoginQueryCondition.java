package com.xuguruogu.auth.dal.querycondition;

public class KssLogLoginQueryCondition extends QueryCondition<KssLogLoginQueryCondition> {

	/**  */
	private static final long serialVersionUID = 6807986218572200736L;

	private static final String adminid = "adminid";
	private static final String parentid = "parentid";

	public KssLogLoginQueryCondition putAdminid(Long adminid) {
		addIfExist(KssLogLoginQueryCondition.adminid, adminid);
		return this;
	}

	public KssLogLoginQueryCondition putParentid(Long parentid) {
		addIfExist(KssLogLoginQueryCondition.parentid, parentid);
		return this;
	}

}