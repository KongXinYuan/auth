package com.xuguruogu.auth.config;

import java.io.Serializable;

public class SoftConfigDO implements Serializable {

	private static final long serialVersionUID = -2149747956353834226L;

	private long softCode;
	private String softKey;
	private boolean islock;
	private int intervaltime;
	private String clientPublicKey;
	private String serverPrivateKey;

	public long getSoftCode() {
		return softCode;
	}

	public void setSoftCode(long softCode) {
		this.softCode = softCode;
	}

	public String getSoftKey() {
		return softKey;
	}

	public void setSoftKey(String softKey) {
		this.softKey = softKey;
	}

	public boolean isIslock() {
		return islock;
	}

	public void setIslock(boolean islock) {
		this.islock = islock;
	}

	public int getIntervaltime() {
		return intervaltime;
	}

	public void setIntervaltime(int intervaltime) {
		this.intervaltime = intervaltime;
	}

	public String getClientPublicKey() {
		return clientPublicKey;
	}

	public void setClientPublicKey(String clientPublicKey) {
		this.clientPublicKey = clientPublicKey;
	}

	public String getServerPrivateKey() {
		return serverPrivateKey;
	}

	public void setServerPrivateKey(String serverPrivateKey) {
		this.serverPrivateKey = serverPrivateKey;
	}

}
