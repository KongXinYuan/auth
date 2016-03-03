package com.xuguruogu.auth.dal.mybatis;

import org.springframework.stereotype.Component;

import com.xuguruogu.auth.dal.daointerface.KssLogLoginDao;
import com.xuguruogu.auth.dal.dataobject.KssLogLoginDO;
import com.xuguruogu.auth.dal.querycondition.KssLogLoginQueryCondition;

@Component("kssLogLoginDao")
public class KssLogLoginDaoImpl extends KssDaoImplBase<KssLogLoginDO, KssLogLoginQueryCondition> implements
		KssLogLoginDao {

	protected KssLogLoginDaoImpl() {
		super("KSS_LOG_LOGIN");
	}

}
