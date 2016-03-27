package com.xuguruogu.auth.dal.daointerface;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.xuguruogu.auth.dal.dataobject.KssAdminDO;
import com.xuguruogu.auth.dal.enums.AdminStatusType;
import com.xuguruogu.auth.dal.querycondition.KssAdminQueryCondition;

public interface KssAdminDao extends KssDaoBase<KssAdminDO, KssAdminQueryCondition> {

	public long updatePassword(long id, String password);

	public long updateMoney(long id, BigDecimal money);

	public long updateStatusById(long id, AdminStatusType status);

	public long updateStatusByIds(List<Long> ids, AdminStatusType status);

	public long updateLastLogin(long id, Date lastlogintime, long lastloginip);

}
