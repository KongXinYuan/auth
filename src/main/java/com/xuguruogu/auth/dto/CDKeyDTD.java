package com.xuguruogu.auth.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class CDKeyDTD implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -2981295577256551332L;

	private long id;
	private long adminid;
	private long keysetid;
	private String cdkey;
	private Date addtime;
	private BigDecimal cday;
	private String tag;
	private boolean lock;
	private Date usedtime;
	private String useduser;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getAdminid() {
		return adminid;
	}

	public void setAdminid(long adminid) {
		this.adminid = adminid;
	}

	public long getKeysetid() {
		return keysetid;
	}

	public void setKeysetid(long keysetid) {
		this.keysetid = keysetid;
	}

	public String getCdkey() {
		return cdkey;
	}

	public void setCdkey(String cdkey) {
		this.cdkey = cdkey;
	}

	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public BigDecimal getCday() {
		return cday;
	}

	public void setCday(BigDecimal cday) {
		this.cday = cday;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public boolean isLock() {
		return lock;
	}

	public void setLock(boolean lock) {
		this.lock = lock;
	}

	public Date getUsedtime() {
		return usedtime;
	}

	public void setUsedtime(Date usedtime) {
		this.usedtime = usedtime;
	}

	public String getUseduser() {
		return useduser;
	}

	public void setUseduser(String useduser) {
		this.useduser = useduser;
	}

}
