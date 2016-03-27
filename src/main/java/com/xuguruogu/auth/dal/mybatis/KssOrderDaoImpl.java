package com.xuguruogu.auth.dal.mybatis;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.xuguruogu.auth.dal.daointerface.KssOrderDao;
import com.xuguruogu.auth.dal.dataobject.KssOrderDO;
import com.xuguruogu.auth.dal.querycondition.KssOrderQueryCondition;

@Component("kssOrderDao")
public class KssOrderDaoImpl extends KssDaoImplBase<KssOrderDO, KssOrderQueryCondition> implements KssOrderDao {

	protected KssOrderDaoImpl() {
		super("KSS_ORDER");
	}

	private static Logger logger = LoggerFactory.getLogger(KssOrderDaoImpl.class);

	@Override
	public long update(String ordernum, long beginid) {

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("beginid", beginid);
		param.put("ordernum", ordernum);
		try {
			return getSqlSession().update(this.getMybatisStatementName("update"), param);
		} catch (Exception e) {
			logger.error("update({},{})", ordernum, beginid, e);
			throw new KssSqlException(e);
		}
	}

}
