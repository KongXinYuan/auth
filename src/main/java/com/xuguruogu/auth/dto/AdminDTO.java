package com.xuguruogu.auth.dto;

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
	private Integer parentid;

	private Integer level;

	/** 用户名 */
	private String username;

	/** 锁定 */
	private Boolean islock;

	/** 添加时间 */
	private Date addtime;

	/** 上次登录时间 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	private Date lastlogintime;

	/** 上次登录ip */
	private Long lastloginip;

	/** 权限列表 */
	private String powerlist;

	/** 当前余额 */
	private BigDecimal money;

	/** 累计金额 */
	private BigDecimal exmoney;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Integer getParentid() {
		return parentid;
	}

	public void setParentid(Integer parentid) {
		this.parentid = parentid;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Boolean getIslock() {
		return islock;
	}

	public void setIslock(Boolean islock) {
		this.islock = islock;
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

	public Long getLastloginip() {
		return lastloginip;
	}

	public void setLastloginip(Long lastloginip) {
		this.lastloginip = lastloginip;
	}

	public String getPowerlist() {
		return powerlist;
	}

	public void setPowerlist(String powerlist) {
		this.powerlist = powerlist;
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

}
