package com.xuguruogu.auth.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.xuguruogu.auth.dal.enums.RoleType;

/**
 * 存储在session中的管理员对象
 *
 * @author benli.lbl
 * @version $Id: ApiUserDetails.java, v 0.1 Aug 9, 2015 10:25:08 PM benli.lbl
 *          Exp $
 */
public class AdminUserDetails extends User {

	/**  */
	private static final long serialVersionUID = -7899476908216077110L;

	/** id */
	private long id;
	private RoleType role;

	public AdminUserDetails(String username, String password, boolean enabled,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, true, true, true, authorities);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public RoleType getRole() {
		return role;
	}

	public void setRole(RoleType role) {
		this.role = role;
	}

}
