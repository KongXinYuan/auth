package com.xuguruogu.auth.api.param;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

@Validated
public class ReqParam implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 669561066839909538L;

	@NotNull(message = "软件编号为空")
	@Min(value = 0, message = "软件编号为空")
	private Long softid;
	@NotBlank(message = "softkey为空")
	private String softkey;
	@NotBlank(message = "请求为空")
	private String req;

	public Long getSoftid() {
		return softid;
	}

	public void setSoftid(Long softid) {
		this.softid = softid;
	}

	public String getSoftkey() {
		return softkey;
	}

	public void setSoftkey(String softkey) {
		this.softkey = softkey;
	}

	public String getReq() {
		return req;
	}

	public void setReq(String req) {
		this.req = req;
	}

}
