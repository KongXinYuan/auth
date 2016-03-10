package com.xuguruogu.auth.dal.querycondition;

public class KssSoftQueryCondition extends QueryCondition<KssSoftQueryCondition> {

	/**  */
	private static final long serialVersionUID = 6807986218572200736L;

	private static final String softcode = "softcode";

	private static final String softname = "softname";

	public KssSoftQueryCondition putSoftcode(long softcode) {
		addIfExist(KssSoftQueryCondition.softcode, softcode);
		return this;
	}

	public KssSoftQueryCondition putSoftname(String softname) {
		addIfExist(KssSoftQueryCondition.softname, softname);
		return this;
	}

}