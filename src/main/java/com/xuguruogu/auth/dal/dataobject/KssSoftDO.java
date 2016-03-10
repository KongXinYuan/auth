package com.xuguruogu.auth.dal.dataobject;

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

	/** 软件编号 */
	private long softcode;

	/** 软件校验码 */
	private String softkey;

	/** 软件名称 */
	private String softname;

	/** 锁定标记 */
	private boolean lock;

	/** 校验在线最长间隔时间 */
	private long intervaltime;

	/** 公匙 */
	private String clientpubkey;

	/** 私匙 */
	private String serverprivkey;

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

	public boolean isLock() {
		return lock;
	}

	public void setLock(boolean lock) {
		this.lock = lock;
	}

	public long getIntervaltime() {
		return intervaltime;
	}

	public void setIntervaltime(long intervaltime) {
		this.intervaltime = intervaltime;
	}

	public String getClientpubkey() {
		return clientpubkey;
	}

	public void setClientpubkey(String clientpubkey) {
		this.clientpubkey = clientpubkey;
	}

	public String getServerprivkey() {
		return serverprivkey;
	}

	public void setServerprivkey(String serverprivkey) {
		this.serverprivkey = serverprivkey;
	}

}
