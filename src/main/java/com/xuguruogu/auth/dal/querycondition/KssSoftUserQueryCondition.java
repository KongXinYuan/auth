package com.xuguruogu.auth.dal.querycondition;

import java.util.List;

import com.xuguruogu.auth.dal.enums.UserStatusType;

public class KssSoftUserQueryCondition extends QueryCondition<KssSoftUserQueryCondition> {

	/**  */
	private static final long serialVersionUID = 6807986218572200736L;

	private static final String userid = "userid";
	private static final String username = "username";
	private static final String adminid = "adminid";
	private static final String parentid = "parentid";
	private static final String tag = "tag";
	private static final String status = "status";

	public KssSoftUserQueryCondition putUserid(Long userid) {
		addIfExist(KssSoftUserQueryCondition.userid, userid);
		return this;
	}

	public KssSoftUserQueryCondition putUsername(String username) {
		addIfNutBlank(KssSoftUserQueryCondition.username, username);
		return this;
	}

	public KssSoftUserQueryCondition putAdminid(Long adminid) {
		addIfExist(KssSoftUserQueryCondition.adminid, adminid);
		return this;
	}

	public KssSoftUserQueryCondition putParentid(Long parentid) {
		addIfExist(KssSoftUserQueryCondition.parentid, parentid);
		return this;
	}

	public KssSoftUserQueryCondition putTag(String tag) {
		addIfNutBlank(KssSoftUserQueryCondition.tag, tag);
		return this;
	}

	public KssSoftUserQueryCondition putStatus(List<UserStatusType> status) {
		addIfExist(KssSoftUserQueryCondition.status, status);
		return this;
	}

}