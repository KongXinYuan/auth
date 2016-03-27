package com.xuguruogu.auth.service;

import java.math.BigDecimal;
import java.util.List;

import com.xuguruogu.auth.dal.dataobject.KssKeySetDO;

/**
 *
 *
 * @author benli.lbl
 * @version $Id: AdminLoginLogManager.java, v 0.1 Aug 29, 2015 4:00:06 PM
 *          benli.lbl Exp $
 */
public interface KeySetManager {
	public List<KssKeySetDO> listAll();

	public List<KssKeySetDO> listAll(final long softid);

	public KssKeySetDO create(final long softid, final String keyname, final BigDecimal cday, final String prefix,
			final BigDecimal retailprice);

	public KssKeySetDO detail(final long keySetId);

	public void deleteByIds(final List<Long> keySetIds);

	public KssKeySetDO update(final long keySetId, final BigDecimal cday, final BigDecimal retailprice);

	public void lockByIds(final long keySetId, final boolean lock);

}
