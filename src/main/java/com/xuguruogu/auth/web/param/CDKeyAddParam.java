package com.xuguruogu.auth.web.param;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class CDKeyAddParam implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -1903945187830310534L;
	@NotNull(message = "软件不能为空")
	private Long softid;
	@NotNull(message = "卡类不能为空")
	private Long keysetid;
	@Min(value = 1)
	@Max(value = 500)
	private Long num;
	@NotNull(message = "标签不能为空")
	private String tag;

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

	public Long getNum() {
		return num;
	}

	public void setNum(Long num) {
		this.num = num;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

}
