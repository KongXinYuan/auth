package com.xuguruogu.auth.service;

import java.util.List;

import com.xuguruogu.auth.dal.dataobject.KssSoftDO;

/**
 * 软件管理
 *
 * @author benli.lbl
 * @version $Id: AdminLoginLogManager.java, v 0.1 Aug 29, 2015 4:00:06 PM
 *          benli.lbl Exp $
 */
public interface SoftManager {
	public List<KssSoftDO> listAll();

	public KssSoftDO detail(long softid);

	public KssSoftDO update(long softid, long intervaltime, String privkey);

	public void updateLock(long softid, boolean lock);

	public void deleteByIds(List<Long> softids);

	public KssSoftDO create(String softname, long intervaltime, String privkey);

}
