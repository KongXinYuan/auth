package com.xuguruogu.auth.dto;

import org.springframework.stereotype.Component;

import com.xuguruogu.auth.dal.dataobject.KssSoftKeyDO;
import com.xuguruogu.auth.util.AbstractConverter;

@Component("cdkeyDTOConverter")
public class CDKeyDTOConverter extends AbstractConverter<KssSoftKeyDO, CDKeyDTD> {

	@Override
	protected CDKeyDTD doConvert(KssSoftKeyDO kssSoftKeyDO) {

		CDKeyDTD dto = new CDKeyDTD();
		dto.setId(kssSoftKeyDO.getId());
		dto.setKeysetid(kssSoftKeyDO.getKeysetid());
		dto.setAddtime(kssSoftKeyDO.getAddtime());
		dto.setAdminid(kssSoftKeyDO.getAdminid());
		dto.setCday(kssSoftKeyDO.getCday());
		dto.setCdkey(kssSoftKeyDO.getCdkey());
		dto.setLock(kssSoftKeyDO.isLock());
		dto.setTag(kssSoftKeyDO.getTag());
		dto.setUsedtime(kssSoftKeyDO.getUsedtime());
		dto.setUseduser(kssSoftKeyDO.getUseduser());

		return dto;
	}
}
