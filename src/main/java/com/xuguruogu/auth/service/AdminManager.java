package com.xuguruogu.auth.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.xuguruogu.auth.dal.dataobject.KssAdminDO;
import com.xuguruogu.auth.dal.dataobject.KssPowerDO;
import com.xuguruogu.auth.dal.enums.RoleType;

/**
 * 管理员用户
 *
 * @author benli.lbl
 * @version $Id: AdminManager.java, v 0.1 Aug 29, 2015 10:12:49 AM benli.lbl Exp
 *          $
 */
public interface AdminManager {

	public List<KssAdminDO> listAll();

	public KssPowerDO empower(long adminid, long keysetid, BigDecimal sellprice);

	public KssPowerDO updatepower(long adminid, long keysetid, BigDecimal sellprice);

	public void removepower(long adminid, long keysetid);

	public List<KssPowerDO> listpower(Long softid, Long adminid);

	public KssAdminDO detail(long adminid);

	public KssAdminDO detail();

	public Map<String, Object> listLogLogin(Integer pageNo, Integer pageSize);

	public void onLoginSucess(String ip);

	public KssAdminDO register(RoleType role, String username, String password, BigDecimal money);

	public void deleteByIds(List<Long> adminids);

	public void updateLock(long adminid, boolean lock);

	public void updatePwd(long adminid, String pwd);

	public void updateMoney(long adminid, BigDecimal money);

	public Map<String, Object> listFinance(Integer pageNo, Integer pageSize);
}
