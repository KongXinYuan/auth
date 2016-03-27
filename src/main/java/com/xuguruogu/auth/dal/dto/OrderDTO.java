package com.xuguruogu.auth.dal.dto;

import java.io.Serializable;
import java.util.Date;

public class OrderDTO implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 8044274391768428545L;

	private long id;
	private String ordernum;
	private String softname;
	private String adminname;
	private String parentname;
	private String keyname;
	private long keycount;
	private long beginid;
	private Date addtime;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getOrdernum() {
		return ordernum;
	}

	public void setOrdernum(String ordernum) {
		this.ordernum = ordernum;
	}

	public String getSoftname() {
		return softname;
	}

	public void setSoftname(String softname) {
		this.softname = softname;
	}

	public String getAdminname() {
		return adminname;
	}

	public void setAdminname(String adminname) {
		this.adminname = adminname;
	}

	public String getParentname() {
		return parentname;
	}

	public void setParentname(String parentname) {
		this.parentname = parentname;
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
