package com.xuguruogu.auth.dal.dataobject;

import java.util.Date;

/**
 * 软件对象
 *
 * @author benli.lbl
 *
 */
public class KssOrderDO extends Entity {

	/**
	 *
	 */
	private static final long serialVersionUID = -5979181482797869979L;
	private String ordernum;
	private long softid;
	private String softname;
	private long adminid;
	private String adminname;
	private long parentid;
	private String parentname;
	private long keysetid;
	private String keyname;
	private long keycount;
	private long beginid;
	private Date addtime;

	public String getOrdernum() {
		return ordernum;
	}

	public void setOrdernum(String ordernum) {
		this.ordernum = ordernum;
	}

	public long getSoftid() {
		return softid;
	}

	public void setSoftid(long softid) {
		this.softid = softid;
	}

	public String getSoftname() {
		return softname;
	}

	public void setSoftname(String softname) {
		this.softname = softname;
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

	public long getKeycount() {
		return keycount;
	}

	public void setKeycount(long keycount) {
		this.keycount = keycount;
	}

	public long getBeginid() {
		return beginid;
	}

	public void setBeginid(long beginid) {
		this.beginid = beginid;
	}

	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

}