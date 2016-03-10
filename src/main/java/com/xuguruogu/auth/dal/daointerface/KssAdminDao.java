package com.xuguruogu.auth.dal.daointerface;

import java.math.BigDecimal;
import java.util.Date;

import com.xuguruogu.auth.dal.dataobject.KssAdminDO;
import com.xuguruogu.auth.dal.querycondition.KssAdminQueryCondition;

public interface KssAdminDao extends KssDaoBase<KssAdminDO, KssAdminQueryCondition> {

	public int updatePassword(long id, String password);

	public int updateLock(long id, boolean lock);

	public int updateLastLogin(long id, Date lastlogintime, long lastloginip);

	public int updatePowerlist(long id, String powerlist);

	public int updateMoney(long id, BigDecimal money, BigDecimal exmoney);
}
