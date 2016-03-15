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
	private boolean done;
	private long softid;
	private long adminid;
	private long keysetid;
	private long keycount;
	private long beginid;
	private Date addtime;

	public String getOrdernum() {
		return ordernum;
	}

	public void setOrdernum(String ordernum) {
		this.ordernum = ordernum;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

	public long getSoftid() {
		return softid;
	}

	public void setSoftid(long softid) {
		this.softid = softid;
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