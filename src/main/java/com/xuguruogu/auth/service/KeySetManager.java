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

	public KssKeySetDO create(long softid, String keyname, BigDecimal cday, String prefix, BigDecimal retailprice);

	public KssKeySetDO queryById(long keySetId);

	public List<KssKeySetDO> selectBySoftId(long softid);

	public long countBySoftId(long softid);

	public void deleteById(long keySetId);

	public void deleteByIds(List<Long> keySetIds);

	public void update(long keySetId, String keyname, BigDecimal retailprice);

	public void updateLock(long keySetId, boolean lock);

}
