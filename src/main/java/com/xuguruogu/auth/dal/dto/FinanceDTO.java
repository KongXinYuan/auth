package com.xuguruogu.auth.dal.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 管理员对象
 *
 * @author benli.lbl
 *
 */
public class FinanceDTO implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 7273526675576153518L;
	/** 上级id */
	private long adminid;
	private String username;
	private String direction;
	private long relatedid;
	private String relatedname;
	private BigDecimal moneynow;
	private BigDecimal moneybefore;
	private BigDecimal moneychange;
	private Date addtime;
	private String disc;

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

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public long getRelatedid() {
		return relatedid;
	}

	public void setRelatedid(long relatedid) {
		this.relatedid = relatedid;
	}

	public String getRelatedname() {
		return relatedname;
	}

	public void setRelatedname(String relatedname) {
		this.relatedname = relatedname;
	}

	public BigDecimal getMoneynow() {
		return moneynow;
	}

	public void setMoneynow(BigDecimal moneynow) {
		this.moneynow = moneynow;
	}

	public BigDecimal getMoneybefore() {
		return moneybefore;
	}

	public void setMoneybefore(BigDecimal moneybefore) {
		this.moneybefore = moneybefore;
	}

	public BigDecimal getMoneychange() {
		return moneychange;
	}

	public void setMoneychange(BigDecimal moneychange) {
		this.moneychange = moneychange;
	}

	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public String getDisc() {
		return disc;
	}

	public void setDisc(String disc) {
		this.disc = disc;
	}

}
