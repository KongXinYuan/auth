package com.xuguruogu.auth.dal.dataobject;

import java.math.BigDecimal;
import java.util.Date;

import com.xuguruogu.auth.dal.enums.CDKeyStatusType;

/**
 * 软件对象
 *
 * @author benli.lbl
 *
 */
public class KssSoftKeyDO extends EntityWithSeg {

	private static final long serialVersionUID = -8464398454249392934L;

	private long adminid;
	private String adminname;
	private long parentid;
	private String parentname;
	private long ownerid;
	private long keysetid;
	private String keyname;
	private String cdkey;
	private Date addtime;
	private String ordernum;
	private BigDecimal cday;
	private String tag;
	private Date usedtime;
	private Long userid;
	private String username;
	private BigDecimal oldcday;
	private BigDecimal newcday;
	private CDKeyStatusType status;

	public boolean isOwnedBy(long id) {
		return this.adminid == id || this.parentid == id || this.ownerid == id;
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

	public long getKeysetid() {
		return keysetid;
	}

	public void setKeysetid(long keysetid) {
		this.keysetid = keysetid;
	}

	public String getKeyname() {
		return keyname;
	}

	public void setKeyname(String keyname) {
		this.keyname = keyname;
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

	public Date getUsedtime() {
		return usedtime;
	}

	public void setUsedtime(Date usedtime) {
		this.usedtime = usedtime;
	}

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public BigDecimal getOldcday() {
		return oldcday;
	}

	public void setOldcday(BigDecimal oldcday) {
		this.oldcday = oldcday;
	}

	public BigDecimal getNewcday() {
		return newcday;
	}

	public void setNewcday(BigDecimal newcday) {
		this.newcday = newcday;
	}

	public CDKeyStatusType getStatus() {
		return status;
	}

	public void setStatus(CDKeyStatusType status) {
		this.status = status;
	}

}
