package com.xuguruogu.auth.web.param;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class PowerAddParam implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 7971990463614485541L;

	@NotNull(message = "请选择管理员")
	private Long adminid;
	@NotNull(message = "请选择软件")
	private Long softid;
	@NotNull(message = "请选择卡类")
	private Long keysetid;
	@NotNull(message = "标签不能为空")
	@Min(value = 0, message = "不能为负")
	private BigDecimal sellprice;

	public Long getAdminid() {
		return adminid;
	}

	public void setAdminid(Long adminid) {
		this.adminid = adminid;
	}

	public Long getSoftid() {
		return softid;
	}

	public void setSoftid(Long softid) {
		this.softid = softid;
	}

	public Long getKeysetid() {
		return keysetid;
	}

	public void setKeysetid(Long keysetid) {
		this.keysetid = keysetid;
	}

	public BigDecimal getSellprice() {
		return sellprice;
	}

	public void setSellprice(BigDecimal sellprice) {
		this.sellprice = sellprice;
	}

}
