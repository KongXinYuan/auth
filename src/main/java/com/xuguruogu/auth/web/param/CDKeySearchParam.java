package com.xuguruogu.auth.web.param;

import java.io.Serializable;

import com.xuguruogu.auth.dal.enums.CDKeyStatusType;

public class CDKeySearchParam implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -6097234273936865640L;

	private Integer pageNo;
	private Integer pageSize;
	private Long adminid;
	private Long keysetid;
	private Long userid;
	private String username;
	private String cdkey;
	private String tag;
	private String ordernum;
	private CDKeyStatusType status;

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

	public Long getKeysetid() {
		return keysetid;
	}

	public void setKeysetid(Long keysetid) {
		this.keysetid = keysetid;
	}

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
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

	public String getOrdernum() {
		return ordernum;
	}

	public void setOrdernum(String ordernum) {
		this.ordernum = ordernum;
	}

	public CDKeyStatusType getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		if (null == status) {
			this.status = null;
			return;
		}
		for (CDKeyStatusType sts : CDKeyStatusType.values()) {
			if (sts.getCode() == status) {
				this.status = sts;
				return;
			}
		}
		this.status = null;
	}

}
