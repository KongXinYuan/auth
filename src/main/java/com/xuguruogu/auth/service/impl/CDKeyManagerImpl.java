package com.xuguruogu.auth.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xuguruogu.auth.dal.daointerface.KssKeySetDao;
import com.xuguruogu.auth.dal.daointerface.KssOrderDao;
import com.xuguruogu.auth.dal.daointerface.KssSoftKeyDao;
import com.xuguruogu.auth.dal.dataobject.KssKeySetDO;
import com.xuguruogu.auth.dal.dataobject.KssOrderDO;
import com.xuguruogu.auth.dal.dataobject.KssSoftKeyDO;
import com.xuguruogu.auth.dal.querycondition.KssSoftKeyQueryCondition;
import com.xuguruogu.auth.dto.CDKeyDTD;
import com.xuguruogu.auth.interceptor.KssException;
import com.xuguruogu.auth.service.CDKeyManager;
import com.xuguruogu.auth.util.Converter;
import com.xuguruogu.auth.util.RandomUtil;
import com.xuguruogu.auth.web.param.CDKeySearchParam;

@Component("cdkeyManager")
public class CDKeyManagerImpl implements CDKeyManager {

	@Autowired
	private KssSoftKeyDao kssSoftKeyDao;

	@Autowired
	private KssKeySetDao kssKeySetDao;

	@Autowired
	private KssOrderDao kssOrderDao;

	@Autowired
	private Converter<KssSoftKeyDO, CDKeyDTD> cdkeyDTOConverter;

	@Override
	public List<CDKeyDTD> create(long softid, long adminid, long keysetid, String tag, long num) {
		// 校验
		KssKeySetDO keyset = kssKeySetDao.selectById(keysetid);
		if (null == keyset || softid != keyset.getSoftid() || keyset.isLock()) {
			throw new KssException("卡类不存在或被锁定");
		}
		BigDecimal cday = keyset.getCday();
		String prefix = keyset.getPrefix();

		// 创建订单 生成订单号
		String ordernum = UUID.randomUUID().toString().replace("-", "");
		KssOrderDO order = new KssOrderDO();
		order.setAddtime(new Date());
		order.setAdminid(adminid);
		order.setKeycount(num);
		order.setKeysetid(keysetid);
		order.setOrdernum(ordernum);
		order.setSoftid(softid);
		order.setDone(false);
		kssOrderDao.insert(order);

		// 生成cdkey
		for (long now = 0, trytimes = 0; now < num || trytimes > 2;) {
			long insertnum = num - now;
			List<KssSoftKeyDO> cdkeysinsert = new ArrayList<KssSoftKeyDO>();
			for (long i = 0; i < insertnum; i++) {
				KssSoftKeyDO cdkey = new KssSoftKeyDO();
				cdkey.setAddtime(new Date());
				cdkey.setAdminid(adminid);
				cdkey.setCdkey(prefix + RandomUtil.getRandomCharAndNumr(28));
				cdkey.setKeysetid(keysetid);
				cdkey.setTag(tag);
				cdkey.setOrdernum(ordernum);
				cdkey.setCday(cday);
				cdkeysinsert.add(cdkey);
			}
			trytimes++;
			now += kssSoftKeyDao.insertList(softid, cdkeysinsert);
		}

		// 查询
		List<KssSoftKeyDO> cdkeys = kssSoftKeyDao.selectListByQueryCondition(softid,
				new KssSoftKeyQueryCondition().putOrdernum(ordernum));
		if (null == cdkeys || num != cdkeys.size()) {
			throw new KssException("读取为空或数量不符");
		}

		// 完成订单
		kssOrderDao.update(ordernum, true, cdkeys.get(0).getId());

		return cdkeyDTOConverter.converter(cdkeys);
	}

	@Override
	public List<CDKeyDTD> search(long softid, long adminid, CDKeySearchParam param) {
		KssSoftKeyQueryCondition query = new KssSoftKeyQueryCondition().putAdminid(param.getAdminid())
				.putCdkey(param.getCdkey()).putKeysetid(param.getKeysetid()).putLock(param.getLock())
				.putOrdernum(param.getOrdernum()).putTag(param.getTag()).putUsed(param.getUsed())
				.putUseduser(param.getUseduser()).pagination(param.getPageNo(), param.getPageSize());

		return cdkeyDTOConverter.converter(kssSoftKeyDao.selectListByQueryCondition(softid, query));
	}

	@Override
	public KssSoftKeyDO queryByCdkey(long softid, String cdkey) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateLock(long softid, long keyid, boolean lock) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(long softid, long keyid) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteByIds(long softid, List<Long> keyids) {
		// TODO Auto-generated method stub

	}

}
