package com.xuguruogu.auth.dal.querycondition;

public class KssSoftKeyQueryCondition extends QueryCondition<KssSoftKeyQueryCondition> {

	private static final long serialVersionUID = 3534111102196083316L;
	private static final String adminid = "adminid";
	private static final String keysetid = "keysetid";
	private static final String useduser = "useduser";
	private static final String cdkey = "cdkey";
	private static final String tag = "tag";
	private static final String ordernum = "ordernum";
	private static final String lock = "lock";
	private static final String used = "used";

	public KssSoftKeyQueryCondition putAdminid(long adminid) {
		addIfExist(KssSoftKeyQueryCondition.adminid, adminid);
		return this;
	}

	public KssSoftKeyQueryCondition putKeysetid(Long keysetid) {
		addIfExist(KssSoftKeyQueryCondition.keysetid, keysetid);
		return this;
	}

	public KssSoftKeyQueryCondition putUseduser(String useduser) {
		addIfExist(KssSoftKeyQueryCondition.useduser, useduser);
		return this;
	}

	public KssSoftKeyQueryCondition putCdkey(String cdkey) {
		addIfExist(KssSoftKeyQueryCondition.cdkey, cdkey);
		return this;
	}

	public KssSoftKeyQueryCondition putTag(String tag) {
		addIfExist(KssSoftKeyQueryCondition.tag, tag);
		return this;
	}

	public KssSoftKeyQueryCondition putOrdernum(String ordernum) {
		addIfExist(KssSoftKeyQueryCondition.ordernum, ordernum);
		return this;
	}

	public KssSoftKeyQueryCondition putLock(Boolean lock) {
		addIfExist(KssSoftKeyQueryCondition.lock, lock);
		return this;
	}

	public KssSoftKeyQueryCondition putUsed(Boolean used) {
		addIfExist(KssSoftKeyQueryCondition.used, used);
		return this;
	}

}