package com.xuguruogu.auth.dal.dto;

import org.springframework.stereotype.Component;

import com.xuguruogu.auth.dal.dataobject.KssOrderDO;
import com.xuguruogu.auth.util.AbstractConverter;

@Component("orderDTOConverter")
public class OrderDTOConverter extends AbstractConverter<KssOrderDO, OrderDTO> {

	@Override
	protected OrderDTO doConvert(KssOrderDO kssOrderDO) {

		OrderDTO dto = new OrderDTO();
		dto.setId(kssOrderDO.getId());
		dto.setAddtime(kssOrderDO.getAddtime());
		dto.setAdminname(kssOrderDO.getAdminname());
		dto.setBeginid(kssOrderDO.getBeginid());
		dto.setKeycount(kssOrderDO.getKeycount());
		dto.setKeyname(kssOrderDO.getKeyname());
		dto.setOrdernum(kssOrderDO.getOrdernum());
		dto.setParentname(kssOrderDO.getParentname());
		dto.setSoftname(kssOrderDO.getSoftname());

		return dto;
	}
}
