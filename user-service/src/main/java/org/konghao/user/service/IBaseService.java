package org.konghao.user.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import cn.mrsong.user.core.Pagination;
import cn.mrsong.user.core.SqlBean;
import cn.mrsong.user.core.response.ResponsePage;

/**
 * Service 基类接口
 * 
 * 2015年1月3日
 */
public interface IBaseService <E, PK extends Serializable> {
 
    /**
     * 不推荐使用全列查询除非必要（如：你当前无法知道有哪些列）
     * 获取当前对象的所有记录的所有属性
     * @return 所有记录
     */
	@Deprecated
    List<E> findAll() throws Exception;
    
    /**
     * 获取当前对象的所有记录(只返回指定的列,列名以“，”号隔开）
     * @param columns 列名
     * @return 所有记录
     */
    List<E> findAll(String columns) throws Exception;
 
    /**
     * 查找所有，并分页
     * 
     * @param page 要返回的页数
     * @param pageSize 没有记录数
     * @return
     */
    ResponsePage<E> pageAll(String columns,Pagination pagination)throws Exception;
 
    /**
     * 保存对象
     * @param e 对象
     */
    void save(E e)throws Exception;;
 
    /**
     * 删除对象
     * @param e 对象
     */
    void delete(E e)throws Exception;;
 
    /**
     * 删除属性名为propertyName，属性值为value的记录
     */
    void deleteByProperty(String propertyName, Object value)throws Exception;
 
    /**
     * 通过属性查找并返回需要的列
     * @param 需要返回的列名以“，”号隔开
     * @param propertyName 属性名称
     * @param value 属性的值
     * @return
     */
    List<E> findByProperty(String columns,String propertyName, Object value)throws Exception;
 
    /**
     * 通过属性查找并返回所有列
     * 不推荐使用全列查询除非必要（如：你当前无法知道有哪些列）
     * @param 需要返回的列名以“，”号隔开
     * @param propertyName 属性名称
     * @param value 属性的值
     * @return
     */
    @Deprecated
    List<E> findByProperty(String propertyName, Object value)throws Exception;
    
    /**
     * 通过多个属性查找并指定的所有列
     * @param columns 需要返回的列名
     * @param propertyNames 属性名称数组
     * @param values 属性值数组
     * @return
     */
    List<E> findByPropertys(String columns,String[] propertyNames, Object[] values)throws Exception;
    
    
    /**
     * 通过多个属性查找并返回所有列
     * 不推荐使用全列查询除非必要（如：你当前无法知道有哪些列）
     * @param propertyNames 属性名称数组
     * @param values 属性值数组
     * @return
     */
    @Deprecated
    List<E> findByPropertys(String[] propertyNames, Object[] values)throws Exception;
    
    /**
     * 通过属性模糊查找并指定的所有列
     * @param columns 需要返回的列名
     * @param propertyName 属性名称
     * @param value 属性的值
     * @return
     */
    List<E> findByPropertyFuzzy(String columns,String propertyName, Object value)throws Exception;
    
    /**
     * 通过属性模糊查找并返回所有列
     * 不推荐使用全列查询除非必要（如：你当前无法知道有哪些列）
     * @param propertyName 属性名称
     * @param value 属性的值
     * @return
     */
    @Deprecated
    List<E> findByPropertyFuzzy(String propertyName, Object value)throws Exception;
 
 
    /**
     * 通过多个属性模糊查找并返回指定的列
     * @param columns 需要返回的列名
     * @param propertyNames 属性名称数组
     * @param values 属性值数组
     * @return
     */
    List<E> findByPropertysFuzzy(String columns,String[] propertyNames, Object[] values)throws Exception;
    
