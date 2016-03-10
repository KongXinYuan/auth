package com.xuguruogu.auth.dal.daointerface;

import java.math.BigDecimal;
import java.util.Date;

import com.xuguruogu.auth.dal.dataobject.KssSoftUserDO;
import com.xuguruogu.auth.dal.querycondition.KssSoftUserQueryCondition;

public interface KssSoftUserDao extends KssDaoBaseWithSeg<KssSoftUserDO, KssSoftUserQueryCondition> {

	public int updatePassword(long softid, long id, String password);

	public int updateLock(long softid, long id, boolean lock, Date lockendtime);

	public int updatePublic(long softid, long id, boolean pub);

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
	public int updateLastLogin(long softid, long id, Date lastlogintime, long lastloginip, String pccode,
			String linecode);

	public int updateRecharge(long softid, long id, BigDecimal cday, Date starttime, Date endtime);

}
