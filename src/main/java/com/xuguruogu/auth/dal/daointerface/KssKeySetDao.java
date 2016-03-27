package com.xuguruogu.auth.dal.daointerface;

import java.math.BigDecimal;
import java.util.List;

import com.xuguruogu.auth.dal.dataobject.KssKeySetDO;
import com.xuguruogu.auth.dal.enums.KeySetStatusType;
import com.xuguruogu.auth.dal.querycondition.KssKeySetQueryCondition;

public interface KssKeySetDao extends KssDaoBase<KssKeySetDO, KssKeySetQueryCondition> {

	public long update(long keySetId, BigDecimal cday, BigDecimal retailprice);

	public long updateStatusById(long id, KeySetStatusType status);

	public long updateStatusByIds(List<Long> ids, KeySetStatusType status);

	public long updateStatusBySoftId(long id, KeySetStatusType status);

	public long updateStatusBySoftIds(List<Long> ids, KeySetStatusType status);
}
