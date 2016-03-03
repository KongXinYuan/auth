package com.xuguruogu.auth.facade;

import java.util.List;
import java.util.Map;

import com.xuguruogu.auth.dto.AdminDTO;

/**
 * 管理域
 *
 * @author benli.lbl
 * @version $Id: AdminFacade.java, v 0.1 Aug 29, 2015 11:47:32 AM benli.lbl Exp
 *          $
 */
public interface AdminFacade {

	public AdminDTO create(Long parentid, Integer level, String username, String password);

	public AdminDTO profile(Long adminid);

	public void chagePassword(Long adminid, String oldPassword, String newPassword);

	public void resetPassword(Long adminid, String password);

	public Map<String, Object> querylogLoginByPage(Long adminid, int limit, int pageIndex);

	void onLoginSucess(Long adminid, String ip);

	public Map<String, Object> queryByPage(int limit, int pageIndex, Long parentid);

	public void deleteById(Long adminid);

	public void deleteByIds(List<Long> adminids);

	public void updateLock(Long adminid, boolean lock);
}
