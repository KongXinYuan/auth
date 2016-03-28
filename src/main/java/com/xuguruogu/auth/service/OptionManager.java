package com.xuguruogu.auth.service;

public interface OptionManager {

	public String get(String name);

	public void put(String name, String value);

	public String getBySoftId(long softid, String name);

	public void putBySoftId(long softid, String name, String value);
}
