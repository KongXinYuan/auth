package com.xuguruogu.auth.web.param;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class SoftAddParam implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 42343573064547886L;

	@NotBlank(message = "名字不能为空")
	private String softname;
	@NotNull(message = "intervaltime不能为空")
	private Long intervaltime;
	@NotBlank(message = "privkey不能为空")
	private String privkey;

	public String getSoftname() {
		return softname;
	}

	public void setSoftname(String softname) {
		this.softname = softname;
	}

	public Long getIntervaltime() {
		return intervaltime;
	}

	public void setIntervaltime(Long intervaltime) {
		this.intervaltime = intervaltime;
	}

	public String getPrivkey() {
		return privkey;
	}

	public void setPrivkey(String privkey) {
		this.privkey = privkey;
	}

}