    /**
     * 通过多个属性模糊查找并返回所有列
     * 不推荐使用全列查询除非必要（如：你当前无法知道有哪些列）
     * @param propertyNames 属性名称数组
     * @param values 属性值数组
     * @return
     */
    @Deprecated
    List<E> findByPropertysFuzzy(String[] propertyNames, Object[] values)throws Exception;
    
    
    /**
     * 通过多个属性查找，并分页， 属性名称数组和属性值数组的序列要对应
     * @param columns 需要返回的列名
     * @param propertyNames 属性名称数组
     * @param values 属性值数组
     * @param pagination 分页参数
     * @return 分页后的结果
     */
    ResponsePage<E> pageByPropertys(String columns,String[] propertyNames, Object[] values,Pagination pagination)throws Exception;
 
    /**
     * 通过属性查找，并分页， 属性名称数组和属性值数组的序列要对应
     * @param columns 需要返回的列名
     * @param propertyNames 属性名称
     * @param values 属性值
     * @param pagination 分页参数
     * @return
     */
    ResponsePage<E> pageByProperty(String columns,String propertyName, Object value,Pagination pagination)throws Exception;
    /**
     * 通过多个属性模糊查找，并分页， 属性名称数组和属性值数组的序列要对应
     * @param columns 需要返回的列名
     * @param propertyNames 属性名称数组
     * @param values 属性值数组
     * @param pagination 分页参数
     * @return 分页后的结果
     */
    ResponsePage<E> pageByPropertysFuzzy(String columns,String[] propertyNames, Object[] values,Pagination pagination)throws Exception;
 
    /**
     * 通过属性模糊查找，并分页， 属性名称数组和属性值数组的序列要对应
     * @param columns 需要返回的列名
     * @param propertyNames 属性名称
     * @param values 属性值
     * @param pagination 分页参数
     * @return
     */
    ResponsePage<E> pageByPropertyFuzzy(String columns,String propertyName, Object value,Pagination pagination)throws Exception;
    
    
    List<E> queryBySQL(SqlBean sql)throws Exception;
    
    List<Map<String, Object>> queryMapByHQL(String hql,Object[] args)throws Exception;
    
    ResponsePage<E> pageBySQL(SqlBean sql,Pagination pagination)throws Exception;
    
    ResponsePage<Map<String, Object>> pageMapByHQL(String hql,Object[] args,Pagination pagination)throws Exception;
     
    
    /**
     * 统计所有记录的总数
     * 
     * @return 总数
     */
    int countAll()throws Exception;
 
    /**
     * 统计数据库中当propertyName=value时的记录总数
     * 
     * @param propertyName
     * @param value
     * @return
     */
    int countByProperty(String propertyName, Object value)throws Exception;
 
    /**
     * 统计数据库中当多个propertyName=value时的记录总数
     * 
     * @param propertyNames
     * @param values
     * @return
     */
    int countByPropertys(String[] propertyNames, Object[] values)throws Exception;
 
    
    /**
     * 统计数据库中当propertyName=value 模糊查询时的记录总数
     * 
     * @param propertyName
     * @param value
     * @return
     */
    int countByPropertyFuzzy(String propertyName, Object value)throws Exception;
 
    /**
     * 统计数据库中当多个propertyName=value 模糊查询时的记录总数
     * 
     * @param propertyNames
     * @param values
     * @return
     */
    int countByPropertysFuzzy(String[] propertyNames, Object[] values)throws Exception;
    
    
    /**
     * 根据SQL语句查询结果集条数
     * @return
     */
    int countBySql(SqlBean sql)throws Exception;
    
    /**
     * 保存或更新
     * @param e
     */
    void saveOrUpdate(E e)throws Exception;
    
    
    
 
    /**
     * 通过ID查询对象
     * @param id 主键编号
     * @return
     */
    E findById(PK id)throws Exception;
 
    /**
     * 更新对象
     * @param e
     * @throws Exception 
     */
    void update(E e) throws Exception;
 
    /**
     * 获得持久化对象的类型
     * 
     * @return
     */
    Class<E> getClazz()throws Exception;
 
        
}
