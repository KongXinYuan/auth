package com.xuguruogu.auth.web.param;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.xuguruogu.auth.dal.enums.RoleType;

public class AdminAddParam implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = -7117072859279673769L;

	@NotBlank(message = "名字不能为空")
	private String username;
	@NotBlank(message = "密码不能为空")
	private String password;
	@NotNull
	private RoleType role;

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

	public RoleType getRole() {
		return role;
	}

	public void setRole(long role) {
		for (RoleType r : RoleType.values()) {
			if (r.getLevel() == role) {
				this.role = r;
				return;
			}
		}
		this.role = null;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

}
