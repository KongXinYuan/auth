package com.xuguruogu.auth.dal.daointerface;

import java.util.List;

import com.xuguruogu.auth.dal.dataobject.KssCDKeyStatisticsDO;

public interface KssStatisticsDao {

	public List<KssCDKeyStatisticsDO> selectCDKeyStatistics(long softid, Long adminid);

}
