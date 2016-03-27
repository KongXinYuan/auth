package com.xuguruogu.auth.dal.enums;

public enum RoleType {

	OWNER(0, "ROLE_OWNER", "作者"),

	ADMIN(1, "ROLE_ADMIN", "一级代理"),

	SELLER(2, "ROLE_SELLER", "代理");

	/** 级别 */
	private final long level;

	/** 码值 */
	private final String role;

	/** 描述 */
	private final String desc;

	private RoleType(long level, String role, String desc) {
		this.level = level;
		this.role = role;
		this.desc = desc;
	}

	public boolean hasFullPermission() {
		return this == RoleType.OWNER;
	}

	public long getLevel() {
		return level;
	}

	public String getRole() {
		return role;
	}

	public String getDesc() {
		return desc;
	}

}
