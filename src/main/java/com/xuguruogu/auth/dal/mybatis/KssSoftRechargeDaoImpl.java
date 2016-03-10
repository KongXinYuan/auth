package com.xuguruogu.auth.dal.mybatis;

import org.springframework.stereotype.Component;

import com.xuguruogu.auth.dal.daointerface.KssSoftRechargeDao;
import com.xuguruogu.auth.dal.dataobject.KssSoftRechargeDO;
import com.xuguruogu.auth.dal.querycondition.KssSoftRechargeQueryCondition;

@Component("kssSoftRechargeDao")
public class KssSoftRechargeDaoImpl extends KssDaoImplBaseWithSeg<KssSoftRechargeDO, KssSoftRechargeQueryCondition>
		implements KssSoftRechargeDao {

	protected KssSoftRechargeDaoImpl() {
		super("KSS_SOFT_RECHARGE");
	}

}
