package com.xuguruogu.auth.dto;

import org.springframework.stereotype.Component;

import com.xuguruogu.auth.dal.dataobject.KssSoftDO;
import com.xuguruogu.auth.util.AbstractConverter;

/**
 * 用户摘要信息转换器
 *
 * @author benli.lbl
 * @version $Id: UserSummaryDTOConverter.java, v 0.1 Aug 12, 2015 2:42:50 PM
 *          benli.lbl Exp $
 */
@Component("softDTOConverter")
public class SoftDTOConverter extends AbstractConverter<KssSoftDO, SoftDTO> {

	@Override
	protected SoftDTO doConvert(KssSoftDO kssSoftDO) {

		SoftDTO dto = new SoftDTO();

		dto.setId(kssSoftDO.getId());
		dto.setSoftcode(kssSoftDO.getSoftcode());
		dto.setSoftkey(kssSoftDO.getSoftkey());
		dto.setSoftname(kssSoftDO.getSoftname());
		dto.setLock(kssSoftDO.isLock());
		dto.setIntervaltime(kssSoftDO.getIntervaltime());
		dto.setClientpubkey(kssSoftDO.getClientpubkey());
		dto.setServerprivkey(kssSoftDO.getServerprivkey());

		return dto;
	}
}
