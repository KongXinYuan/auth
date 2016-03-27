package com.xuguruogu.auth.web.param;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserAddParam implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -1903945187830310534L;
	@NotNull(message = "软件不能为空")
	private Long softid;
	@Size(min = 2, max = 30, message = "用户名长度2-30")
	private String username;
	@Size(min = 2, max = 30, message = "密码长度2-30")
	private String password;
	@Min(value = 0, message = "时间不能为空")
	private BigDecimal cday;
	@Size(min = 2, max = 20, message = "标签长度2-20")
	private String tag;

	public Long getSoftid() {
		return softid;
	}

	public void setSoftid(Long softid) {
		this.softid = softid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public BigDecimal getCday() {
		return cday;
	}

	public void setCday(BigDecimal cday) {
		this.cday = cday;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

}
