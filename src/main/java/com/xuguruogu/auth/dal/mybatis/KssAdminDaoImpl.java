package com.xuguruogu.auth.dal.mybatis;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.xuguruogu.auth.dal.daointerface.KssAdminDao;
import com.xuguruogu.auth.dal.dataobject.KssAdminDO;
import com.xuguruogu.auth.dal.enums.AdminStatusType;
import com.xuguruogu.auth.dal.querycondition.KssAdminQueryCondition;

@Component("kssAdminDao")
public class KssAdminDaoImpl extends KssDaoImplBase<KssAdminDO, KssAdminQueryCondition> implements KssAdminDao {

	protected KssAdminDaoImpl() {
		super("KSS_ADMIN");
	}

	private static Logger logger = LoggerFactory.getLogger(KssAdminDaoImpl.class);

	@Override
	public long updatePassword(long id, String password) {

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("password", password);
		param.put("id", id);

		try {
			return getSqlSession().update(this.getMybatisStatementName("updatePassword"), param);
		} catch (Exception e) {
			logger.error("updatePassword({},{})", id, password, e);
			throw new KssSqlException(e);
		}
	}

	@Override
	public long updateStatusById(long id, AdminStatusType status) {
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
	public long updateStatusByIds(List<Long> ids, AdminStatusType status) {
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
	public long updateLastLogin(long id, Date lastlogintime, long lastloginip) {

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("lastlogintime", lastlogintime);
		param.put("lastloginip", lastloginip);
		param.put("id", id);
		try {
			return getSqlSession().update(this.getMybatisStatementName("updateLastLogin"), param);
		} catch (Exception e) {
			logger.error("updateLastLogin({},{},{})", id, lastlogintime, lastloginip, e);
			throw new KssSqlException(e);
		}
	}

	@Override
	public long updateMoney(long id, BigDecimal money) {

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("money", money);
		param.put("id", id);
		try {
			return getSqlSession().update(this.getMybatisStatementName("updateMoney"), param);
		} catch (Exception e) {
			logger.error("updateMoney({},{})", id, money, e);
			throw new KssSqlException(e);
		}
	}

}
