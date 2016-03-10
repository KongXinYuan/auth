package com.xuguruogu.auth.dal.daointerface;

import com.xuguruogu.auth.dal.dataobject.KssSoftDO;
import com.xuguruogu.auth.dal.querycondition.KssSoftQueryCondition;

public interface KssSoftDao extends KssDaoBase<KssSoftDO, KssSoftQueryCondition> {

	public int update(long softid, boolean lock, long intervaltime, String clientpubkey, String serverprivkey);

	public long updateSoftcode(long softid, long softcode);

	public long updateLock(long softid, boolean lock);
}
