package com.xuguruogu.auth.dal.enums;

import java.util.ArrayList;
import java.util.List;

public enum CDKeyStatusType {

	ACTIVE(0, "激活"),

	LOCKED(1, "已锁定"),

	DELETED(2, "已删除"),

	USED(3, "已使用");

	private final long code;
	private final String desc;

	private CDKeyStatusType(long code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public boolean isActive() {
		return this == ACTIVE;
	}

	public boolean canLock() {
		return this == ACTIVE || this == LOCKED;
	}

	public static List<CDKeyStatusType> asList(CDKeyStatusType status) {
		List<CDKeyStatusType> s = new ArrayList<>();
		s.add(status);
		return s;
	}

	public static List<CDKeyStatusType> getNonDelList() {
		List<CDKeyStatusType> s = new ArrayList<>();
		s.add(ACTIVE);
		s.add(LOCKED);
		s.add(USED);
		return s;
	}

	public static CDKeyStatusType fromLock(Boolean lock) {
		if (null == lock) {
			return null;
		}
		return lock ? LOCKED : ACTIVE;

	}

	public String getDesc() {
		return desc;
	}

	public long getCode() {
		return code;
	}

}
