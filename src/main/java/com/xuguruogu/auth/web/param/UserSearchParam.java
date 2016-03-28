package com.xuguruogu.auth.web.param;

import java.io.Serializable;

import com.xuguruogu.auth.dal.enums.UserStatusType;

public class UserSearchParam implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -6097234273936865640L;

	private Integer pageNo;
	private Integer pageSize;
	private Long adminid;
	private String username;
	private String cdkey;
	private String tag;
	private UserStatusType status;

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Long getAdminid() {
		return adminid;
	}

	public void setAdminid(Long adminid) {
		this.adminid = adminid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCdkey() {
		return cdkey;
	}

	public void setCdkey(String cdkey) {
		this.cdkey = cdkey;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public UserStatusType getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		if (null == status) {
			this.status = null;
			return;
		}
		for (UserStatusType s : UserStatusType.values()) {
			if (s.getCode() == status) {
				this.status = s;
				return;
			}
		}
		this.status = null;
	}

}
