package com.xuguruogu.auth.dal.daointerface;

import java.math.BigDecimal;
import java.util.List;

import com.xuguruogu.auth.dal.dataobject.KssPowerDO;
import com.xuguruogu.auth.dal.querycondition.KssPowerQueryCondition;

public interface KssPowerDao extends KssDaoBase<KssPowerDO, KssPowerQueryCondition> {

	public long insertList(List<KssPowerDO> list);

	public long update(long id, BigDecimal sellprice);

	public long deleteByKeySetId(long id);

	public long deleteByKeySetIds(List<Long> ids);

	public long deleteByKeysetIdAndAdminid(long keysetid, long adminid);

	public long deleteByAdminId(long id);

	public long deleteByAdminIds(List<Long> ids);

	public long deleteBySoftId(long id);

	public long deleteBySoftIds(List<Long> ids);
}
