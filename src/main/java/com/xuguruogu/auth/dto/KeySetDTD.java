package com.xuguruogu.auth.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class KeySetDTD implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -8573272598444441478L;
	private long id;
	private long softid;
	private boolean lock;
	private String keyname;
	private BigDecimal cday;
	private String prefix;
	private BigDecimal retailprice;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getSoftid() {
		return softid;
	}

	public void setSoftid(long softid) {
		this.softid = softid;
	}

	public boolean isLock() {
		return lock;
	}

	public void setLock(boolean lock) {
		this.lock = lock;
	}

	public String getKeyname() {
		return keyname;
	}

	public void setKeyname(String keyname) {
		this.keyname = keyname;
	}

	public BigDecimal getCday() {
		return cday;
	}

	public void setCday(BigDecimal cday) {
		this.cday = cday;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public BigDecimal getRetailprice() {
		return retailprice;
	}

	public void setRetailprice(BigDecimal retailprice) {
		this.retailprice = retailprice;
	}

}
