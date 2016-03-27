package com.xuguruogu.auth.web.param;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

public class SoftUpdateParam implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 4421049386067961080L;

	@NotNull(message = "intervaltime不能为空")
	private long softid;
	@NotNull(message = "intervaltime不能为空")
	private long intervaltime;
	private String privkey;

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

	public String getPrivkey() {
		return privkey;
	}

	public void setPrivkey(String privkey) {
		this.privkey = privkey;
	}

}
