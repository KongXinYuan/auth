package com.xuguruogu.auth.dal.mybatis;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.xuguruogu.auth.dal.daointerface.KssSoftUserDao;
import com.xuguruogu.auth.dal.dataobject.KssSoftUserDO;
import com.xuguruogu.auth.dal.querycondition.KssSoftUserQueryCondition;

@Component("kssSoftUserDao")
public class KssSoftUserDaoImpl extends KssDaoImplBaseWithSeg<KssSoftUserDO, KssSoftUserQueryCondition>
		implements KssSoftUserDao {

	protected KssSoftUserDaoImpl() {
		super("KSS_SOFT_USER");
	}

	@Override
	public int updatePassword(long softid, long id, String password) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("password", password);
		param.put("id", id);
		param.put("softid", softid);
		return sqlSessionTemplate.update(this.getMybatisStatementName("updatePasswordWithSeg"), param);

	}

	@Override
	public int updateLock(long softid, long id, boolean lock, Date lockendtime) {

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("islock", lock);
		param.put("lockendtime", lockendtime);
		param.put("id", id);
		param.put("softid", softid);

		return sqlSessionTemplate.update(this.getMybatisStatementName("updateLockWithSeg"), param);
	}

	@Override
	public int updatePublic(long softid, long id, boolean pub) {

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("ispub", pub);
		param.put("id", id);
		param.put("softid", softid);

		return sqlSessionTemplate.update(this.getMybatisStatementName("updatePublicWithSeg"), param);
	}

	@Override
	public int updateLastLogin(long softid, long id, Date lastlogintime, long lastloginip, String pccode,
			String linecode) {

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("lastlogintime", lastlogintime);
		param.put("lastloginip", lastloginip);
		param.put("pccode", pccode);
		param.put("linecode", linecode);
		param.put("id", id);
		param.put("softid", softid);
		return sqlSessionTemplate.update(this.getMybatisStatementName("updateLastLoginWithSeg"), param);
	}

	@Override
	public int updateRecharge(long softid, long id, BigDecimal cday, Date starttime, Date endtime) {

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("starttime", starttime);
		param.put("endtime", endtime);
		param.put("cday", cday);
		param.put("id", id);
		param.put("softid", softid);
		return sqlSessionTemplate.update(this.getMybatisStatementName("updateRechargeWithSeg"), param);
	}

}
