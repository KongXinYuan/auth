package com.xuguruogu.auth.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.xuguruogu.auth.dal.daointerface.KssOptionDao;
import com.xuguruogu.auth.service.OptionManager;

@Component("optionManager")
public class OptionManagerImpl implements OptionManager {

	@Autowired
	private KssOptionDao kssOptionDao;

	private static final String SOFTPREFIX = "soft";
	private static final String JOIN = "-";

	@Override
	@Cacheable(value = "option", key = "#name")
	public String get(String name) {
		if (StringUtils.isBlank(name)) {
			return null;
		}
		return kssOptionDao.get(name);
	}

	@Override
	@CacheEvict(value = "option", key = "#name")
	public void put(String name, String value) {
		if (StringUtils.isNotBlank(name) && null != value) {
			kssOptionDao.put(name, value);
		}
	}

	@Override
	public String getBySoftId(long softid, String name) {
		if (StringUtils.isBlank(name)) {
			return null;
		}
		return this.get(SOFTPREFIX + Long.toString(softid) + JOIN + name);
	}

	@Override
	public void putBySoftId(long softid, String name, String value) {
		if (StringUtils.isNotBlank(name) && null != value) {
			this.put(SOFTPREFIX + Long.toString(softid) + JOIN + name, value);
		}
	}

}
