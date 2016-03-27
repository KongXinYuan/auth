package com.xuguruogu.auth.dal.mybatis;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.xuguruogu.auth.dal.daointerface.KssPowerDao;
import com.xuguruogu.auth.dal.dataobject.KssPowerDO;
import com.xuguruogu.auth.dal.querycondition.KssPowerQueryCondition;

@Component("kssPowerDao")
public class KssPowerDaoImpl extends KssDaoImplBase<KssPowerDO, KssPowerQueryCondition> implements KssPowerDao {

	protected KssPowerDaoImpl() {
		super("KSS_POWER");
	}

	private static Logger logger = LoggerFactory.getLogger(KssPowerDaoImpl.class);

	@Override
	public long insertList(List<KssPowerDO> list) {
		try {
			return getSqlSession().insert(this.getMybatisStatementName("insertList"), list);
		} catch (Exception e) {
			logger.error("insertList({})", list);
			throw new KssSqlException(e);
		}
	}

	@Override
	public long update(long id, BigDecimal sellprice) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("sellprice", sellprice);
		param.put("id", id);
		try {
			return getSqlSession().insert(this.getMybatisStatementName("update"), param);
		} catch (Exception e) {
			logger.error("update({},{})", id, sellprice, e);
			throw new KssSqlException(e);
		}
	}

	@Override
	public long deleteByKeySetId(long id) {
		try {

			return getSqlSession().delete(getMybatisStatementName("deleteByKeySetId"), id);
		} catch (Exception e) {
			logger.error("deleteByKeySetId({})", id, e);
			throw new KssSqlException(e);
		}
	}

	@Override
	public long deleteByKeySetIds(List<Long> ids) {

		try {
			return getSqlSession().delete(getMybatisStatementName("deleteByKeySetIds"), ids);
		} catch (Exception e) {
			logger.error("deleteByKeySetIds({})", ids, e);
			throw new KssSqlException(e);
		}
	}

	@Override
	public long deleteByKeysetIdAndAdminid(long keysetid, long adminid) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("keysetid", keysetid);
		param.put("adminid", adminid);
		try {
			return getSqlSession().delete(getMybatisStatementName("deleteByKeysetIdAndAdminid"), param);
		} catch (Exception e) {
			logger.error("deleteByKeysetIdAndAdminid({},{})", keysetid, adminid, e);
			throw new KssSqlException(e);
		}
	}

	@Override
	public long deleteByAdminId(long id) {
		try {
			return getSqlSession().delete(getMybatisStatementName("deleteByAdminId"), id);
		} catch (Exception e) {
			logger.error("deleteByAdminId({})", id, e);
			throw new KssSqlException(e);
		}
	}

	@Override
	public long deleteByAdminIds(List<Long> ids) {
		try {
			return getSqlSession().delete(getMybatisStatementName("deleteByAdminIds"), ids);
		} catch (Exception e) {
			logger.error("deleteByAdminIds({})", ids, e);
			throw new KssSqlException(e);
		}
	}

	@Override
	public long deleteBySoftId(long id) {
		try {

			return getSqlSession().delete(getMybatisStatementName("deleteBySoftId"), id);
		} catch (Exception e) {
			logger.error("deleteBySoftId({})", id, e);
			throw new KssSqlException(e);
		}
	}

	@Override
	public long deleteBySoftIds(List<Long> ids) {

		try {
			return getSqlSession().delete(getMybatisStatementName("deleteBySoftIds"), ids);
		} catch (Exception e) {
			logger.error("deleteBySoftIds({})", ids, e);
			throw new KssSqlException(e);
		}
	}

}
