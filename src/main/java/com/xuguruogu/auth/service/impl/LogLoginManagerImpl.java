package com.xuguruogu.auth.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xuguruogu.auth.dal.daointerface.KssLogLoginDao;
import com.xuguruogu.auth.dal.dataobject.KssLogLoginDO;
import com.xuguruogu.auth.dal.querycondition.KssLogLoginQueryCondition;
import com.xuguruogu.auth.service.LogLoginManager;
import com.xuguruogu.auth.util.IPv4Util;

@Component("logLoginManager")
public class LogLoginManagerImpl implements LogLoginManager {

	@Autowired
	private KssLogLoginDao kssLogLoginDao;

	@Override
	public void create(Long adminid, String ip) {
		KssLogLoginDO kssLogLoginDO = new KssLogLoginDO();
		kssLogLoginDO.setAdminid(adminid.intValue());
		kssLogLoginDO.setLoginip(IPv4Util.ipToIntWithDefault(ip));
		kssLogLoginDO.setLogintime(new Date());
	}

	@Override
	public List<KssLogLoginDO> queryByPage(Long adminid, int limit, int pageIndex) {
		KssLogLoginQueryCondition query = new KssLogLoginQueryCondition();
		query.putAdminid(adminid.intValue()).pagination(pageIndex, limit);
		return kssLogLoginDao.selectListByQueryCondition(query);
	}

	@Override
	public long queryCount(Long adminid) {
		KssLogLoginQueryCondition query = new KssLogLoginQueryCondition();
		query.putAdminid(adminid.intValue());
		return kssLogLoginDao.selectCountByQueryCondition(query);
	}

}
