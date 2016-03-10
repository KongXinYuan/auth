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
