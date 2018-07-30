package org.konghao.user.base.dao;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import cn.mrsong.user.core.Pagination;
import cn.mrsong.user.core.SqlBean;

public interface IBaseDao<E, PK extends Serializable> {
 
 
	
	/**
	 * 获得持久化对象的类型
	 * 
	 * @return
	 */
	Class<E> getClazz();
 
	
	/**
	 * 保存对象
	 */
	void save(E e);
 
	/**
	 * 删除对象
	 */
	void delete(E e);
	
	/**
	 * 更新对象
	 * 
	 * @param e
	 */
	void update(E e);
	
	/**
	 * 保存或更新
	 * 
	 * @param e
	 */
	void saveOrUpdate(E e);
	
 
	/**
	 * 删除properyName == value 的记录
	 */
	void deleteByProperty(String propertyName, Object value);
 
	
	/**
	 * 通过ID查询对象
	 * @param id 主键
	 * @return
	 */
	E findById(PK id);
	
 
	/**
	 * 获取当前对象的所有记录
	 * @ columns 需要返回的列名
	 * @ pagination 分页信息
	 * @return 所有记录
	 */
	List<E> findAll(String columns,Pagination pagination);
	
	
	/**
	 * 通过多个属性查找
	 * 
	 * @param propertyNames
	 *            属性名称数组
	 * @param values
	 *            属性值数组
	 * @return
	 */
	List<E> findByPropertys(String columns,String[] propertyNames, Object[] values,Pagination pagination);
	
	
	/**
	 * 通过多个属性查找
	 * 
	 * @param propertyNames
	 *            属性名称数组
	 * @param values
	 *            属性值数组
	 * @return
	 */
	List<E> findByProperty(String columns,String propertyName, Object value,Pagination pagination);
 
 
	/**
	 * 通过多个属性模糊查找
	 * 
	 * @param propertyNames
	 *            属性名称数组
	 * @param values
	 *            属性值数组
	 * @return
	 */
	List<E> findByPropertysFuzzy(String columns,String[] propertyNames, Object[] values,Pagination pagination);
	
	/**
	 * 通过某个属性模糊查找
	 * 
	 * @param propertyName
	 *            属性名称
	 * @param value
	 *            属性值
	 * @return
	 */
	List<E> findByPropertyFuzzy(String column,String propertyName, Object value,Pagination pagination);
 
 
	/**
	 * 统计所有记录的总数
	 * 
	 * @return 总数
	 */
	 int countAll();
 
 
	/**
	 * 统计数据库中当1个或多个propertyName=value时的记录总数
	 * 
	 * @param propertyNames
	 * @param values
	 * @return
	 */
	int countByPropertys(String[] propertyNames, Object[] values);
 
	
	int countByPropertysFuzzy(String[] propertyNames, Object[] values);
	
	/**
	 * 根据SQL查询记录数
	 */
	int  queryCountBySQL(SqlBean sql);
	
	int  mapCountByHQL(String hql,Object[] args);
	/**
	 * 查找并通过某一属性排序
	 * 
	 * @param property
	 *            排序依据的顺序
	 * @param isSequence
	 *            是否顺序排序
	 */
 
	/**
	 * 根据SQL语法查询
	 * 
	 * @param sql
	 * @return
	 */
	List<E> queryBySQL(SqlBean sql);
 
	
	/**
	 * 根据SQL语法分页查询
	 * 
	 * @param sql
	 * @return
	 */
	List<E> queryBySQL(SqlBean sql,Pagination pagination);
	/**
	 * 根据SQL语法查询
	 * @param sql
	 * @return 返回自定义的Map类型
	 */
	List<Map<String,Object>> queryMapByHQL(String hql,Object[] args,Pagination pagination);
 
}