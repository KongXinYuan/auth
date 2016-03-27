package com.xuguruogu.auth.dal.mybatis;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xuguruogu.auth.dal.daointerface.KssDaoBase;
import com.xuguruogu.auth.dal.dataobject.Entity;
import com.xuguruogu.auth.dal.querycondition.QueryCondition;

public abstract class KssDaoImplBase<T extends Entity, C extends QueryCondition<?>> extends KssDaoSupport
		implements KssDaoBase<T, C> {

	protected KssDaoImplBase(String DBNamespace) {
		super(DBNamespace);
	}

	private static Logger logger = LoggerFactory.getLogger(KssDaoImplBase.class);

	@Override
	public long insert(T entity) {

		try {
			return getSqlSession().insert(getMybatisStatementName("insert"), entity);
		} catch (Exception e) {
			logger.error("insert({})", entity, e);
			throw new KssSqlException(e);
		}
	}

	@Override
	public T selectById(long id) {
		try {
			return getSqlSession().selectOne(getMybatisStatementName("selectById"), id);
		} catch (Exception e) {
			logger.error("selectById({})", id, e);
			throw new KssSqlException(e);
		}
	}

	@Override
	public List<T> selectByIds(List<Long> ids) {
		try {
			return getSqlSession().selectList(getMybatisStatementName("selectByIds"), ids);
		} catch (Exception e) {
			logger.error("selectByIds({})", ids, e);
			throw new KssSqlException(e);
		}
	}

	@Override
	public long deleteById(long id) {
		try {
			return getSqlSession().delete(getMybatisStatementName("deleteById"), id);
		} catch (Exception e) {
			logger.error("deleteById({})", id, e);
			throw new KssSqlException(e);
		}

	}

	@Override
	public long deleteByIds(List<Long> ids) {
		try {
			return getSqlSession().delete(getMybatisStatementName("deleteByIds"), ids);
		} catch (Exception e) {
			logger.error("deleteByIds({})", ids, e);
			throw new KssSqlException(e);
		}
	}

	@Override
	public long selectCountByQueryCondition(C queryCondition) {

		try {
			return getSqlSession().selectOne(getMybatisStatementName("selectCountByQueryCondition"),
					queryCondition.asMap());
		} catch (Exception e) {
			logger.error("selectCountByQueryCondition({})", queryCondition, e);
			throw new KssSqlException(e);
		}

	}

	@Override
	public List<T> selectListByQueryCondition(C queryCondition) {
		try {
			return getSqlSession().selectList(getMybatisStatementName("selectByQueryCondition"),
					queryCondition.asMap());
		} catch (Exception e) {
			logger.error("selectListByQueryCondition({})", queryCondition, e);
			throw new KssSqlException(e);
		}

	}

	@Override
	public T selectOneByQueryCondition(C queryCondition) {
		try {
			return getSqlSession().selectOne(getMybatisStatementName("selectByQueryCondition"), queryCondition.asMap());
		} catch (Exception e) {
			logger.error("selectOneByQueryCondition({})", queryCondition, e);
			throw new KssSqlException(e);
		}

	}

}