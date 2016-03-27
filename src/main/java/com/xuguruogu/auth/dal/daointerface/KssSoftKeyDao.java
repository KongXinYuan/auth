package com.xuguruogu.auth.dal.daointerface;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.xuguruogu.auth.dal.dataobject.KssSoftKeyDO;
import com.xuguruogu.auth.dal.enums.CDKeyStatusType;
import com.xuguruogu.auth.dal.querycondition.KssSoftKeyQueryCondition;

public interface KssSoftKeyDao extends KssDaoBaseWithSeg<KssSoftKeyDO, KssSoftKeyQueryCondition> {

	public long updateStatusById(long softid, long id, CDKeyStatusType status);

	public long updateStatusByIds(long softid, List<Long> ids, CDKeyStatusType status);

	public long updateRecharge(long softid, long id, Date usedtime, long userid, BigDecimal oldcday, BigDecimal newcday,
			CDKeyStatusType status);

	public long insertList(long softid, List<KssSoftKeyDO> cdkeys);

}