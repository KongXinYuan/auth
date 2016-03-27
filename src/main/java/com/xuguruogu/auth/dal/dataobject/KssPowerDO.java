package com.xuguruogu.auth.dal.dataobject;

import java.math.BigDecimal;

/**
 *
 * @author kent
 *
 */
public class KssPowerDO extends Entity {

	/**
	 *
	 */
	private static final long serialVersionUID = -4614683723211403502L;
	private long keysetid;
	private String keyname;
	private long softid;
	private String softname;
	private long adminid;
	private BigDecimal retailprice;
	private BigDecimal sellprice;

	public String getKeyname() {
		return keyname;
	}

	public void setKeyname(String keyname) {
		this.keyname = keyname;
	}

	public String getSoftname() {
		return softname;
	}

	public void setSoftname(String softname) {
		this.softname = softname;
	}

	public long getKeysetid() {
		return keysetid;
	}

	public void setKeysetid(long keysetid) {
		this.keysetid = keysetid;
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

	public BigDecimal getRetailprice() {
		return retailprice;
	}

	public void setRetailprice(BigDecimal retailprice) {
		this.retailprice = retailprice;
	}

	public BigDecimal getSellprice() {
		return sellprice;
	}

	public void setSellprice(BigDecimal sellprice) {
		this.sellprice = sellprice;
	}

}
