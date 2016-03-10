package com.xuguruogu.auth.dal.daointerface;

import java.util.Date;

import com.xuguruogu.auth.dal.dataobject.KssSoftKeyDO;
import com.xuguruogu.auth.dal.querycondition.KssSoftKeyQueryCondition;

public interface KssSoftKeyDao extends KssDaoBaseWithSeg<KssSoftKeyDO, KssSoftKeyQueryCondition> {

	public int updateLock(long softid, long id, boolean lock);

	public int updateRecharge(long softid, long id, Date usedtime, String useduser);

}