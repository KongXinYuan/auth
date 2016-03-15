package com.xuguruogu.auth.web.param;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotBlank;

public class AdminAddParam implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = -7117072859279673769L;

	@NotBlank(message = "名字不能为空")
	private String username;
	@NotBlank(message = "密码不能为空")
	private String password;
	@Min(1)
	@Max(2)
	private long level;

	private BigDecimal money = BigDecimal.ZERO;

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

	public long getLevel() {
		return level;
	}

	public void setLevel(long level) {
		this.level = level;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

}
