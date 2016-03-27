package com.xuguruogu.auth.dal.querycondition;

import java.util.List;

import com.xuguruogu.auth.dal.enums.KeySetStatusType;

public class KssKeySetQueryCondition extends QueryCondition<KssKeySetQueryCondition> {

	/**  */
	private static final long serialVersionUID = 6807986218572200736L;

	private static final String softid = "softid";
	private static final String keyname = "keyname";
	private static final String adminid = "adminid";
	private static final String status = "status";

	public KssKeySetQueryCondition putSoftid(Long softid) {
		addIfExist(KssKeySetQueryCondition.softid, softid);
		return this;
	}

	public KssKeySetQueryCondition putKeyname(String keyname) {
		addIfNutBlank(KssKeySetQueryCondition.keyname, keyname);
		return this;
	}

	public KssKeySetQueryCondition putAdminid(Long adminid) {
		addIfExist(KssKeySetQueryCondition.adminid, adminid);
		return this;
	}

	public KssKeySetQueryCondition putStatus(List<KeySetStatusType> status) {
		addIfExist(KssKeySetQueryCondition.status, status);
		return this;
	}
}