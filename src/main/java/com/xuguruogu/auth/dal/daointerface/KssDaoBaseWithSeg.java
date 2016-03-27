package com.xuguruogu.auth.dal.daointerface;

import java.util.List;

import com.xuguruogu.auth.dal.dataobject.EntityWithSeg;
import com.xuguruogu.auth.dal.querycondition.QueryCondition;

public interface KssDaoBaseWithSeg<T extends EntityWithSeg, C extends QueryCondition<?>> {

	/**
	 * 插入
	 *
	 * @param T
	 *            entity
	 * @return
	 */
	public long insert(T entity);

	/**
	 * selectOne
	 *
	 * @param id
	 * @return
	 */
	public T selectById(long softid, long id);

	public List<T> selectByIds(long softid, List<Long> ids);

	/**
	 * 删除
	 *
	 * @param id
	 * @return
	 */
	public long deleteById(long softid, long id);

	/**
	 * 删除
	 *
	 * @param id
	 * @return
	 */
	public long deleteByIds(long softid, List<Long> ids);

	/**
	 * 查找计数
	 *
	 * @param queryCondition
	 * @return
	 */
	public long selectCountByQueryCondition(long softid, C queryCondition);

	/**
	 * 查找
	 *
	 * @param queryCondition
	 * @return
	 */
	public List<T> selectListByQueryCondition(long softid, C queryCondition);

	/**
	 * 查找
	 *
	 * @param queryCondition
	 * @return
	 */
	public T selectOneByQueryCondition(long softid, C queryCondition);
}
