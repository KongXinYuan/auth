package com.xuguruogu.auth.dal.mybatis;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.xuguruogu.auth.dal.daointerface.KssKeySetDao;
import com.xuguruogu.auth.dal.dataobject.KssKeySetDO;
import com.xuguruogu.auth.dal.enums.KeySetStatusType;
import com.xuguruogu.auth.dal.querycondition.KssKeySetQueryCondition;

@Component("kssKeySetDao")
public class KssKeySetDaoImpl extends KssDaoImplBase<KssKeySetDO, KssKeySetQueryCondition> implements KssKeySetDao {

	protected KssKeySetDaoImpl() {
		super("KSS_KEY_SET");
	}

	private static Logger logger = LoggerFactory.getLogger(KssKeySetDaoImpl.class);

	@Override
	public long updateStatusById(long id, KeySetStatusType status) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("status", status);
		param.put("id", id);
		try {
			return getSqlSession().update(this.getMybatisStatementName("updateStatusById"), param);
		} catch (Exception e) {
			logger.error("updateStatusById({},{})", id, status, e);
			throw new KssSqlException(e);
		}
	}

	@Override
	public long updateStatusByIds(List<Long> ids, KeySetStatusType status) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("status", status);
		param.put("ids", ids);
		try {
			return getSqlSession().update(this.getMybatisStatementName("updateStatusByIds"), param);
		} catch (Exception e) {
			logger.error("updateStatusByIds({},{})", ids, status, e);
			throw new KssSqlException(e);
		}
	}

	@Override
	public long update(long keySetId, BigDecimal cday, BigDecimal retailprice) {

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("retailprice", retailprice);
		param.put("cday", cday);
		param.put("id", keySetId);
		try {
			return getSqlSession().update(this.getMybatisStatementName("update"), param);
		} catch (Exception e) {
			logger.error("update({},{},{})", keySetId, cday, retailprice, e);
			throw new KssSqlException(e);
		}
	}

	@Override
	public long updateStatusBySoftId(long id, KeySetStatusType status) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("status", status);
		param.put("id", id);
		try {
			return getSqlSession().update(this.getMybatisStatementName("updateStatusBySoftId"), param);
		} catch (Exception e) {
			logger.error("updateStatusBySoftId({},{})", id, status, e);
			throw new KssSqlException(e);
		}
	}

	@Override
	public long updateStatusBySoftIds(List<Long> ids, KeySetStatusType status) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("status", status);
		param.put("ids", ids);
		try {
			return getSqlSession().update(this.getMybatisStatementName("updateStatusBySoftIds"), param);
		} catch (Exception e) {
			logger.error("updateStatusBySoftIds({},{})", ids, status, e);
			throw new KssSqlException(e);
		}
	}

}
