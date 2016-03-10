package com.xuguruogu.auth.dal.querycondition;

public class KssKeySetQueryCondition extends QueryCondition<KssKeySetQueryCondition> {

	/**  */
	private static final long serialVersionUID = 6807986218572200736L;

	private static final String softid = "softid";
	private static final String keyname = "keyname";

	public KssKeySetQueryCondition putSoftid(long softid) {
		addIfExist(KssKeySetQueryCondition.softid, softid);
		return this;
	}

	public KssKeySetQueryCondition putKeyname(String keyname) {
		addIfExist(KssKeySetQueryCondition.keyname, keyname);
		return this;
	}

}