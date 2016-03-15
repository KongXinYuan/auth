package com.xuguruogu.auth.dal.daointerface;

import com.xuguruogu.auth.dal.dataobject.KssOrderDO;
import com.xuguruogu.auth.dal.querycondition.KssOrderQueryCondition;

public interface KssOrderDao extends KssDaoBase<KssOrderDO, KssOrderQueryCondition> {

	public int update(String ordernum, boolean done, long beginid);
}
