package com.xuguruogu.auth.dal.dto;

import org.springframework.stereotype.Component;

import com.xuguruogu.auth.dal.dataobject.KssPowerDO;
import com.xuguruogu.auth.util.AbstractConverter;

@Component("powerDTOConverter")
public class PowerDTOConverter extends AbstractConverter<KssPowerDO, PowerDTD> {

	@Override
	protected PowerDTD doConvert(KssPowerDO kssPowerDO) {

		PowerDTD dto = new PowerDTD();
		dto.setId(kssPowerDO.getId());
		dto.setAdminid(kssPowerDO.getAdminid());
		dto.setKeyname(kssPowerDO.getKeyname());
		dto.setKeysetid(kssPowerDO.getKeysetid());
		dto.setRetailprice(kssPowerDO.getRetailprice());
		dto.setSellprice(kssPowerDO.getSellprice());
		dto.setSoftid(kssPowerDO.getSoftid());
		dto.setSoftname(kssPowerDO.getSoftname());
		return dto;
	}
}
