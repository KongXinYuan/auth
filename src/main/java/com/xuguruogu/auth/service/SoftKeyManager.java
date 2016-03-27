package com.xuguruogu.auth.service;

import java.util.List;
import java.util.Map;

import com.xuguruogu.auth.dal.dataobject.KssCDKeyStatisticsDO;
import com.xuguruogu.auth.dal.dataobject.KssSoftKeyDO;
import com.xuguruogu.auth.web.param.CDKeySearchParam;

public interface SoftKeyManager {

	// 创建cdkey
	public List<KssSoftKeyDO> create(long softid, long keysetid, String tag, long num);

	public Map<String, Object> search(long softid, CDKeySearchParam param);

	public Map<String, Object> order(Integer pageNo, Integer pageSize);

	public void lockByIds(long softid, List<Long> cdkeyids, boolean lock);

	public void deleteByIds(long softid, List<Long> keyids);

	public List<KssCDKeyStatisticsDO> statistics(long softid);
}
