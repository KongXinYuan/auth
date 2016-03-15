package com.xuguruogu.auth.dal.mybatis;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.xuguruogu.auth.dal.daointerface.KssSoftKeyDao;
import com.xuguruogu.auth.dal.dataobject.KssSoftKeyDO;
import com.xuguruogu.auth.dal.querycondition.KssSoftKeyQueryCondition;

@Component("kssSoftKeyDao")
public class KssSoftKeyDaoImpl extends KssDaoImplBaseWithSeg<KssSoftKeyDO, KssSoftKeyQueryCondition>
		implements KssSoftKeyDao {

	protected KssSoftKeyDaoImpl() {
		super("KSS_SOFT_KEY");
	}

	@Override
	public int updateLock(long softid, long id, boolean lock) {

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("islock", lock);
		param.put("id", id);
		param.put("softid", softid);

		return sqlSessionTemplate.update(this.getMybatisStatementName("updateLockWithSeg"), param);
	}

	@Override
	public int updateRecharge(long softid, long id, Date usedtime, String useduser) {

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("usedtime", usedtime);
		param.put("useduser", useduser);
		param.put("id", id);
		param.put("softid", softid);
		return sqlSessionTemplate.update(this.getMybatisStatementName("updateRechargeWithSeg"), param);
	}

	@Override
	public int insertList(long softid, List<KssSoftKeyDO> cdkeys) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("cdkeys", cdkeys);
		param.put("softid", softid);
		return sqlSessionTemplate.insert(this.getMybatisStatementName("insertListWithSeg"), param);
	}

}
