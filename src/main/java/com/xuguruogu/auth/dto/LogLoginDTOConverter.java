package com.xuguruogu.auth.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xuguruogu.auth.dal.dataobject.KssLogLoginDO;
import com.xuguruogu.auth.service.AdminManager;
import com.xuguruogu.auth.util.AbstractConverter;

/**
 * 用户摘要信息转换器
 *
 * @author benli.lbl
 * @version $Id: UserSummaryDTOConverter.java, v 0.1 Aug 12, 2015 2:42:50 PM
 *          benli.lbl Exp $
 */
@Component("logLoginDTOConverter")
public class LogLoginDTOConverter extends AbstractConverter<KssLogLoginDO, LogLoginDTO> {

	@Autowired
	private AdminManager adminManager;

	@Override
	protected LogLoginDTO doConvert(KssLogLoginDO kssLogLoginDO) {

		LogLoginDTO dto = new LogLoginDTO();

		dto.setId(kssLogLoginDO.getId());
		dto.setAdminid(kssLogLoginDO.getAdminid());
		dto.setLoginip(kssLogLoginDO.getLoginip());
		dto.setLogintime(kssLogLoginDO.getLogintime());

		return dto;
	}
}
