package com.xuguruogu.auth.dal.dataobject;

import com.xuguruogu.auth.dal.enums.SoftStatusType;

/**
 * 软件对象
 *
 * @author benli.lbl
 *
 */
public class KssSoftDO extends Entity {

	/**
	 *
	 */
	private static final long serialVersionUID = -4645237272295270232L;

	/** 软件校验码 */
	private String softkey;

	/** 软件名称 */
	private String softname;

	/** 校验在线最长间隔时间 */
	private long intervaltime;

	/** 私匙 */
	private String privkey;

	private SoftStatusType status;

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

	public String getPrivkey() {
		return privkey;
	}

	public void setPrivkey(String privkey) {
		this.privkey = privkey;
	}

	public SoftStatusType getStatus() {
		return status;
	}

	public void setStatus(SoftStatusType status) {
		this.status = status;
	}

}
