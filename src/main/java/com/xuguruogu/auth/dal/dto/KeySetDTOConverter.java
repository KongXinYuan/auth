package com.xuguruogu.auth.dal.dto;

import org.springframework.stereotype.Component;

import com.xuguruogu.auth.dal.dataobject.KssKeySetDO;
import com.xuguruogu.auth.util.AbstractConverter;

/**
 * 用户摘要信息转换器
 *
 * @author benli.lbl
 * @version $Id: UserSummaryDTOConverter.java, v 0.1 Aug 12, 2015 2:42:50 PM
 *          benli.lbl Exp $
 */
@Component("keySetDTOConverter")
public class KeySetDTOConverter extends AbstractConverter<KssKeySetDO, KeySetDTD> {

	@Override
	protected KeySetDTD doConvert(KssKeySetDO kssKeySetDO) {

		KeySetDTD dto = new KeySetDTD();
		dto.setStatus("未知");

		dto.setStatus(kssKeySetDO.getStatus().getDesc());
		dto.setId(kssKeySetDO.getId());
		dto.setCday(kssKeySetDO.getCday());
		dto.setKeyname(kssKeySetDO.getKeyname());
		dto.setPrefix(kssKeySetDO.getPrefix());
		dto.setSoftid(kssKeySetDO.getSoftid());
		dto.setSoftname(kssKeySetDO.getSoftname());
		dto.setRetailprice(kssKeySetDO.getRetailprice());

		return dto;
	}
}
