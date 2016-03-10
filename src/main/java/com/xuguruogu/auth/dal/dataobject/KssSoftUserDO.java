package com.xuguruogu.auth.dal.dataobject;

import java.math.BigDecimal;
import java.util.Date;

public class KssSoftUserDO extends EntityWithSeg {

	/**
	 *
	 */
	private static final long serialVersionUID = -3387206639843852824L;
	private long adminid;
	private String username;
	private String password;
	// 累计充值时间
	private BigDecimal cday;
	private boolean lock;
	private boolean pub;
	// 类似于cookie
	private String linecode;
	// 机器码
	private String pccode;
	private Date addtime;
	private Date starttime;
	private Date endtime;
	private Date lastlogintime;
	private long lastloginip;
	// 登录计数
	private long activetimes;
	private Date lockendtime;
	// 充值计数
	private long rechargetimes;

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

	public boolean isLock() {
		return lock;
	}

	public void setLock(boolean lock) {
		this.lock = lock;
	}

	public boolean isPub() {
		return pub;
	}

	public void setPub(boolean pub) {
		this.pub = pub;
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

	public Date getStarttime() {
		return starttime;
	}

	public void setStarttime(Date starttime) {
		this.starttime = starttime;
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

	public long getActivetimes() {
		return activetimes;
	}

	public void setActivetimes(long activetimes) {
		this.activetimes = activetimes;
	}

	public Date getLockendtime() {
		return lockendtime;
	}

	public void setLockendtime(Date lockendtime) {
		this.lockendtime = lockendtime;
	}

	public long getRechargetimes() {
		return rechargetimes;
	}

	public void setRechargetimes(long rechargetimes) {
		this.rechargetimes = rechargetimes;
	}

}
