package com.xuguruogu.auth.util;

import org.springframework.security.core.context.SecurityContextHolder;

import com.xuguruogu.auth.security.AdminUserDetails;

public class SecurityUtil {

	public static AdminUserDetails getCurrentAdmin() {
		return (AdminUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();// spring
	}

	public static boolean hasPermission(long adminid) {
		return false;
	}

}
