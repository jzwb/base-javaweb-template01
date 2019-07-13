package com.jzwb.service;

import com.jzwb.common.Filter;
import com.jzwb.common.Order;
import com.jzwb.common.Page;
import com.jzwb.common.Pageable;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Service - 基类
 */
public interface BaseService<T, ID extends Serializable> {

	/**
	 * 查找实体对象
	 *
	 * @param id ID
	 * @return
	 */
	T find(ID id);

	/**
	 * 查找所有实体对象集合
	 *
	 * @return
	 */
	List<T> findAll();

	/**
	 * 查找实体对象集合
	 *
	 * @param ids ID
	 * @return
	 */
	List<T> findList(@SuppressWarnings("unchecked") ID... ids);

	/**
	 * 查找实体对象集合
	 *
	 * @param count   数量
	 * @param filters 筛选
	 * @param orders  排序
	 * @return
	 */
	List<T> findList(Integer count, List<Filter> filters, List<Order> orders);

	/**
	 * 查找实体对象集合
	 *
	 * @param first   起始记录
	 * @param count   数量
	 * @param filters 筛选
	 * @param orders  排序
	 * @return
	 */
	List<T> findList(Integer first, Integer count, List<Filter> filters, List<Order> orders);

	/**
	 * 获取实体类执行的字段属性集合
	 *
	 * @param first      起始记录
	 * @param count      数量
	 * @param filters    筛选
	 * @param orders     排序
	 * @param properties 目标字段属性
	 * @return
	 */
	List<Object[]> findList(Integer first, Integer count, List<Filter> filters, List<Order> orders, String[] properties);

	/**
	 * 获取实体类指定的字段属性集合
	 *
	 * @param first      起始记录
	 * @param count      数量
	 * @param filters    筛选
	 * @param orders     排序
	 * @param properties 目标字段属性
	 * @return
	 */
	List<Map<String, Object>> findListMap(Integer first, Integer count, List<Filter> filters, List<Order> orders, String... properties);

	/**
	 * 查找实体对象分页
	 *
	 * @param pageable 分页信息
	 * @return
	 */
	Page<T> findPage(Pageable pageable);

	/**
	 * 获取实体类指定的指定的字段属性集合（分页）
	 *
	 * @param pageable   分页信息
	 * @param properties 目标字段属性
	 * @return
	 */
	Page<Object[]> findPage(Pageable pageable, String[] properties);

	/**
	 * 获取实体类指定的指定的字段属性集合（分页）
	 *
	 * @param pageable   分页信息
	 * @param properties 目标字段属性
	 * @return
	 */
	Page<Map<String, Object>> findPageMap(Pageable pageable, String... properties);

	/**
	 * 查询实体对象总数
	 *
	 * @return
	 */
	long count();

	/**
	 * 查询实体对象数量
	 *
	 * @param filters 筛选
	 * @return
	 */
	long count(Filter... filters);

	/**
	 * 判断实体对象是否存在
	 *
	 * @param id ID
	 * @return
	 */
	boolean exists(ID id);

	/**
	 * 判断实体对象是否存在
	 *
	 * @param filters 筛选
	 * @return
	 */
	boolean exists(Filter... filters);

	/**
	 * 保存实体对象
	 *
	 * @param entity 实体对象
	 */
	void save(T entity);

	/**
	 * 更新实体对象
	 *
	 * @param entity 实体对象
	 * @return
	 */
	T update(T entity);

	/**
	 * 更新实体对象
	 *
	 * @param entity           实体对象
	 * @param ignoreProperties 忽略属性
	 * @return
	 */
	T update(T entity, String... ignoreProperties);

	/**
	 * 删除实体对象
	 *
	 * @param id ID
	 */
	void delete(ID id);

	/**
	 * 删除实体对象
	 *
	 * @param ids ID
	 */
	void delete(@SuppressWarnings("unchecked") ID... ids);

	/**
	 * 删除实体对象
	 *
	 * @param entity 实体对象
	 */
	void delete(T entity);

}