package com.xuguruogu.auth.dal.querycondition;

public class KssSoftKeyQueryCondition extends QueryCondition<KssSoftKeyQueryCondition> {

	/**
	 *
	 */
	private static final long serialVersionUID = 3534111102196083316L;
	/**  */

	private static final String adminid = "adminid";
	private static final String prefix = "prefix";
	private static final String cdkey = "cdkey";
	private static final String password = "password";
	private static final String ordernum = "ordernum";
	private static final String useduser = "useduser";

	public KssSoftKeyQueryCondition putAdminid(long adminid) {
		addIfExist(KssSoftKeyQueryCondition.adminid, adminid);
		return this;
	}

	public KssSoftKeyQueryCondition putPrefix(String prefix) {
		addIfExist(KssSoftKeyQueryCondition.prefix, prefix);
		return this;
	}

	public KssSoftKeyQueryCondition putCdkey(String cdkey) {
		addIfExist(KssSoftKeyQueryCondition.cdkey, cdkey);
		return this;
	}

	public KssSoftKeyQueryCondition putPassword(String password) {
		addIfExist(KssSoftKeyQueryCondition.password, password);
		return this;
	}

	public KssSoftKeyQueryCondition putOrdernum(String ordernum) {
		addIfExist(KssSoftKeyQueryCondition.ordernum, ordernum);
		return this;
	}

	public KssSoftKeyQueryCondition putUseduser(String useduser) {
		addIfExist(KssSoftKeyQueryCondition.useduser, useduser);
		return this;
	}
}