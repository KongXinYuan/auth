package com.xuguruogu.auth.dal.mybatis;

import org.springframework.stereotype.Component;

import com.xuguruogu.auth.dal.daointerface.KssOrderDao;
import com.xuguruogu.auth.dal.dataobject.KssOrderDO;
import com.xuguruogu.auth.dal.querycondition.KssOrderQueryCondition;

@Component("kssOrderDao")
public class KssOrderDaoImpl extends KssDaoImplBase<KssOrderDO, KssOrderQueryCondition> implements KssOrderDao {

	protected KssOrderDaoImpl() {
		super("KSS_ORDER");
	}

}
