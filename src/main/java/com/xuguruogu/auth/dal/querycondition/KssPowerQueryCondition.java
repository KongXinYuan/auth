package com.xuguruogu.auth.dal.querycondition;

public class KssPowerQueryCondition extends QueryCondition<KssPowerQueryCondition> {

	/**  */
	private static final long serialVersionUID = 6807986218572200736L;

	private static final String keysetid = "keysetid";
	private static final String softid = "softid";
	private static final String adminid = "adminid";

	public KssPowerQueryCondition putKeysetid(Long keysetid) {
		addIfExist(KssPowerQueryCondition.keysetid, keysetid);
		return this;
	}

	public KssPowerQueryCondition putSoftid(Long softid) {
		addIfExist(KssPowerQueryCondition.softid, softid);
		return this;
	}

	public KssPowerQueryCondition putAdminid(Long adminid) {
		addIfExist(KssPowerQueryCondition.adminid, adminid);
		return this;
	}

}