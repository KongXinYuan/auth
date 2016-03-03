package com.xuguruogu.auth.dto;

import java.io.Serializable;

public class SoftDTO implements Serializable {

	/**  */
	private static final long serialVersionUID = -5058138313226583283L;

	/** 软件编号 */
	private Integer softcode;

	/** 软件校验码 */
	private String softkey;

	/** 软件名称 */
	private String softname;

	/** 锁定标记 */
	private Boolean islock;

	/** 校验在线最长间隔时间 */
	private Integer intervaltime;

	/** 公匙 */
	private String publickey;

	/** 私匙 */
	private String privatekey;

	public Integer getSoftcode() {
		return softcode;
	}

	public void setSoftcode(Integer softcode) {
		this.softcode = softcode;
	}

	public String getSoftkey() {
		return softkey;
	}

	public void setSoftkey(String softkey) {
		this.softkey = softkey;
	}

	public String getSoftname() {
		return softname;
	}

	public void setSoftname(String softname) {
		this.softname = softname;
	}

	public Boolean getIslock() {
		return islock;
	}

	public void setIslock(Boolean islock) {
		this.islock = islock;
	}

	public Integer getIntervaltime() {
		return intervaltime;
	}

	public void setIntervaltime(Integer intervaltime) {
		this.intervaltime = intervaltime;
	}

	public String getPublickey() {
		return publickey;
	}

	public void setPublickey(String publickey) {
		this.publickey = publickey;
	}

	public String getPrivatekey() {
		return privatekey;
	}

	public void setPrivatekey(String privatekey) {
		this.privatekey = privatekey;
	}

}
