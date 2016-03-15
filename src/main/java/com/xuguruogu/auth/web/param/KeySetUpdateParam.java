package com.xuguruogu.auth.web.param;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

public class KeySetUpdateParam implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -2609761703898349849L;

	@NotNull(message = "名字不能为空")
	private Long keysetid;
	@NotNull(message = "充值天数不能为空")
	private BigDecimal cday;
	@NotNull(message = "零售价不能为空")
	private BigDecimal retailprice;

	public Long getKeysetid() {
		return keysetid;
	}

	public void setKeysetid(Long keysetid) {
		this.keysetid = keysetid;
	}

	public BigDecimal getCday() {
		return cday;
	}

	public void setCday(BigDecimal cday) {
		this.cday = cday;
	}

	public BigDecimal getRetailprice() {
		return retailprice;
	}

	public void setRetailprice(BigDecimal retailprice) {
		this.retailprice = retailprice;
	}

}
