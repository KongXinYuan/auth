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

	public void onLoginSucess(long adminid, String ip);

	public void create(final long parentid, long level, String username, String password);

	public KssAdminDO queryById(final long adminid);

	public KssAdminDO queryByUsername(final String username);

	public void updatePassword(final long adminid, final String password);

	public List<KssAdminDO> queryByPage(final long parentid, final int limit, final int pageIndex);

	public long queryCount(final long parentid);

	public void deleteById(final long adminid);

	public void deleteByIds(final List<Long> adminids);

	public void updateLock(final long adminid, boolean lock);

}
