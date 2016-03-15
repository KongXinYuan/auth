package com.xuguruogu.auth.web.param;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

public class KeySetAddParam implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 2820962025388275087L;

	@NotNull(message = "名字不能为空")
	private Long softid;
	@NotBlank(message = "名字不能为空")
	private String keyname;
	@NotNull(message = "充值天数不能为空")
	private BigDecimal cday;
	@Pattern(regexp = "^[0-9a-zA-Z]{4}$", message = "4位字母或数字")
	private String prefix;
	@NotNull(message = "零售价不能为空")
	private BigDecimal retailprice;

	public Long getSoftid() {
		return softid;
	}

	public void setSoftid(Long softid) {
		this.softid = softid;
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
