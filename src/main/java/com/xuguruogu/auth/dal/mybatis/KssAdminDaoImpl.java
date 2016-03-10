package com.xuguruogu.auth.dal.mybatis;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.xuguruogu.auth.dal.daointerface.KssAdminDao;
import com.xuguruogu.auth.dal.dataobject.KssAdminDO;
import com.xuguruogu.auth.dal.querycondition.KssAdminQueryCondition;

@Component("kssAdminDao")
public class KssAdminDaoImpl extends KssDaoImplBase<KssAdminDO, KssAdminQueryCondition> implements KssAdminDao {

	protected KssAdminDaoImpl() {
		super("KSS_ADMIN");
	}

	@Override
	public int updatePassword(long id, String password) {

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("password", password);
		param.put("id", id);

		return sqlSessionTemplate.update(this.getMybatisStatementName("updatePassword"), param);
	}

	@Override
	public int updateLock(long id, boolean lock) {

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("islock", lock);
		param.put("id", id);

		return sqlSessionTemplate.update(this.getMybatisStatementName("updateLock"), param);
	}

	@Override
	public int updateLastLogin(long id, Date lastlogintime, long lastloginip) {

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("lastlogintime", lastlogintime);
		param.put("lastloginip", lastloginip);
		param.put("id", id);

		return sqlSessionTemplate.update(this.getMybatisStatementName("updateLastLogin"), param);
	}

	@Override
	public int updatePowerlist(long id, String powerlist) {

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("powerlist", powerlist);
		param.put("id", id);

		return sqlSessionTemplate.update(this.getMybatisStatementName("updatePowerlist"), param);
	}

	@Override
	public int updateMoney(long id, BigDecimal money, BigDecimal exmoney) {

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("money", money);
		param.put("exmoney", exmoney);
		param.put("id", id);

		return sqlSessionTemplate.update(this.getMybatisStatementName("updateMoney"), param);
	}

}
