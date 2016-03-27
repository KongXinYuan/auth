package com.xuguruogu.auth.dal.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xuguruogu.auth.dal.dataobject.KssAdminDO;
import com.xuguruogu.auth.dal.dataobject.KssCDKeyStatisticsDO;
import com.xuguruogu.auth.dal.dataobject.KssFinanceDO;
import com.xuguruogu.auth.dal.dataobject.KssKeySetDO;
import com.xuguruogu.auth.dal.dataobject.KssLogLoginDO;
import com.xuguruogu.auth.dal.dataobject.KssOrderDO;
import com.xuguruogu.auth.dal.dataobject.KssPowerDO;
import com.xuguruogu.auth.dal.dataobject.KssSoftDO;
import com.xuguruogu.auth.dal.dataobject.KssSoftKeyDO;
import com.xuguruogu.auth.dal.dataobject.KssSoftUserDO;
import com.xuguruogu.auth.interceptor.KssException;
import com.xuguruogu.auth.util.Converter;

@Component("kssConverter")
public class KssConverter {

	private Map<Class<?>, Converter<?, ?>> map;

	@Autowired
	private Converter<KssAdminDO, AdminDTO> adminDTOConverter;

	@Autowired
	private Converter<KssSoftKeyDO, CDKeyDTD> cdkeyDTOConverter;

	@Autowired
	private Converter<KssKeySetDO, KeySetDTD> keySetDTOConverter;

	@Autowired
	private Converter<KssLogLoginDO, LogLoginDTO> logLoginDTOConverter;

	@Autowired
	private Converter<KssSoftDO, SoftDTO> softDTOConverter;

	@Autowired
	private Converter<KssSoftUserDO, UserDTO> userDTOConverter;

	@Autowired
	private Converter<KssFinanceDO, FinanceDTO> financeDTOConverter;

	@Autowired
	private Converter<KssOrderDO, OrderDTO> orderDTOConverter;

	@Autowired
	private Converter<KssCDKeyStatisticsDO, CDKeyStatisticsDTO> cdkeyStatisticsDTOConverter;

	@Autowired
	private Converter<KssPowerDO, PowerDTD> powerDTOConverter;

	@PostConstruct
	public void init() {
		map = new HashMap<Class<?>, Converter<?, ?>>();
		map.put(KssAdminDO.class, adminDTOConverter);
		map.put(KssSoftKeyDO.class, cdkeyDTOConverter);
		map.put(KssKeySetDO.class, keySetDTOConverter);
		map.put(KssLogLoginDO.class, logLoginDTOConverter);
		map.put(KssSoftDO.class, softDTOConverter);
		map.put(KssSoftUserDO.class, userDTOConverter);
		map.put(KssFinanceDO.class, financeDTOConverter);
		map.put(KssOrderDO.class, orderDTOConverter);
		map.put(KssCDKeyStatisticsDO.class, cdkeyStatisticsDTOConverter);
		map.put(KssPowerDO.class, powerDTOConverter);
	}

	@SuppressWarnings("unchecked")
	public Object convert(Object source) {
		if (source instanceof List) {
			@SuppressWarnings("rawtypes")
			List list = (List) source;
			if (list.size() == 0) {
				return null;
			}
			@SuppressWarnings("rawtypes")
			Converter converter = map.get(list.get(0).getClass());
			if (null == converter) {
				throw new KssException("没有找到converter");
			}
			return converter.converter(list);
		}

		@SuppressWarnings("rawtypes")
		Converter converter = map.get(source.getClass());
		if (null == converter) {
			throw new KssException("没有找到converter");
		}
		return converter.convert(source);
	}

}
