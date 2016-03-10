package com.xuguruogu.auth.service;

import java.util.List;

import com.xuguruogu.auth.dal.dataobject.KssSoftKeyDO;

public interface SoftKeyManager {

	// 创建cdkey
	public List<KssSoftKeyDO> create(long softid, long adminid, long keysetid, String tag, long num);

	public KssSoftKeyDO queryByCdkey(long softid, String cdkey);

	public void updateLock(long softid, long keyid, boolean lock);

	public List<KssSoftKeyDO> queryByPage(long softid, long adminid, int limit, final int pageIndex);

	public long queryCount(long softid, long adminid);

	public List<KssSoftKeyDO> queryByOrdernum(long softid, String ordernum);

	public long queryCountByOrdernum(long softid, String ordernum);

	public void deleteById(long softid, long keyid);

	public void deleteByIds(long softid, List<Long> keyids);
}
