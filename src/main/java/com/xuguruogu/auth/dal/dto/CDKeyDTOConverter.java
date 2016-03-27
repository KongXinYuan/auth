package com.xuguruogu.auth.dal.dto;

import org.springframework.stereotype.Component;

import com.xuguruogu.auth.dal.dataobject.KssSoftKeyDO;
import com.xuguruogu.auth.util.AbstractConverter;

@Component("cdkeyDTOConverter")
public class CDKeyDTOConverter extends AbstractConverter<KssSoftKeyDO, CDKeyDTD> {

	@Override
	protected CDKeyDTD doConvert(KssSoftKeyDO kssSoftKeyDO) {

		CDKeyDTD dto = new CDKeyDTD();

		dto.setStatus(kssSoftKeyDO.getStatus().getDesc());
		dto.setId(kssSoftKeyDO.getId());
		dto.setKeysetid(kssSoftKeyDO.getKeysetid());
		dto.setKeyname(kssSoftKeyDO.getKeyname());
		dto.setAddtime(kssSoftKeyDO.getAddtime());
		dto.setAdminid(kssSoftKeyDO.getAdminid());
		dto.setAdminname(kssSoftKeyDO.getAdminname());
		dto.setCday(kssSoftKeyDO.getCday());
		dto.setCdkey(kssSoftKeyDO.getCdkey());
		dto.setTag(kssSoftKeyDO.getTag());
		dto.setUsedtime(kssSoftKeyDO.getUsedtime());
		dto.setUsername(kssSoftKeyDO.getUsername());
		dto.setOrdernum(kssSoftKeyDO.getOrdernum());

		return dto;
	}
}
