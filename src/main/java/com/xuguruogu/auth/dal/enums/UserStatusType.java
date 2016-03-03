package com.xuguruogu.auth.dal.enums;

public enum UserStatusType {

	ACTIVE(0, "正常"),

	LOCKED(1, "锁定");

	/** 码值 */
	private final Integer code;

	/** 描述 */
	private final String desc;

	private UserStatusType(Integer code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	/**
	 * Getter method for property <tt>code</tt>.
	 *
	 * @return property value of code
	 */
	public Integer getCode() {
		return code;
	}

	/**
	 * Getter method for property <tt>desc</tt>.
	 *
	 * @return property value of desc
	 */
	public String getDesc() {
		return desc;
	}

}