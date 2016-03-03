package com.xuguruogu.auth.service;

import java.util.List;

import com.xuguruogu.auth.dal.dataobject.KssLogLoginDO;

/**
 * 管理员登录记录
 *
 * @author benli.lbl
 * @version $Id: AdminLoginLogManager.java, v 0.1 Aug 29, 2015 4:00:06 PM
 *          benli.lbl Exp $
 */
public interface LogLoginManager {

	public void create(Long adminid, String ip);

	public List<KssLogLoginDO> queryPage(Long adminid, int limit, int pageIndex);

	public int queryCount(Long adminid);

}
