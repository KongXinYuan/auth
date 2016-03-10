package com.xuguruogu.auth.dal.dataobject;

import java.math.BigDecimal;

/**
 * 软件对象
 *
 * @author benli.lbl
 *
 */
public class KssSoftRechargeDO extends EntityWithSeg {

	/**
	 *
	 */
	private static final long serialVersionUID = -7737146852706905697L;

	private long adminid;
	private String username;
	private BigDecimal oldcday;
	private BigDecimal newcday;
	private String cdkey;

	public long getAdminid() {
		return adminid;
	}

	public void setAdminid(long adminid) {
		this.adminid = adminid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public BigDecimal getOldcday() {
		return oldcday;
	}

	public void setOldcday(BigDecimal oldcday) {
		this.oldcday = oldcday;
	}

	public BigDecimal getNewcday() {
		return newcday;
	}

	public void setNewcday(BigDecimal newcday) {
		this.newcday = newcday;
	}

	public String getCdkey() {
		return cdkey;
	}

	public void setCdkey(String cdkey) {
		this.cdkey = cdkey;
	}

}
