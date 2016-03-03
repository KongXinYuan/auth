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

	public void create(String softname, String clientpubkey, String serverprivkey);

	public KssSoftDO selectOne(long softcode);

	public List<KssSoftDO> selectAll();

	public int count();

	public void update(long softid, boolean islock, int intervaltime, String clientpubkey, String serverprivkey);

}
