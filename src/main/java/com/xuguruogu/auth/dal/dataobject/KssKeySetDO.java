package com.xuguruogu.auth.dal.dataobject;

import java.math.BigDecimal;

/**
 *
 * @author kent
 *
 */
public class KssKeySetDO extends Entity {

	/**
	 *
	 */
	private static final long serialVersionUID = -4614683723211403502L;
	private long softid;
	private boolean lock;
	private String keyname;
	private BigDecimal cday;
	private String prefix;
	private BigDecimal retailprice;

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
