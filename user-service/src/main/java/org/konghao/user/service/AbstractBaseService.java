package org.konghao.user.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.konghao.user.base.dao.IBaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.mrsong.user.core.Pagination;
import cn.mrsong.user.core.SqlBean;
import cn.mrsong.user.core.response.ResponsePage;


/**
 * 基础的Service抽象类，默认实现基础操作
 * 
 * @date:2016年5月30日
 * @param <E>
 * @param <PK>
 */
 
@Service
@Transactional
public  abstract class AbstractBaseService<E, PK extends Serializable>{
 
	@Autowired
	protected IBaseDao<E, PK> baseDao;
 
	public AbstractBaseService() {
	}
 
	
	public List<E> findAll() throws Exception {
		return baseDao.findAll("", null);
	}
 
	
	public List<E> findAll(String columns) throws Exception {
		return baseDao.findAll(columns, null);
	}
 
	
	public ResponsePage<E> pageAll(String columns, Pagination pagination) throws Exception {
		ResponsePage<E> responsePage = new ResponsePage<E>();
		responsePage.setRows(baseDao.findAll(columns, pagination));
		pagination.setDataSize(baseDao.countAll());
		responsePage.setPagination(pagination);
		return responsePage;
	}
 
	
	public void save(E e) throws Exception {
		baseDao.save(e);
	}
 
	
	public void delete(E e) throws Exception {
		baseDao.delete(e);
	}
 
	
	public void deleteByProperty(String propertyName, Object value) throws Exception {
		baseDao.deleteByProperty(propertyName, value);
	}
 
	
	public List<E> findByProperty(String columns, String propertyName, Object value) throws Exception {
		return baseDao.findByProperty(columns, propertyName, value, null);
	}
 
	
	public List<E> findByProperty(String propertyName, Object value) throws Exception {
		return baseDao.findByProperty("", propertyName, value, null);
	}
 
	
	public List<E> findByPropertys(String columns, String[] propertyNames, Object[] values) throws Exception {
		return baseDao.findByPropertys(columns, propertyNames, values, null);
	}
 
	
	public List<E> findByPropertys(String[] propertyNames, Object[] values) throws Exception {
		return baseDao.findByPropertys("", propertyNames, values, null);
	}
 
	
	public List<E> findByPropertyFuzzy(String columns, String propertyName, Object value) throws Exception {
		return baseDao.findByPropertyFuzzy(columns, propertyName, value, null);
	}
 
	
	public List<E> findByPropertyFuzzy(String propertyName, Object value) throws Exception {
		return baseDao.findByPropertyFuzzy("", propertyName, value, null);
	}
 
	
	public List<E> findByPropertysFuzzy(String columns, String[] propertyNames, Object[] values) throws Exception {
		return baseDao.findByPropertysFuzzy(columns, propertyNames, values, null);
	}
 
	
	public List<E> findByPropertysFuzzy(String[] propertyNames, Object[] values) throws Exception {
		return baseDao.findByPropertysFuzzy("", propertyNames, values, null);
	}
 
	
	public ResponsePage<E> pageByPropertys(String columns, String[] propertyNames, Object[] values, Pagination pagination) throws Exception {
		ResponsePage<E> responsePage = new ResponsePage<E>();
		pagination.setDataSize(baseDao.countByPropertys(propertyNames, values));
		responsePage.setRows(baseDao.findByPropertys(columns, propertyNames, values, pagination));
		responsePage.setPagination(pagination);
		return responsePage;
	}
 
	
	public ResponsePage<E> pageByProperty(String columns, String propertyName, Object value, Pagination pagination) throws Exception {
		ResponsePage<E> responsePage = new ResponsePage<E>();
		String[] propertyNames = { propertyName };
		Object[] values = { value };
		pagination.setDataSize(baseDao.countByPropertys(propertyNames, values));
		responsePage.setRows(baseDao.findByProperty(columns, propertyName, value, pagination));
		responsePage.setPagination(pagination);
		return responsePage;
	}
 
	
	public ResponsePage<E> pageByPropertysFuzzy(String columns, String[] propertyNames, Object[] values, Pagination pagination)
			throws Exception {
		ResponsePage<E> responsePage = new ResponsePage<E>();
		pagination.setDataSize(baseDao.countByPropertysFuzzy(propertyNames, values));
		responsePage.setRows(baseDao.findByPropertysFuzzy(columns, propertyNames, values, pagination));
		responsePage.setPagination(pagination);
		return responsePage;
	}
 
	
	public ResponsePage<E> pageByPropertyFuzzy(String columns, String propertyName, Object value, Pagination pagination) throws Exception {
		String[] propertyNames = { propertyName };
		Object[] values = { value };
		return pageByPropertysFuzzy(columns, propertyNames, values, pagination);
	}
 
	
	public List<E> queryBySQL(SqlBean sql) throws Exception {
		return baseDao.queryBySQL(sql);
	}
 
	
	public List<Map<String, Object>> queryMapByHQL(String hql, Object[] args) throws Exception {
		return baseDao.queryMapByHQL(hql, args, null);
	}
 
	
	public ResponsePage<E> pageBySQL(SqlBean sql, Pagination pagination) throws Exception {
		ResponsePage<E> responsePage = new ResponsePage<E>();
		pagination.setDataSize(baseDao.queryCountBySQL(sql));
		responsePage.setRows(baseDao.queryBySQL(sql, pagination));
		responsePage.setPagination(pagination);
		return responsePage;
	}
	
	public ResponsePage<Map<String, Object>> pageMapByHQL(String hql, Object[] args, Pagination pagination) throws Exception {
		ResponsePage<Map<String, Object>> responsePage = new ResponsePage<Map<String, Object>>();
		pagination.setDataSize(baseDao.mapCountByHQL(hql, args));
		responsePage.setRows(baseDao.queryMapByHQL(hql, args, pagination));
		responsePage.setPagination(pagination);
		return responsePage;
	}
 
	
	public int countAll() throws Exception {
		return baseDao.countAll();
	}
 
	
	public int countByProperty(String propertyName, Object value) throws Exception {
		String[] propertyNames = { propertyName };
		Object[] values = { value };
		return baseDao.countByPropertys(propertyNames, values);
	}
 
	
	public int countByPropertys(String[] propertyNames, Object[] values) throws Exception {
		return baseDao.countByPropertys(propertyNames, values);
	}
 
	
	public int countByPropertyFuzzy(String propertyName, Object value) throws Exception {
		String[] propertyNames = { propertyName };
		Object[] values = { value };
		return baseDao.countByPropertysFuzzy(propertyNames, values);
	}
 
	
	public int countByPropertysFuzzy(String[] propertyNames, Object[] values) throws Exception {
		return baseDao.countByPropertysFuzzy(propertyNames, values);
	}
 
	
	public int countBySql(SqlBean sql) throws Exception {
		return baseDao.queryCountBySQL(sql);
	}
 
	
	public void saveOrUpdate(E e) throws Exception {
		baseDao.saveOrUpdate(e);
	}
 
	
	public E findById(PK id) throws Exception {
		return baseDao.findById(id);
	}
 
	
	public void update(E e) throws Exception {
		baseDao.update(e);
	}
 
	
	public Class<E> getClazz() throws Exception {
		return baseDao.getClazz();
	}
}