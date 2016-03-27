package com.xuguruogu.auth.dal.mybatis;

import org.springframework.stereotype.Component;

import com.xuguruogu.auth.dal.daointerface.KssFinanceDao;
import com.xuguruogu.auth.dal.dataobject.KssFinanceDO;
import com.xuguruogu.auth.dal.querycondition.KssFinanceQueryCondition;

@Component("kssFinanceDao")
public class KssFinanceDaoImpl extends KssDaoImplBase<KssFinanceDO, KssFinanceQueryCondition> implements KssFinanceDao {

	protected KssFinanceDaoImpl() {
		super("KSS_FINANCE");
	}

}
