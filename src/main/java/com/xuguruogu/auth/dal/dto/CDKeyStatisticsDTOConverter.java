package com.xuguruogu.auth.dal.dto;

import org.springframework.stereotype.Component;

import com.xuguruogu.auth.dal.dataobject.KssCDKeyStatisticsDO;
import com.xuguruogu.auth.util.AbstractConverter;

@Component("cdkeyStatisticsDTOConverter")
public class CDKeyStatisticsDTOConverter extends AbstractConverter<KssCDKeyStatisticsDO, CDKeyStatisticsDTO> {

	@Override
	protected CDKeyStatisticsDTO doConvert(KssCDKeyStatisticsDO kssCDKeyStatisticsDO) {

		CDKeyStatisticsDTO dto = new CDKeyStatisticsDTO();

		dto.setAdminid(kssCDKeyStatisticsDO.getAdminid());
		dto.setAdminname(kssCDKeyStatisticsDO.getAdminname());
		dto.setKeyname(kssCDKeyStatisticsDO.getKeyname());
		dto.setKeysetid(kssCDKeyStatisticsDO.getKeysetid());
		dto.setParentid(kssCDKeyStatisticsDO.getParentid());
		dto.setParentname(kssCDKeyStatisticsDO.getParentname());
		dto.setTotal(kssCDKeyStatisticsDO.getTotal());
		dto.setLocked(kssCDKeyStatisticsDO.getLocked());
		dto.setUsed(kssCDKeyStatisticsDO.getUsed());
		dto.setDeleted(kssCDKeyStatisticsDO.getDeleted());
		return dto;
	}
}
