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
	private Integer adminid;

	/** ip地址 */
	private Integer loginip;

	/** 登录时间 */
	private Date logintime;

	public Integer getAdminid() {
		return adminid;
	}

	public void setAdminid(Integer adminid) {
		this.adminid = adminid;
	}

	public Integer getLoginip() {
		return loginip;
	}

	public void setLoginip(Integer loginip) {
		this.loginip = loginip;
	}

	public Date getLogintime() {
		return logintime;
	}

	public void setLogintime(Date logintime) {
		this.logintime = logintime;
	}

}
