package com.xuguruogu.auth.dal.dataobject;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 管理员对象
 *
 * @author benli.lbl
 *
 */
public class KssAdminDO extends Entity {

	/**  */
	private static final long serialVersionUID = 2500557805132606740L;

	/** 上级id */
	private Integer parentid;

	/** 层级 */
	private Integer level;

	/** 删除标记 */
	private Boolean isdel;

	/** 用户名 */
	private String username;

	/** 密码 */
	private String password;

	/** 锁定 */
	private Boolean islock;

	/** 添加时间 */
	private Date addtime;

	/** 上次登录时间 */
	private Date lastlogintime;

	/** 上次登录ip */
	private Long lastloginip;

	/** 权限列表 */
	private String powerlist;

	/** 当前余额 */
	private BigDecimal money;

	/** 累计金额 */
	private BigDecimal exmoney;

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

	public Boolean getIsdel() {
		return isdel;
	}

	public void setIsdel(Boolean isdel) {
		this.isdel = isdel;
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
