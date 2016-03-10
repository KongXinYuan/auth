package com.xuguruogu.auth.dal.daointerface;

import java.util.List;

import com.xuguruogu.auth.dal.dataobject.Entity;
import com.xuguruogu.auth.dal.querycondition.QueryCondition;

public interface KssDaoBase<T extends Entity, C extends QueryCondition<?>> {

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
	public T selectById(long id);

	/**
	 * 删除
	 *
	 * @param id
	 * @return
	 */
	public long deleteById(long id);

	/**
	 * 删除
	 *
	 * @param id
	 * @return
	 */
	public long deleteByIds(List<Long> ids);

	/**
	 * 查找计数
	 *
	 * @param queryCondition
	 * @return
	 */
	public long selectCountByQueryCondition(C queryCondition);

	/**
	 * 查找
	 *
	 * @param queryCondition
	 * @return
	 */
	public List<T> selectListByQueryCondition(C queryCondition);

	/**
	 * 查找
	 *
	 * @param queryCondition
	 * @return
	 */
	public T selectOneByQueryCondition(C queryCondition);
}
