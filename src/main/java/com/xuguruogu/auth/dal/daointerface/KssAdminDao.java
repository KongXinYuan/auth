package com.xuguruogu.auth.dal.daointerface;

import java.math.BigDecimal;
import java.util.Date;

import com.xuguruogu.auth.dal.dataobject.KssAdminDO;
import com.xuguruogu.auth.dal.querycondition.KssAdminQueryCondition;

public interface KssAdminDao extends KssDaoBase<KssAdminDO, KssAdminQueryCondition> {

	public int updatePassword(Long id, String password);

	public int updateLock(Long id, Boolean lock);

	public int updateLastLogin(Long id, Date lastlogintime, Integer lastloginip);

	public int updatePowerlist(Long id, String powerlist);

	public int updateMoney(Long id, BigDecimal money, BigDecimal exmoney);
}
