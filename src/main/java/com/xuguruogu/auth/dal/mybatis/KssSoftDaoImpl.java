package com.xuguruogu.auth.dal.mybatis;

import org.springframework.stereotype.Component;

import com.xuguruogu.auth.dal.daointerface.KssSoftDao;
import com.xuguruogu.auth.dal.dataobject.KssSoftDO;
import com.xuguruogu.auth.dal.querycondition.KssSoftQueryCondition;

@Component("kssSoftDao")
public class KssSoftDaoImpl extends KssDaoImplBase<KssSoftDO, KssSoftQueryCondition> implements KssSoftDao {

	protected KssSoftDaoImpl() {
		super("KSS_SOFT");
	}
}
