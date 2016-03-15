package com.xuguruogu.auth.web.param;

import java.io.Serializable;

public class CDKeySearchParam implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -6097234273936865640L;

	private Integer pageNo;
	private Integer pageSize;
	private long keysetid;
	private String useduser;
	private String cdkey;
	private String tag;
	private String ordernum;
	private Boolean lock;
	private Boolean used;
	private Long adminid;

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

	public long getKeysetid() {
		return keysetid;
	}

	public void setKeysetid(long keysetid) {
		this.keysetid = keysetid;
	}

	public String getUseduser() {
		return useduser;
	}

	public void setUseduser(String useduser) {
		this.useduser = useduser;
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

	public Boolean getLock() {
		return lock;
	}

	public void setLock(Boolean lock) {
		this.lock = lock;
	}

	public Boolean getUsed() {
		return used;
	}

	public void setUsed(Boolean used) {
		this.used = used;
	}

	public Long getAdminid() {
		return adminid;
	}

	public void setAdminid(Long adminid) {
		this.adminid = adminid;
	}

}
