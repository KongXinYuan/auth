package com.xuguruogu.auth.dal.dataobject;

import java.util.Date;

/**
 * 管理员登录日志
 *
 * @author benli.lbl
 *
 */
public class KssLogLoginDO extends Entity {

	/**  */
	private static final long serialVersionUID = 2500557805132606740L;

	/** 管理员id */
	private long adminid;

	private String username;
	private long parentid;

	private String parentname;

	/** ip地址 */
	private int loginip;

	/** 登录时间 */
	private Date logintime;

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

	public long getParentid() {
		return parentid;
	}

	public void setParentid(long parentid) {
		this.parentid = parentid;
	}

	public String getParentname() {
		return parentname;
	}

	public void setParentname(String parentname) {
		this.parentname = parentname;
	}

	public int getLoginip() {
		return loginip;
	}

	public void setLoginip(int loginip) {
		this.loginip = loginip;
	}

	public Date getLogintime() {
		return logintime;
	}

	public void setLogintime(Date logintime) {
		this.logintime = logintime;
	}

}
