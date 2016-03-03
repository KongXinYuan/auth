package com.xuguruogu.auth.service;

import java.util.List;

import com.xuguruogu.auth.dal.dataobject.KssAdminDO;

/**
 * 管理员用户
 *
 * @author benli.lbl
 * @version $Id: AdminManager.java, v 0.1 Aug 29, 2015 10:12:49 AM benli.lbl Exp
 *          $
 */
public interface AdminManager {

	public void create(final Long parentid, Integer level, String username, String password);

	public KssAdminDO queryById(final Long adminid);

	public KssAdminDO queryByUsername(final String username);

	public void updatePassword(final Long adminid, final String password);

	public void updateLastLogin(final Long adminid, final String ip);

	public List<KssAdminDO> queryByPage(final int limit, final int pageIndex, final Long parentid);

	public int queryCount(final Long parentid);

	public void deleteById(final Long adminid);

	public void deleteByIds(final List<Long> adminids);

	public void updateLock(final Long adminid, boolean lock);

}
