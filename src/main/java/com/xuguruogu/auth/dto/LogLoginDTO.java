package com.xuguruogu.auth.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class LogLoginDTO implements Serializable {

	/**  */
	private static final long serialVersionUID = -5058138313226583283L;

	private long id;

	/** 管理员id */
	private Integer adminid;

	/** ip地址 */
	private Integer loginip;

	/** 登录时间 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	private Date logintime;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

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
