package com.xuguruogu.auth.dal.querycondition;

public class KssOrderQueryCondition extends QueryCondition<KssOrderQueryCondition> {

	/**  */
	private static final long serialVersionUID = 6807986218572200736L;

	private static final String ordernum = "ordernum";
	private static final String softid = "softid";
	private static final String adminid = "adminid";
	private static final String keysetid = "keysetid";

	public KssOrderQueryCondition putOrdernum(String ordernum) {
		addIfExist(KssOrderQueryCondition.ordernum, ordernum);
		return this;
	}

	public KssOrderQueryCondition putSoftid(long softid) {
		addIfExist(KssOrderQueryCondition.softid, softid);
		return this;
	}

	public KssOrderQueryCondition putAdminid(long adminid) {
		addIfExist(KssOrderQueryCondition.adminid, adminid);
		return this;
	}

	public KssOrderQueryCondition putKeysetid(long keysetid) {
		addIfExist(KssOrderQueryCondition.keysetid, keysetid);
		return this;
	}
}