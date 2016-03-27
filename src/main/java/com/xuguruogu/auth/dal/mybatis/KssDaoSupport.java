package com.xuguruogu.auth.dal.mybatis;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

public class KssDaoSupport extends SqlSessionDaoSupport {

	private String DBNamespace;

	@Autowired
	@Override
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	};

	protected KssDaoSupport(String DBNamespace) {
		this.DBNamespace = DBNamespace;
	}

	protected final String getMybatisStatementName(String statementName) {
		return DBNamespace + "." + statementName;
	}
}
