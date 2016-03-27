package com.xuguruogu.auth.dal.dto;

import org.springframework.stereotype.Component;

import com.xuguruogu.auth.dal.dataobject.KssFinanceDO;
import com.xuguruogu.auth.util.AbstractConverter;

/**
 * 用户摘要信息转换器
 *
 * @author benli.lbl
 * @version $Id: UserSummaryDTOConverter.java, v 0.1 Aug 12, 2015 2:42:50 PM
 *          benli.lbl Exp $
 */
@Component("financeDTOConverter")
public class FinanceDTOConverter extends AbstractConverter<KssFinanceDO, FinanceDTO> {

	@Override
	protected FinanceDTO doConvert(KssFinanceDO finance) {

		FinanceDTO dto = new FinanceDTO();

		dto.setAddtime(finance.getAddtime());
		dto.setAdminid(finance.getAdminid());
		dto.setDirection(finance.isIn() ? "入账" : "出账");
		dto.setMoneybefore(finance.getMoneybefore());
		dto.setMoneynow(finance.getMoneynow());
		dto.setMoneychange(finance.getMoneychange());
		dto.setRelatedid(finance.getRelatedid());
		dto.setRelatedname(finance.getRelatedname());
		dto.setUsername(finance.getUsername());
		dto.setDisc(finance.getDisc());
		return dto;
	}
}
