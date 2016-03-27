package com.xuguruogu.auth.dal.dto;

import org.springframework.stereotype.Component;

import com.xuguruogu.auth.dal.dataobject.KssAdminDO;
import com.xuguruogu.auth.util.AbstractConverter;
import com.xuguruogu.auth.util.IPv4Util;

/**
 * 用户摘要信息转换器
 *
 * @author benli.lbl
 * @version $Id: UserSummaryDTOConverter.java, v 0.1 Aug 12, 2015 2:42:50 PM
 *          benli.lbl Exp $
 */
@Component("adminDTOConverter")
public class AdminDTOConverter extends AbstractConverter<KssAdminDO, AdminDTO> {

	@Override
	protected AdminDTO doConvert(KssAdminDO kssAdminDO) {

		AdminDTO dto = new AdminDTO();

		dto.setStatus(kssAdminDO.getStatus().getDesc());
		dto.setId(kssAdminDO.getId());
		dto.setParentname(kssAdminDO.getParentname());
		dto.setRole(kssAdminDO.getRole().getDesc());
		dto.setUsername(kssAdminDO.getUsername());
		dto.setAddtime(kssAdminDO.getAddtime());
		dto.setLastlogintime(kssAdminDO.getLastlogintime());
		dto.setLastloginip(IPv4Util.intToIpWithDefault(kssAdminDO.getLastloginip()));
		dto.setMoney(kssAdminDO.getMoney());
		dto.setExmoney(kssAdminDO.getExmoney());

		return dto;
	}
}
