package com.xuguruogu.auth.service;

import java.math.BigDecimal;
import java.util.List;

import com.xuguruogu.auth.dal.dataobject.KssSoftUserDO;

public interface SoftUserManager {

	// 注册
	public KssSoftUserDO registerWithCdkey(long softid, String username, String password, String cdkey);

	public KssSoftUserDO queryById(long softid, long userid);

	public KssSoftUserDO queryByUsername(long softid, String username);

	public void updatePassword(long softid, long userid, String password);

	// 登录
	public KssSoftUserDO login(long softid, String username, String password, long ip, String pccode, String linecode);

	// 充值
	public KssSoftUserDO recharge(long softid, String username, String cdkey);

	// 校验
	public KssSoftUserDO valid(long softid, String username, String linecode);

	public void updateLock(long softid, long userid, boolean islock, BigDecimal cday);

	public void updatePublic(long softid, long userid, boolean ispublic);

	public List<KssSoftUserDO> queryByPage(long softid, long adminid, int limit, final int pageIndex);

	public long queryCount(long softid, long adminid);

	public void deleteById(long softid, long userid);

	public void deleteByIds(long softid, List<Long> userids);
}
