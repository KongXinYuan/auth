package com.xuguruogu.auth.dal.querycondition;

import java.util.List;

import com.xuguruogu.auth.dal.enums.SoftStatusType;

public class KssSoftQueryCondition extends QueryCondition<KssSoftQueryCondition> {

	/**  */
	private static final long serialVersionUID = 6807986218572200736L;

	private static final String softcode = "softcode";

	private static final String softname = "softname";

	private static final String adminid = "adminid";

	private static final String status = "status";

	public KssSoftQueryCondition putSoftcode(long softcode) {
		addIfExist(KssSoftQueryCondition.softcode, softcode);
		return this;
	}

	public KssSoftQueryCondition putSoftname(String softname) {
		addIfNutBlank(KssSoftQueryCondition.softname, softname);
		return this;
	}

	public KssSoftQueryCondition putAdminid(Long adminid) {
		addIfExist(KssSoftQueryCondition.adminid, adminid);
		return this;
	}

	public KssSoftQueryCondition putStatus(List<SoftStatusType> status) {
		addIfExist(KssSoftQueryCondition.status, status);
		return this;
	}

}