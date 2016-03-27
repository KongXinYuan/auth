package com.xuguruogu.auth.dal.querycondition;

public class KssSoftRechargeQueryCondition extends QueryCondition<KssSoftRechargeQueryCondition> {

	/**
	 *
	 */
	private static final long serialVersionUID = -1491351676461480754L;
	/**  */
	private static final String username = "username";
	private static final String adminid = "adminid";
	private static final String cdkey = "cdkey";

	public KssSoftRechargeQueryCondition putAdminid(long adminid) {
		addIfExist(KssSoftRechargeQueryCondition.adminid, adminid);
		return this;
	}

	public KssSoftRechargeQueryCondition putUsername(String username) {
		addIfNutBlank(KssSoftRechargeQueryCondition.username, username);
		return this;
	}

	public KssSoftRechargeQueryCondition putCdkey(String cdkey) {
		addIfNutBlank(KssSoftRechargeQueryCondition.cdkey, cdkey);
		return this;
	}

}
