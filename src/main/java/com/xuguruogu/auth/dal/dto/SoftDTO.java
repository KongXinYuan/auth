package com.xuguruogu.auth.dal.dto;

import java.io.Serializable;

public class SoftDTO implements Serializable {

	/**  */
	private static final long serialVersionUID = -5058138313226583283L;
	long id;
	/** 软件编号 */
	private long softcode;

	/** 软件校验码 */
	private String softkey;

	/** 软件名称 */
	private String softname;

	/** 校验在线最长间隔时间 */
	private long intervaltime;

	private String status;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getSoftcode() {
		return softcode;
	}

	public void setSoftcode(long softcode) {
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

	public long getIntervaltime() {
		return intervaltime;
	}

	public void setIntervaltime(long intervaltime) {
		this.intervaltime = intervaltime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
