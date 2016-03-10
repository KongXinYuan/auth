package com.xuguruogu.auth.dal.mybatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.xuguruogu.auth.dal.daointerface.KssDaoBaseWithSeg;
import com.xuguruogu.auth.dal.dataobject.EntityWithSeg;
import com.xuguruogu.auth.dal.querycondition.QueryCondition;

public abstract class KssDaoImplBaseWithSeg<T extends EntityWithSeg, C extends QueryCondition<?>>
		implements KssDaoBaseWithSeg<T, C> {

	private String DBNamespace;

	@Autowired
	protected SqlSessionTemplate sqlSessionTemplate;

	protected KssDaoImplBaseWithSeg(String DBNamespace) {
		this.DBNamespace = DBNamespace;
	}

	protected final String getMybatisStatementName(String statementName) {
		return DBNamespace + "." + statementName;
	}

	@Override
	public long insert(T entity) {

		return sqlSessionTemplate.insert(getMybatisStatementName("insertWithSeg"), entity);

	}

	@Override
	public T selectById(long softid, long id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("softid", softid);

		T t = sqlSessionTemplate.selectOne(getMybatisStatementName("selectByIdWithSeg"), map);
		t.setSoftid(softid);
		return t;
	}

	@Override
	public long deleteById(long softid, long id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("softid", softid);

		return sqlSessionTemplate.delete(getMybatisStatementName("deleteByIdWithSeg"), map);

	}

	@Override
	public long deleteByIds(long softid, List<Long> ids) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ids", ids);
		map.put("softid", softid);
		return sqlSessionTemplate.delete(getMybatisStatementName("deleteByIdsWithSeg"), map);

	}

	@Override
	public long selectCountByQueryCondition(long softid, C queryCondition) {

		return sqlSessionTemplate.selectOne(getMybatisStatementName("selectCountByQueryConditionWithSeg"),
				queryCondition.asMap().put("softid", softid));

	}

	@Override
	public List<T> selectListByQueryCondition(long softid, C queryCondition) {

		List<T> tlist = sqlSessionTemplate.selectList(getMybatisStatementName("selectByQueryConditionWithSeg"),
				queryCondition.asMap().put("softid", softid));
		for (T t : tlist) {
			t.setSoftid(softid);
		}
		return tlist;
	}

	@Override
	public T selectOneByQueryCondition(long softid, C queryCondition) {

		T t = sqlSessionTemplate.selectOne(getMybatisStatementName("selectByQueryConditionWithSeg"),
				queryCondition.asMap().put("softid", softid));
		t.setSoftid(softid);
		return t;

	}

}