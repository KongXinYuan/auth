package com.xuguruogu.auth.dal.mybatis;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.xuguruogu.auth.dal.daointerface.KssSoftKeyDao;
import com.xuguruogu.auth.dal.dataobject.KssSoftKeyDO;
import com.xuguruogu.auth.dal.enums.CDKeyStatusType;
import com.xuguruogu.auth.dal.querycondition.KssSoftKeyQueryCondition;

@Component("kssSoftKeyDao")
public class KssSoftKeyDaoImpl extends KssDaoImplBaseWithSeg<KssSoftKeyDO, KssSoftKeyQueryCondition>
		implements KssSoftKeyDao {

	protected KssSoftKeyDaoImpl() {
		super("KSS_SOFT_KEY");
	}

	private static Logger logger = LoggerFactory.getLogger(KssSoftKeyDaoImpl.class);

	@Override
	public long updateStatusById(long softid, long id, CDKeyStatusType status) {
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
	public long updateStatusByIds(long softid, List<Long> ids, CDKeyStatusType status) {
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
	public long insertList(long softid, List<KssSoftKeyDO> cdkeys) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("cdkeys", cdkeys);
		param.put("softid", softid);
		try {
			return getSqlSession().insert(this.getMybatisStatementName("insertListWithSeg"), param);
		} catch (Exception e) {
			logger.error("insertList({},{})", softid, cdkeys, e);
			throw new KssSqlException(e);
		}
	}

	@Override
	public long updateRecharge(long softid, long id, Date usedtime, long userid, BigDecimal oldcday, BigDecimal newcday,
			CDKeyStatusType status) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("usedtime", usedtime);
		param.put("userid", userid);
		param.put("id", id);
		param.put("softid", softid);
		param.put("oldcday", oldcday);
		param.put("newcday", newcday);
		param.put("status", status);
		try {
			return getSqlSession().update(this.getMybatisStatementName("updateRechargeWithSeg"), param);
		} catch (Exception e) {
			logger.error("insertList({},{},{},{},{},{},{})", softid, id, usedtime, userid, oldcday, newcday, status, e);
			throw new KssSqlException(e);
		}
	}

}
