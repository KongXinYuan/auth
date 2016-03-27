package com.xuguruogu.auth.dal.mybatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xuguruogu.auth.dal.daointerface.KssDaoBaseWithSeg;
import com.xuguruogu.auth.dal.dataobject.EntityWithSeg;
import com.xuguruogu.auth.dal.querycondition.QueryCondition;

public abstract class KssDaoImplBaseWithSeg<T extends EntityWithSeg, C extends QueryCondition<?>> extends KssDaoSupport
		implements KssDaoBaseWithSeg<T, C> {

	protected KssDaoImplBaseWithSeg(String DBNamespace) {
		super(DBNamespace);
	}

	private static Logger logger = LoggerFactory.getLogger(KssDaoImplBaseWithSeg.class);

	@Override
	public long insert(T entity) {

		try {
			return getSqlSession().insert(getMybatisStatementName("insertWithSeg"), entity);
		} catch (Exception e) {
			logger.error("insert({})", entity, e);
			throw new KssSqlException(e);
		}

	}

	@Override
	public T selectById(long softid, long id) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", id);
			map.put("softid", softid);

			T t = getSqlSession().selectOne(getMybatisStatementName("selectByIdWithSeg"), map);
			if (null != t) {
				t.setSoftid(softid);
			}
			return t;
		} catch (Exception e) {
			logger.error("selectById({},{})", softid, id, e);
			throw new KssSqlException(e);
		}
	}

	@Override
	public List<T> selectByIds(long softid, List<Long> ids) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("ids", ids);
			map.put("softid", softid);

			List<T> list = getSqlSession().selectList(getMybatisStatementName("selectByIdsWithSeg"), map);
			for (T t : list) {
				t.setSoftid(softid);
			}
			return list;
		} catch (Exception e) {
			logger.error("selectByIds({},{})", softid, ids, e);
			throw new KssSqlException(e);
		}
	}

	@Override
	public long deleteById(long softid, long id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("softid", softid);

		try {
			return getSqlSession().delete(getMybatisStatementName("deleteByIdWithSeg"), map);
		} catch (Exception e) {
			logger.error("deleteById({},{})", softid, id, e);
			throw new KssSqlException(e);
		}

	}

	@Override
	public long deleteByIds(long softid, List<Long> ids) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ids", ids);
		map.put("softid", softid);
		try {
			return getSqlSession().delete(getMybatisStatementName("deleteByIdsWithSeg"), map);
		} catch (Exception e) {
			logger.error("deleteByIds({},{})", softid, ids, e);
			throw new KssSqlException(e);
		}

	}

	@Override
	public long selectCountByQueryCondition(long softid, C queryCondition) {
		Map<String, Object> param = queryCondition.asMap();
		param.put("softid", softid);
		try {
			return getSqlSession().selectOne(getMybatisStatementName("selectCountByQueryConditionWithSeg"), param);
		} catch (Exception e) {
			logger.error("selectCountByQueryCondition({},{})", softid, queryCondition, e);
			throw new KssSqlException(e);
		}

	}

	@Override
	public List<T> selectListByQueryCondition(long softid, C queryCondition) {
		Map<String, Object> param = queryCondition.asMap();
		param.put("softid", softid);
		try {
			List<T> list = getSqlSession().selectList(getMybatisStatementName("selectByQueryConditionWithSeg"), param);
			for (T t : list) {
				t.setSoftid(softid);
			}
			return list;
		} catch (Exception e) {
			logger.error("selectListByQueryCondition({},{})", softid, queryCondition, e);
			throw new KssSqlException(e);
		}
	}

	@Override
	public T selectOneByQueryCondition(long softid, C queryCondition) {
		Map<String, Object> param = queryCondition.asMap();
		param.put("softid", softid);

		try {
			T t = getSqlSession().selectOne(getMybatisStatementName("selectByQueryConditionWithSeg"), param);
			if (null != t) {
				t.setSoftid(softid);
			}
			return t;

		} catch (Exception e) {
			logger.error("selectOneByQueryCondition({},{})", softid, queryCondition, e);
			throw new KssSqlException(e);
		}
	}

}