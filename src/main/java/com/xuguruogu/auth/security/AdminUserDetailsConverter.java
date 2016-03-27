package com.xuguruogu.auth.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.xuguruogu.auth.dal.dataobject.KssAdminDO;
import com.xuguruogu.auth.dal.enums.AdminStatusType;
import com.xuguruogu.auth.util.AbstractConverter;

/**
 *
 *
 * @author benli.lbl
 * @version $Id: ApiUserDetailsConverter.java, v 0.1 Aug 10, 2015 11:31:07 PM
 *          benli.lbl Exp $
 */
@Component("adminUserDetailsConverter")
public class AdminUserDetailsConverter extends AbstractConverter<KssAdminDO, AdminUserDetails> {

	@Override
	protected AdminUserDetails doConvert(KssAdminDO kssAdminDO) {

		boolean enabled = AdminStatusType.ACTIVE == kssAdminDO.getStatus();

		Collection<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
		long adminid = kssAdminDO.getId();

		auths.add(new SimpleGrantedAuthority(kssAdminDO.getRole().getRole()));

		AdminUserDetails adminUserDetails = new AdminUserDetails(kssAdminDO.getUsername(), kssAdminDO.getPassword(),
				enabled, auths);

		adminUserDetails.setId(adminid);
		adminUserDetails.setRole(kssAdminDO.getRole());

		return adminUserDetails;
	}
}
