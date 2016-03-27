package com.xuguruogu.auth.dal.mybatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.xuguruogu.auth.dal.daointerface.KssStatisticsDao;
import com.xuguruogu.auth.dal.dataobject.KssCDKeyStatisticsDO;

@Component("kssStatisticsDao")
public class KssStatisticsDaoImpl extends KssDaoSupport implements KssStatisticsDao {

	protected KssStatisticsDaoImpl() {
		super("KSS_STATISTICS");
	}

	private static Logger logger = LoggerFactory.getLogger(KssStatisticsDaoImpl.class);

	@Override
	public List<KssCDKeyStatisticsDO> selectCDKeyStatistics(long softid, Long adminid) {

		Map<String, Object> param = new HashMap<String, Object>();
		if (null != adminid) {
			param.put("adminid", adminid);
		}
		param.put("softid", softid);
		try {

			return getSqlSession().selectList(this.getMybatisStatementName("selectCDKeyStatisticsWithSeg"), param);
		} catch (Exception e) {
			logger.error("selectCDKeyStatistics({},{})", softid, adminid, e);
			throw new KssSqlException(e);
		}

	}

}
