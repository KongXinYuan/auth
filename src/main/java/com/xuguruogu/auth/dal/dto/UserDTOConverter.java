package com.xuguruogu.auth.dal.dto;

import org.springframework.stereotype.Component;

import com.xuguruogu.auth.dal.dataobject.KssSoftUserDO;
import com.xuguruogu.auth.util.AbstractConverter;

/**
 * 用户摘要信息转换器
 *
 * @author benli.lbl
 * @version $Id: UserSummaryDTOConverter.java, v 0.1 Aug 12, 2015 2:42:50 PM
 *          benli.lbl Exp $
 */
@Component("userDTOConverter")
public class UserDTOConverter extends AbstractConverter<KssSoftUserDO, UserDTO> {

	@Override
	protected UserDTO doConvert(KssSoftUserDO kssSoftUserDO) {
		UserDTO dto = new UserDTO();
		dto.setStatus(kssSoftUserDO.getStatus().getDesc());
		dto.setAddtime(kssSoftUserDO.getAddtime());
		dto.setAdminname(kssSoftUserDO.getAdminname());
		dto.setCday(kssSoftUserDO.getCday());
		dto.setEndtime(kssSoftUserDO.getEndtime());
		dto.setLastloginip(kssSoftUserDO.getLastloginip());
		dto.setLastlogintime(kssSoftUserDO.getLastlogintime());
		dto.setRechargetimes(kssSoftUserDO.getRechargetimes());
		dto.setUsername(kssSoftUserDO.getUsername());

		return dto;
	}
}
