package com.xuguruogu.auth.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.xuguruogu.auth.dal.dataobject.KssAdminDO;
import com.xuguruogu.auth.dto.AdminDTO;

/**
 * 管理员用户
 *
 * @author benli.lbl
 * @version $Id: AdminManager.java, v 0.1 Aug 29, 2015 10:12:49 AM benli.lbl Exp
 *          $
 */
public interface AdminManager {

	public Map<String, Object> list(long parentid, Integer pageNo, Integer pageSize);

	public Map<String, Object> profile(long adminid);

	public void onLoginSucess(long adminid, String ip);

	public AdminDTO register(long parentid, long level, String username, String password, BigDecimal money);

	public KssAdminDO queryById(final long adminid);

	public KssAdminDO queryByUsername(final String username);

	public void updatePassword(final long adminid, final String password);

	public void deleteByIds(long parentid, List<Long> adminids);

	public void updateLock(long adminid, long lockid, boolean lock);

}
