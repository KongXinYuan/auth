package com.xuguruogu.auth.dal.enums;

import java.util.ArrayList;
import java.util.List;

public enum AdminStatusType {

	ACTIVE(0, "激活"),

	LOCKED(1, "已锁定"),

	DELETED(2, "已删除");

	private final long code;
	private final String desc;

	private AdminStatusType(long code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public boolean isActive() {
		return this == ACTIVE;
	}

	public boolean canLock() {
		return this == ACTIVE || this == LOCKED;
	}

	public static List<AdminStatusType> asList(AdminStatusType status) {
		List<AdminStatusType> s = new ArrayList<>();
		s.add(status);
		return s;
	}

	public static List<AdminStatusType> getNonDelList() {
		List<AdminStatusType> s = new ArrayList<>();
		s.add(ACTIVE);
		s.add(LOCKED);
		return s;
	}

	public static AdminStatusType fromLock(Boolean lock) {
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
