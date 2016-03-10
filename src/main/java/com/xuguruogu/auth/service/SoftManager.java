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

	public KssSoftDO queryById(final long softid);

	public KssSoftDO create(String softname, String clientpubkey, String serverprivkey);

	public KssSoftDO selectBySoftcode(long softcode);

	public KssSoftDO selectBySoftname(String softname);

	public List<KssSoftDO> selectAll();

	public long count();

	public void update(long softid, boolean islock, long intervaltime, String clientpubkey, String serverprivkey);

	public void deleteById(final long softid);
}
