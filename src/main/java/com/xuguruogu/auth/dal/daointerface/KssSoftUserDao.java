package com.xuguruogu.auth.dal.daointerface;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.xuguruogu.auth.dal.dataobject.KssSoftUserDO;
import com.xuguruogu.auth.dal.enums.UserStatusType;
import com.xuguruogu.auth.dal.querycondition.KssSoftUserQueryCondition;

public interface KssSoftUserDao extends KssDaoBaseWithSeg<KssSoftUserDO, KssSoftUserQueryCondition> {

	public long updatePassword(long softid, long id, String password);

	public long updateStatusById(long softid, long id, UserStatusType status);

	public long updateStatusByIds(long softid, List<Long> ids, UserStatusType status);

	/**
	 * @param id
	 *            用户id
	 * @param lastlogintime
	 *            上次登录时间
	 * @param lastloginip
	 *            上次登录ip
	 * @param pccode
	 *            机器码
	 * @param linecode
	 *            rookie，每次运行产生一个随机字符串
	 * @return
	 */
	public long updateLastLogin(long softid, long id, Date lastlogintime, long lastloginip, String pccode,
			String linecode);

	public long updateRecharge(long softid, long id, BigDecimal cday, Date endtime);

}
