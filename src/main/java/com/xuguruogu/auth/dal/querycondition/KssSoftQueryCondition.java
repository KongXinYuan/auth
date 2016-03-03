package com.xuguruogu.auth.dal.querycondition;


public class KssSoftQueryCondition extends QueryCondition<KssSoftQueryCondition> {

	/**  */
	private static final long serialVersionUID = 6807986218572200736L;

	private static final String softcode = "softcode";

	public KssSoftQueryCondition putSoftcode(Integer softcode) {
		addIfExist(KssSoftQueryCondition.softcode, softcode);
		return this;
	}

}