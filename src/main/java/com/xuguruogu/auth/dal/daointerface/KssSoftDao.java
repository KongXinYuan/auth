package com.xuguruogu.auth.dal.daointerface;

import java.util.List;

import com.xuguruogu.auth.dal.dataobject.KssSoftDO;
import com.xuguruogu.auth.dal.enums.SoftStatusType;
import com.xuguruogu.auth.dal.querycondition.KssSoftQueryCondition;

public interface KssSoftDao extends KssDaoBase<KssSoftDO, KssSoftQueryCondition> {

	public int update(long id, long intervaltime, String privkey);

	public long updateStatusById(long id, SoftStatusType status);

	public long updateStatusByIds(List<Long> ids, SoftStatusType status);

	public long creatTableWithSeg(long id);

}
