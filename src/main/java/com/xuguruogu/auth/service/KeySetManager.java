package com.xuguruogu.auth.service;

import java.math.BigDecimal;
import java.util.List;

import com.xuguruogu.auth.dto.KeySetDTD;

/**
 *
 *
 * @author benli.lbl
 * @version $Id: AdminLoginLogManager.java, v 0.1 Aug 29, 2015 4:00:06 PM
 *          benli.lbl Exp $
 */
public interface KeySetManager {

	public List<KeySetDTD> listid(long softid);

	public KeySetDTD create(long softid, String keyname, BigDecimal cday, String prefix, BigDecimal retailprice);

	public KeySetDTD detail(long keySetId);

	public long countBySoftId(long softid);

	public void deleteById(long keySetId);

	public void deleteByIds(List<Long> keySetIds);

	public KeySetDTD update(long keySetId, BigDecimal cday, BigDecimal retailprice);

	public void updateLock(long keySetId, boolean lock);

}
