package com.xuguruogu.auth.service;

import java.util.List;
import java.util.Map;

import com.xuguruogu.auth.dal.dataobject.KssSoftUserDO;
import com.xuguruogu.auth.web.param.UserSearchParam;

public interface SoftUserManager {

	// 注册
	public KssSoftUserDO registerWithCdkey(long softid, String username, String password, String cdkey);

	// 获取信息
	public KssSoftUserDO queryBygUserId(long softid, long userid);

	// 登录
	public KssSoftUserDO login(long softid, String username, String password, long ip, String pccode, String linecode);

	// 充值
	public KssSoftUserDO recharge(long softid, String username, String cdkey);

	// 校验
	public KssSoftUserDO valid(long softid, String username, String linecode);

	// 锁定
	public void lockByIds(long softid, List<Long> userids, boolean lock);

	// 删除
	public void deleteByIds(long softid, List<Long> userids);

	// 搜索
	public Map<String, Object> search(long softid, UserSearchParam param);
}
