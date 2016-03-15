package com.xuguruogu.auth.dal.enums;

public enum RoleType {

	OWNER(0, "ROLE_OWNER", "作者"),

	ADMIN(1, "ROLE_ADMIN", "一级代理"),

	SELLER(2, "ROLE_SELLER", "代理");

	/** 级别 */
	private final long level;

	/** 码值 */
	private final String code;

	/** 描述 */
	private final String desc;

	private RoleType(long level, String code, String desc) {
		this.level = level;
		this.code = code;
		this.desc = desc;
	}

	public long getLevel() {
		return level;
	}

	public String getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}

}
