package com.xuguruogu.auth.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.xuguruogu.auth.dal.daointerface.KssAdminDao;
import com.xuguruogu.auth.dal.dataobject.KssAdminDO;
import com.xuguruogu.auth.dal.querycondition.KssAdminQueryCondition;

@Component("adminUserDetailsService")
public class AdminUserDetailsService implements UserDetailsService {

	private static Logger logger = LoggerFactory.getLogger(AdminUserDetailsService.class);

	@Autowired
	private AdminUserDetailsConverter adminUserDetailsConverter;

	@Autowired
	private KssAdminDao kssAdminDao;

	// 登陆验证时，通过username获取用户的所有权限信息，
	// 并返回User放到spring的全局缓存SecurityContextHolder中，以供授权器使用
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {

		KssAdminDO kssAdminDO = kssAdminDao
				.selectOneByQueryCondition(new KssAdminQueryCondition().putUsername(username));

		if (null == kssAdminDO) {
			if (logger.isWarnEnabled()) {
				logger.warn("user not found: " + username);
			}
			throw new UsernameNotFoundException(username);
		}

		return adminUserDetailsConverter.convert(kssAdminDO);
	}
}