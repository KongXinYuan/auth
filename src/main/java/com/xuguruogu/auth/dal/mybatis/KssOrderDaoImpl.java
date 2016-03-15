package com.xuguruogu.auth.dal.mybatis;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.xuguruogu.auth.dal.daointerface.KssOrderDao;
import com.xuguruogu.auth.dal.dataobject.KssOrderDO;
import com.xuguruogu.auth.dal.querycondition.KssOrderQueryCondition;

@Component("kssOrderDao")
public class KssOrderDaoImpl extends KssDaoImplBase<KssOrderDO, KssOrderQueryCondition> implements KssOrderDao {

	protected KssOrderDaoImpl() {
		super("KSS_ORDER");
	}

	@Override
	public int update(String ordernum, boolean done, long beginid) {

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("beginid", beginid);
		param.put("isdone", done);
		param.put("ordernum", ordernum);

		return sqlSessionTemplate.update(this.getMybatisStatementName("update"), param);
	}

}
