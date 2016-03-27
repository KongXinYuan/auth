package com.xuguruogu.auth.dal.mybatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.xuguruogu.auth.dal.daointerface.KssSoftDao;
import com.xuguruogu.auth.dal.dataobject.KssSoftDO;
import com.xuguruogu.auth.dal.enums.SoftStatusType;
import com.xuguruogu.auth.dal.querycondition.KssSoftQueryCondition;

@Component("kssSoftDao")
public class KssSoftDaoImpl extends KssDaoImplBase<KssSoftDO, KssSoftQueryCondition> implements KssSoftDao {

	protected KssSoftDaoImpl() {
		super("KSS_SOFT");
	}

	private static Logger logger = LoggerFactory.getLogger(KssSoftDaoImpl.class);

	@Override
	public int update(long softid, long intervaltime, String privkey) {

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("intervaltime", intervaltime);
		if (StringUtils.isNotBlank(privkey)) {
			param.put("privkey", privkey);
		}
		param.put("id", softid);
		try {
			return getSqlSession().update(this.getMybatisStatementName("update"), param);
		} catch (Exception e) {
			logger.error("update({},{},{})", softid, intervaltime, privkey, e);
			throw new KssSqlException(e);
		}

	}

	@Override
	public long updateSoftcode(long softid, long softcode) {

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("softcode", softcode);
		param.put("id", softid);

		try {
			return getSqlSession().update(this.getMybatisStatementName("updateSoftcode"), param);
		} catch (Exception e) {
			logger.error("updateSoftcode({},{})", softid, softcode, e);
			throw new KssSqlException(e);
		}

	}

	@Override
	public long updateStatusById(long id, SoftStatusType status) {
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
	public long updateStatusByIds(List<Long> ids, SoftStatusType status) {
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
	public long creatTableWithSeg(long softid) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("softid", softid);
		try {
			getSqlSession().update(this.getMybatisStatementName("creatKeyTableWithSeg"), param);
			return getSqlSession().update(this.getMybatisStatementName("creatUserTableWithSeg"), param);
		} catch (Exception e) {
			logger.error("creatTableWithSeg({})", softid, e);
			throw new KssSqlException(e);
		}
	}

}
