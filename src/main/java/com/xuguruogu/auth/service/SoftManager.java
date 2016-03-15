package com.xuguruogu.auth.service;

import java.util.List;
import java.util.Map;

import com.xuguruogu.auth.dal.dataobject.KssSoftDO;
import com.xuguruogu.auth.dto.SoftDTO;

/**
 * 软件管理
 *
 * @author benli.lbl
 * @version $Id: AdminLoginLogManager.java, v 0.1 Aug 29, 2015 4:00:06 PM
 *          benli.lbl Exp $
 */
public interface SoftManager {
	public Map<String, Object> list(Integer pageNo, Integer pageSize);

	public List<SoftDTO> listAll();

	public SoftDTO create(String softname, long intervaltime, String clientpubkey, String serverprivkey);

	public SoftDTO detail(long softid);

	public SoftDTO update(long softid, long intervaltime, String clientpubkey, String serverprivkey);

	public KssSoftDO selectBySoftcode(long softcode);

	public void updateLock(long softid, boolean lock);

	public void deleteByIds(List<Long> softids);

}
