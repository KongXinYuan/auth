package com.xuguruogu.auth.dal.mybatis;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.xuguruogu.auth.dal.daointerface.KssSoftDao;
import com.xuguruogu.auth.dal.dataobject.KssSoftDO;
import com.xuguruogu.auth.dal.querycondition.KssSoftQueryCondition;

@Component("kssSoftDao")
public class KssSoftDaoImpl extends KssDaoImplBase<KssSoftDO, KssSoftQueryCondition> implements KssSoftDao {

	protected KssSoftDaoImpl() {
		super("KSS_SOFT");
	}

	@Override
	public int update(long softid, boolean islock, int intervaltime, String clientpubkey, String serverprivkey) {

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("intervaltime", intervaltime);
		param.put("clientpubkey", clientpubkey);
		param.put("serverprivkey", serverprivkey);
		param.put("islock", islock);
		param.put("id", softid);

		return sqlSessionTemplate.update(this.getMybatisStatementName("update"), param);

	}

	@Override
	public long selectLastId() {
		return sqlSessionTemplate.selectOne(this.getMybatisStatementName("selectLastId"));
	}

	@Override
	public long updateSoftcode(long softid, long softcode) {

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("softcode", softcode);
		param.put("id", softid);

		return sqlSessionTemplate.update(this.getMybatisStatementName("update"), param);

	}

	@Override
	public long updateLock(long softid, boolean islock) {

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("islock", islock);
		param.put("id", softid);

		return sqlSessionTemplate.update(this.getMybatisStatementName("update"), param);
	}

}
