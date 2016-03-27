package com.xuguruogu.auth.dal.dataobject;

import java.math.BigDecimal;
import java.util.Date;

import com.xuguruogu.auth.dal.enums.UserStatusType;

public class KssSoftUserDO extends EntityWithSeg {

	/**
	 *
	 */
	private static final long serialVersionUID = -3387206639843852824L;
	private String username;
	private String password;
	private long adminid;
	private String adminname;
	private long parentid;
	private String parentname;
	private long ownerid;
	// 累计充值时间
	private BigDecimal cday;
	// 类似于cookie
	private String linecode;
	// 机器码
	private String pccode;
	private Date addtime;
	private Date endtime;
	private Date lastlogintime;
	private long lastloginip;
	// 充值计数
	private long rechargetimes;
	private UserStatusType status;

	public boolean isOwnedBy(long adminid) {
		return this.adminid == adminid || this.parentid == adminid || this.ownerid == adminid;
	}

	public long getAdminid() {
		return adminid;
	}

	public void setAdminid(long adminid) {
		this.adminid = adminid;
	}

	public String getAdminname() {
		return adminname;
	}

	public void setAdminname(String adminname) {
		this.adminname = adminname;
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

	public String getLinecode() {
		return linecode;
	}

	public void setLinecode(String linecode) {
		this.linecode = linecode;
	}

	public String getPccode() {
		return pccode;
	}

	public void setPccode(String pccode) {
		this.pccode = pccode;
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

	public UserStatusType getStatus() {
		return status;
	}

	public void setStatus(UserStatusType status) {
		this.status = status;
	}

}
