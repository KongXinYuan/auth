package com.xuguruogu.auth.dal.dataobject;

import java.math.BigDecimal;
import java.util.Date;

import com.xuguruogu.auth.dal.enums.AdminStatusType;
import com.xuguruogu.auth.dal.enums.RoleType;

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
	private long parentid;

	private String parentname;

	private long ownerid;

	/** 层级 */
	private RoleType role;

	/** 用户名 */
	private String username;

	/** 密码 */
	private String password;

	/** 添加时间 */
	private Date addtime;

	/** 上次登录时间 */
	private Date lastlogintime;

	/** 上次登录ip */
	private int lastloginip;

	/** 当前余额 */
	private BigDecimal money;

	/** 累计金额 */
	private BigDecimal exmoney;

	private AdminStatusType status;

	public boolean isOwnedBy(long id) {
		return this.getParentid() == id || this.getOwnerid() == id;
	}

	public boolean isOwnedByOrEqual(long id) {
		return this.id == id || this.getParentid() == id || this.getOwnerid() == id;
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

	public long getOwnerid() {
		return ownerid;
	}

	public void setOwnerid(long ownerid) {
		this.ownerid = ownerid;
	}

	public RoleType getRole() {
		return role;
	}

	public void setRole(RoleType role) {
		this.role = role;
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

	public int getLastloginip() {
		return lastloginip;
	}

	public void setLastloginip(int lastloginip) {
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

	public AdminStatusType getStatus() {
		return status;
	}

	public void setStatus(AdminStatusType status) {
		this.status = status;
	}

}
