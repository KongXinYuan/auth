package com.xuguruogu.auth.dal.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 用户摘要模型
 *
 * @author benli.lbl
 * @version $Id: UserSummaryDTO.java, v 0.1 Aug 10, 2015 10:56:37 PM benli.lbl
 *          Exp $
 */
public class AdminDTO implements Serializable {

	/**  */
	private static final long serialVersionUID = 2623289619052395315L;

	/** 用户id */
	private long id;

	/** 上级id */
	private String parentname;

	private String role;

	/** 用户名 */
	private String username;

	/** 添加时间 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	private Date addtime;

	/** 上次登录时间 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	private Date lastlogintime;

	/** 上次登录ip */
	private String lastloginip;

	/** 当前余额 */
	private BigDecimal money;

	/** 累计金额 */
	private BigDecimal exmoney;

	private String status;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getParentname() {
		return parentname;
	}

	public void setParentname(String parentname) {
		this.parentname = parentname;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public Date getLastlogintime() {
		return lastlogintime;
	}

	public void setLastlogintime(Date lastlogintime) {
		this.lastlogintime = lastlogintime;
	}

	public String getLastloginip() {
		return lastloginip;
	}

	public void setLastloginip(String lastloginip) {
		this.lastloginip = lastloginip;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public BigDecimal getExmoney() {
		return exmoney;
	}

	public void setExmoney(BigDecimal exmoney) {
		this.exmoney = exmoney;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
