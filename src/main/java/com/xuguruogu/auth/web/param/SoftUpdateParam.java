package com.xuguruogu.auth.web.param;

import java.io.Serializable;

public class SoftUpdateParam implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 4421049386067961080L;

	private long softid;
	private long intervaltime;
	private String clientpubkey;
	private String serverprivkey;

	public long getSoftid() {
		return softid;
	}

	public void setSoftid(long softid) {
		this.softid = softid;
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
