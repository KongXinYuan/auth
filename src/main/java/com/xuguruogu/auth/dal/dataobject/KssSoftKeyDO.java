package com.xuguruogu.auth.dal.dataobject;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 软件对象
 *
 * @author benli.lbl
 *
 */
public class KssSoftKeyDO extends EntityWithSeg {

	private static final long serialVersionUID = -8464398454249392934L;

	private long adminid;
	private String prefix;
	private String cdkey;
	private String password;
	private Date addtime;
	private String ordernum;
	private BigDecimal cday;
	private String tag;
	private boolean lock;
	private Date usedtime;
	private String useduser;

	public long getAdminid() {
		return adminid;
	}

	public void setAdminid(long adminid) {
		this.adminid = adminid;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getCdkey() {
		return cdkey;
	}

	public void setCdkey(String cdkey) {
		this.cdkey = cdkey;
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

	public String getOrdernum() {
		return ordernum;
	}

	public void setOrdernum(String ordernum) {
		this.ordernum = ordernum;
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
