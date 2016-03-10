package com.xuguruogu.auth.dal.mybatis;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.xuguruogu.auth.dal.daointerface.KssKeySetDao;
import com.xuguruogu.auth.dal.dataobject.KssKeySetDO;
import com.xuguruogu.auth.dal.querycondition.KssKeySetQueryCondition;

@Component("kssKeySetDao")
public class KssKeySetDaoImpl extends KssDaoImplBase<KssKeySetDO, KssKeySetQueryCondition> implements KssKeySetDao {

	protected KssKeySetDaoImpl() {
		super("KSS_KEY_SET");
	}

	@Override
	public long updateLock(long keySetId, boolean lock) {

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("islock", lock);
		param.put("id", keySetId);

		return sqlSessionTemplate.update(this.getMybatisStatementName("updateLock"), param);
	}

	@Override
	public int update(long keySetId, String keyname, BigDecimal retailprice) {

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("keyname", keyname);
		param.put("retailprice", retailprice);
		param.put("id", keySetId);

		return sqlSessionTemplate.update(this.getMybatisStatementName("update"), param);
	}

}
