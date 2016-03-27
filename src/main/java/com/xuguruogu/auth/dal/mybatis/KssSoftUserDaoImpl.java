package com.xuguruogu.auth.dal.mybatis;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.xuguruogu.auth.dal.daointerface.KssSoftUserDao;
import com.xuguruogu.auth.dal.dataobject.KssSoftUserDO;
import com.xuguruogu.auth.dal.enums.UserStatusType;
import com.xuguruogu.auth.dal.querycondition.KssSoftUserQueryCondition;

@Component("kssSoftUserDao")
public class KssSoftUserDaoImpl extends KssDaoImplBaseWithSeg<KssSoftUserDO, KssSoftUserQueryCondition>
		implements KssSoftUserDao {

	protected KssSoftUserDaoImpl() {
		super("KSS_SOFT_USER");
	}

	private static Logger logger = LoggerFactory.getLogger(KssSoftUserDaoImpl.class);

	@Override
	public long updatePassword(long softid, long id, String password) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("password", password);
		param.put("id", id);
		param.put("softid", softid);
		try {
			return getSqlSession().update(this.getMybatisStatementName("updatePasswordWithSeg"), param);
		} catch (Exception e) {
			logger.error("updatePassword({},{},{})", softid, id, password, e);
			throw new KssSqlException(e);
		}
	}

	@Override
	public long updateStatusById(long softid, long id, UserStatusType status) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("status", status);
		param.put("id", id);
		param.put("softid", softid);
		try {
			return getSqlSession().update(this.getMybatisStatementName("updateStatusByIdWithSeg"), param);
		} catch (Exception e) {
			logger.error("updateStatusById({},{},{})", softid, id, status, e);
			throw new KssSqlException(e);
		}
	}

	@Override
	public long updateStatusByIds(long softid, List<Long> ids, UserStatusType status) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("status", status);
		param.put("ids", ids);
		param.put("softid", softid);
		try {
			return getSqlSession().update(this.getMybatisStatementName("updateStatusByIdsWithSeg"), param);
		} catch (Exception e) {
			logger.error("updateStatusByIds({},{},{})", softid, ids, status, e);
			throw new KssSqlException(e);
		}
	}

	@Override
	public long updateLastLogin(long softid, long id, Date lastlogintime, long lastloginip, String pccode,
			String linecode) {

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("lastlogintime", lastlogintime);
		param.put("lastloginip", lastloginip);
		param.put("pccode", pccode);
		param.put("linecode", linecode);
		param.put("id", id);
		param.put("softid", softid);
		try {
			return getSqlSession().update(this.getMybatisStatementName("updateLastLoginWithSeg"), param);
		} catch (Exception e) {
			logger.error("updateLastLogin({},{},{},{},{},{})", softid, id, lastlogintime, lastloginip, pccode, linecode,
					e);
			throw new KssSqlException(e);
		}
	}

	@Override
	public long updateRecharge(long softid, long id, BigDecimal cday, Date endtime) {

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("endtime", endtime);
		param.put("cday", cday);
		param.put("id", id);
		param.put("softid", softid);
		try {
			return getSqlSession().update(this.getMybatisStatementName("updateRechargeWithSeg"), param);
		} catch (Exception e) {
			logger.error("updateRecharge({},{},{},{})", softid, id, cday, endtime, e);
			throw new KssSqlException(e);
		}
	}

}
