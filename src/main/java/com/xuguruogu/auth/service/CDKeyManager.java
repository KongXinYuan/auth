package com.xuguruogu.auth.service;

import java.util.List;

import com.xuguruogu.auth.dal.dataobject.KssSoftKeyDO;
import com.xuguruogu.auth.dto.CDKeyDTD;
import com.xuguruogu.auth.web.param.CDKeySearchParam;

public interface CDKeyManager {

	// 创建cdkey
	public List<CDKeyDTD> create(long softid, long adminid, long keysetid, String tag, long num);

	public List<CDKeyDTD> search(long softid, long adminid, CDKeySearchParam param);

	public KssSoftKeyDO queryByCdkey(long softid, String cdkey);

	public void updateLock(long softid, long keyid, boolean lock);

	public void deleteById(long softid, long keyid);

	public void deleteByIds(long softid, List<Long> keyids);
}
