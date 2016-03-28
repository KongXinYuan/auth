package com.xuguruogu.auth.dal.mybatis;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.xuguruogu.auth.dal.daointerface.KssOptionDao;

@Component("kssOptionDao")
public class KssOptionDaoImpl extends KssDaoSupport implements KssOptionDao {

	protected KssOptionDaoImpl() {
		super("KSS_OPTIONS");
	}

	private static Logger logger = LoggerFactory.getLogger(KssOptionDaoImpl.class);

	@Override
	public String get(String name) {
		try {
			return getSqlSession().selectOne(this.getMybatisStatementName("get"), name);
		} catch (Exception e) {
			logger.error("get({})", name, e);
			throw new KssSqlException(e);
		}
	}

	@Override
	public long put(String name, String value) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("name", name);
		param.put("value", value);
		try {
			return getSqlSession().insert("put", param);
		} catch (Exception e) {
			logger.error("put({},{})", name, value, e);
			throw new KssSqlException(e);
		}
	}

}
