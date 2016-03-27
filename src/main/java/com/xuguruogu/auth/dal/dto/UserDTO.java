package com.xuguruogu.auth.dal.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class UserDTO implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 549203465062431958L;

	private String adminname;
	private String username;
	private String password;
	// 累计充值时间
	private BigDecimal cday;
	private Date addtime;
	private Date endtime;
	private Date lastlogintime;
	private long lastloginip;
	// 充值计数
	private long rechargetimes;
	private String status;

	public String getAdminname() {
		return adminname;
	}

	public void setAdminname(String adminname) {
		this.adminname = adminname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public BigDecimal getCday() {
		return cday;
	}

	public void setCday(BigDecimal cday) {
		this.cday = cday;
	}

	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public Date getEndtime() {
		return endtime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}

	public Date getLastlogintime() {
		return lastlogintime;
	}

	public void setLastlogintime(Date lastlogintime) {
		this.lastlogintime = lastlogintime;
	}

	public long getLastloginip() {
		return lastloginip;
	}

	public void setLastloginip(long lastloginip) {
		this.lastloginip = lastloginip;
	}

	public long getRechargetimes() {
		return rechargetimes;
	}

	public void setRechargetimes(long rechargetimes) {
		this.rechargetimes = rechargetimes;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
