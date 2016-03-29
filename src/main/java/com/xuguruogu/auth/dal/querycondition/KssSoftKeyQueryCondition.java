package com.xuguruogu.auth.dal.querycondition;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.xuguruogu.auth.dal.enums.CDKeyStatusType;

public class KssSoftKeyQueryCondition extends QueryCondition<KssSoftKeyQueryCondition> {

	private static final long serialVersionUID = 3534111102196083316L;
	private static final String adminid = "adminid";
	private static final String parentid = "parentid";
	private static final String keysetid = "keysetid";
	private static final String userid = "userid";
	private static final String username = "username";
	private static final String cdkey = "cdkey";
	private static final String tag = "tag";
	private static final String ordernum = "ordernum";
	private static final String status = "status";

	public KssSoftKeyQueryCondition putAdminid(Long adminid) {
		addIfExist(KssSoftKeyQueryCondition.adminid, adminid);
		return this;
	}

	public KssSoftKeyQueryCondition putParentid(Long parentid) {
		addIfExist(KssSoftKeyQueryCondition.parentid, parentid);
		return this;
	}

	public KssSoftKeyQueryCondition putKeysetid(Long keysetid) {
		addIfExist(KssSoftKeyQueryCondition.keysetid, keysetid);
		return this;
	}

	public KssSoftKeyQueryCondition putUserid(Long userid) {
		addIfExist(KssSoftKeyQueryCondition.userid, userid);
		return this;
	}

	public KssSoftKeyQueryCondition putUsername(String username) {
		if (StringUtils.isNotBlank(username)) {
			addIfNutBlank(KssSoftKeyQueryCondition.username, username + '%');
		}
		return this;
	}

	public KssSoftKeyQueryCondition putCdkey(String cdkey) {
		addIfNutBlank(KssSoftKeyQueryCondition.cdkey, cdkey);
		return this;
	}

	public KssSoftKeyQueryCondition putTag(String tag) {
		addIfNutBlank(KssSoftKeyQueryCondition.tag, tag);
		return this;
	}

	public KssSoftKeyQueryCondition putOrdernum(String ordernum) {
		addIfNutBlank(KssSoftKeyQueryCondition.ordernum, ordernum);
		return this;
	}

	public KssSoftKeyQueryCondition putStatus(List<CDKeyStatusType> status) {
		addIfExist(KssSoftKeyQueryCondition.status, status);
		return this;
	}

}