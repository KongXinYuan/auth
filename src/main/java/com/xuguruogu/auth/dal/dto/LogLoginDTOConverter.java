package com.xuguruogu.auth.dal.dto;

import org.springframework.stereotype.Component;

import com.xuguruogu.auth.dal.dataobject.KssLogLoginDO;
import com.xuguruogu.auth.util.AbstractConverter;
import com.xuguruogu.auth.util.IPv4Util;

/**
 * 用户摘要信息转换器
 *
 * @author benli.lbl
 * @version $Id: UserSummaryDTOConverter.java, v 0.1 Aug 12, 2015 2:42:50 PM
 *          benli.lbl Exp $
 */
@Component("logLoginDTOConverter")
public class LogLoginDTOConverter extends AbstractConverter<KssLogLoginDO, LogLoginDTO> {

	@Override
	protected LogLoginDTO doConvert(KssLogLoginDO kssLogLoginDO) {

		LogLoginDTO dto = new LogLoginDTO();

		dto.setId(kssLogLoginDO.getId());
		dto.setUsername(kssLogLoginDO.getUsername());
		dto.setParentname(kssLogLoginDO.getParentname());
		dto.setLoginip(IPv4Util.intToIpWithDefault(kssLogLoginDO.getLoginip()));
		dto.setLogintime(kssLogLoginDO.getLogintime());

		return dto;
	}
}
