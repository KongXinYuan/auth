package com.xuguruogu.auth.dal.dataobject;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 数据模型基类，重写“toString”、“equals”和“hashCode”方法。 原作者：李品良
 *
 * @author zhipeng.zouzhp
 * @see org.apache.commons.lang.builder.ToStringBuilder
 * @see org.apache.commons.lang.builder.EqualsBuilder
 * @see org.apache.commons.lang.builder.HashCodeBuilder
 */
public abstract class Entity implements Serializable {

	/**  */
	private static final long serialVersionUID = 1L;

	protected Long id;

	/**
	 * Getter method for property <tt>id</tt>.
	 * 
	 * @return property value of id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Setter method for property <tt>id</tt>.
	 * 
	 * @param id
	 *            value to be assigned to property id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @see ToStringBuilder#reflectionToString(Object, ToStringStyle)
	 * @see ToStringStyle#SHORT_PREFIX_STYLE
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	/**
	 * @see EqualsBuilder#reflectionEquals(Object, Object)
	 */
	@Override
	public boolean equals(Object o) {
		return EqualsBuilder.reflectionEquals(this, o);
	}

	/**
	 * @see HashCodeBuilder#reflectionHashCode
	 */
	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

}
