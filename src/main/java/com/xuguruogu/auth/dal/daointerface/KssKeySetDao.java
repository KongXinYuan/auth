package com.xuguruogu.auth.dal.daointerface;

import java.math.BigDecimal;

import com.xuguruogu.auth.dal.dataobject.KssKeySetDO;
import com.xuguruogu.auth.dal.querycondition.KssKeySetQueryCondition;

public interface KssKeySetDao extends KssDaoBase<KssKeySetDO, KssKeySetQueryCondition> {

	public int update(long keySetId, String keyname, BigDecimal retailprice);

	public long updateLock(long keySetId, boolean lock);
}
