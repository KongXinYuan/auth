package com.xuguruogu.auth.config;

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
@Component("softConfigDOConverter")
public class SoftConfigDOConverter extends AbstractConverter<KssSoftDO, SoftConfigDO> {

	@Override
	protected SoftConfigDO doConvert(KssSoftDO kssSoftDO) {

		SoftConfigDO configDo = new SoftConfigDO();
		configDo.setSoftCode(kssSoftDO.getSoftcode());
		configDo.setSoftKey(kssSoftDO.getSoftkey());
		configDo.setIslock(kssSoftDO.getIslock());
		configDo.setIntervaltime(kssSoftDO.getIntervaltime());
		configDo.setServerPrivateKey(kssSoftDO.getServerprivkey());
		configDo.setClientPublicKey(kssSoftDO.getClientpubkey());

		return configDo;
	}
}
