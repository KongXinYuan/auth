package com.xuguruogu.auth.dal.enums;

import java.util.ArrayList;
import java.util.List;

public enum UserStatusType {

	ACTIVE(0, "激活"),

	LOCKED(1, "已锁定"),

	DELETED(2, "已删除");

	/** 描述 */
	private final long code;
	private final String desc;

	private UserStatusType(long code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public boolean isActive() {
		return this == ACTIVE;
	}

	public boolean canLock() {
		return this == ACTIVE || this == LOCKED;
	}

	public static List<UserStatusType> asList(UserStatusType status) {
		List<UserStatusType> s = new ArrayList<>();
		s.add(status);
		return s;
	}

	public static List<UserStatusType> getNonDelList() {
		List<UserStatusType> s = new ArrayList<>();
		s.add(ACTIVE);
		s.add(LOCKED);
		return s;
	}

	public static UserStatusType fromLock(Boolean lock) {
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
