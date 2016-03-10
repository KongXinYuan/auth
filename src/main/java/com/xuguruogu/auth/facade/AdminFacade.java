package com.xuguruogu.auth.facade;

import java.util.List;
import java.util.Map;

import com.xuguruogu.auth.dto.AdminDTO;
import com.xuguruogu.auth.dto.LogLoginDTO;

/**
 * 管理域
 *
 * @author benli.lbl
 * @version $Id: AdminFacade.java, v 0.1 Aug 29, 2015 11:47:32 AM benli.lbl Exp
 *          $
 */
public interface AdminFacade {

	public AdminDTO create(long parentid, long level, String username, String password);

	public AdminDTO profile(long adminid);

	public List<LogLoginDTO> queryLatestLogLogin(long adminid);

	public void chagePassword(long adminid, String oldPassword, String newPassword);

	public void resetPassword(long adminid, String password);

	public Map<String, Object> querylogLoginByPage(long adminid, int limit, int pageIndex);

	public Map<String, Object> queryByPage(long parentid, int limit, int pageIndex);

	public void deleteById(long adminid);

	public void deleteByIds(List<Long> adminids);

	public void updateLock(long adminid, boolean lock);
}
