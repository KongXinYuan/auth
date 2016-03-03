package com.xuguruogu.auth.dal.mybatis;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.xuguruogu.auth.dal.daointerface.KssDaoBase;
import com.xuguruogu.auth.dal.dataobject.Entity;
import com.xuguruogu.auth.dal.querycondition.QueryCondition;

public abstract class KssDaoImplBase<T extends Entity, C extends QueryCondition<?>> implements KssDaoBase<T, C> {

	private String DBNamespace;

	@Autowired
	protected SqlSessionTemplate sqlSessionTemplate;

	protected KssDaoImplBase(String DBNamespace) {
		this.DBNamespace = DBNamespace;
	}

	protected final String getMybatisStatementName(String statementName) {
		return DBNamespace + "." + statementName;
	}

	@Override
	public int insert(T entity) {

		return sqlSessionTemplate.insert(getMybatisStatementName("insert"), entity);

	}

	public int update(T entity) {

		return sqlSessionTemplate.update(getMybatisStatementName("update"), entity);

	}

	@Override
	public T selectById(Long id) {
		return sqlSessionTemplate.selectOne(getMybatisStatementName("selectById"), id);
	}

	@Override
	public int deleteById(Long id) {

		return sqlSessionTemplate.delete(getMybatisStatementName("deleteById"), id);

	}

	@Override
	public int deleteByIds(List<Long> ids) {

		return sqlSessionTemplate.delete(getMybatisStatementName("deleteByIds"), ids);

	}

	@Override
	public int selectCountByQueryCondition(C queryCondition) {

		return sqlSessionTemplate.selectOne(getMybatisStatementName("selectCountByQueryCondition"),
				queryCondition.asMap());

	}

	@Override
	public List<T> selectListByQueryCondition(C queryCondition) {

		return sqlSessionTemplate.selectList(getMybatisStatementName("selectByQueryCondition"), queryCondition.asMap());

	}

	@Override
	public T selectOneByQueryCondition(C queryCondition) {

		return sqlSessionTemplate.selectOne(getMybatisStatementName("selectByQueryCondition"), queryCondition.asMap());

	}

}