package com.xuguruogu.auth.dal.daointerface;

import java.util.Date;
import java.util.List;

import com.xuguruogu.auth.dal.dataobject.KssSoftKeyDO;
import com.xuguruogu.auth.dal.querycondition.KssSoftKeyQueryCondition;

public interface KssSoftKeyDao extends KssDaoBaseWithSeg<KssSoftKeyDO, KssSoftKeyQueryCondition> {

	public int updateLock(long softid, long id, boolean lock);

	public int updateRecharge(long softid, long id, Date usedtime, String useduser);

	public int insertList(long softid, List<KssSoftKeyDO> cdkeys);

}