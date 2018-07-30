package org.konghao.user.base.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import java.lang.reflect.ParameterizedType;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cn.mrsong.user.core.Pagination;
import cn.mrsong.user.core.SqlBean;

/**
 * 泛型DAO实现
 * 
 * @date:2016年6月7日
 * @param <E>
 * @param <PK>
 */
@SuppressWarnings("unchecked")
public class BaseDaoImpl<E, PK extends Serializable>  implements IBaseDao<E, PK> {
 
	@Autowired
	private SessionFactory sessionFactory;
	
	private Class<E> clazz;
	
 
	public BaseDaoImpl() {
		clazz = (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
 
	public Class<E> getClazz() {
		return clazz;
	} 
	private String from() {
		return "from " + getClazz().getName();
	}
 
	private String count() {
		return "select count(1) " + from();
	}
 
	private String where(String[] propertyNames, Object[] values) {
		StringBuilder accum = new StringBuilder(" where 1 = 1");
		for (int i = 0, len = propertyNames.length; i < len; i++) {
			accum.append(" and ");
			if (values[i] instanceof String) {
				accum.append(propertyNames[i]).append("='").append(values[i]).append("'");
			} else {
				accum.append(propertyNames[i]).append("=").append(values[i]);
			}
		}
		return accum.toString();
	}
 
	// 模糊查询
	private String whereFuzzy(String[] propertyNames, Object[] values) {
		StringBuilder accum = new StringBuilder(" where 1 = 1");
		for (int i = 0, len = propertyNames.length; i < len; i++) {
			accum.append(" and ");
			if (values[i] instanceof String) {
				accum.append(propertyNames[i]).append(" like '%").append(values[i]).append("%'");
			} else {
				accum.append(propertyNames[i]).append("=").append(values[i]);
			}
		}
		return accum.toString();
	}
 
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
 
	public void save(E e) {
		getSession().save(e);
	}
 

	public void update(E e) {
		getSession().update(e);
	}
 

	public void saveOrUpdate(E e) {
		getSession().saveOrUpdate(e);
	}
 

	public void delete(E e) {
		getSession().delete(e);
	}
 

	public void deleteByProperty(String propertyName, Object value) {
		List<E> es = findByProperty("",propertyName,value,null);
		for (E e : es) {
			delete(e);
		}
	}
 
	public E findById(PK id) {
		return (E) getSession().get(getClazz(), id);
	}
 

	public List<E> findAll(String columns, Pagination pagination) {
		String hql="";
		if(columns==null||columns.trim().equals("")){
			hql=from();
		}
		else{
			hql="SELECT "+columns+" "+from();
		}
		Query query = getSession().createQuery(hql);
		if (pagination != null) {
			query.setFirstResult(pagination.getStart());
			query.setMaxResults(pagination.getEnd());
		}
		return query.list();
	}
	
	

	public List<E> findByPropertys(String columns,String[] propertyNames, Object[] values, Pagination pagination) {
		String hql="";
		if(columns==null||columns.trim().equals("")){
			hql=from();
		}
		else{
			hql="SELECT "+columns+" "+from();
		}
		Query query = getSession().createQuery(hql + where(propertyNames, values));
		if (pagination != null) {
			query.setFirstResult(pagination.getStart());
			query.setMaxResults(pagination.getEnd());
		}
		return query.list();
	}
 
 

	public List<E> findByProperty(String columns,String propertyName, Object value, Pagination pagination) {
		String[] propertyNames = { propertyName };
		Object[] values = { value };
		return findByPropertys(columns,propertyNames, values, pagination);
	}
	

	public List<E> findByPropertysFuzzy(String columns,String[] propertyNames, Object[] values, Pagination pagination) {
		String hql="";
		if(columns==null||columns.trim().equals("")){
			hql=from();
		}
		else{
			hql="SELECT "+columns+" "+from();
		}
		Query query = getSession().createQuery(hql + whereFuzzy(propertyNames, values));
		if (pagination != null) {
			query.setFirstResult(pagination.getStart());
			query.setMaxResults(pagination.getEnd());
		}
		return query.list();
	}
	
 
 

	public List<E> findByPropertyFuzzy(String columns,String propertyName, Object value, Pagination pagination) {
		String[] propertyNames = { propertyName };
		Object[] values = { value };
		return findByPropertysFuzzy(columns,propertyNames, values, pagination);
	}
 

	public int countAll() {
		return Integer.valueOf(getSession().createQuery(count()).uniqueResult().toString()) ;
	}
 

	public int countByPropertys(String[] propertyNames, Object[] values) {
		Query query = getSession().createQuery(count() + where(propertyNames, values));
		return Integer.parseInt(query.uniqueResult().toString());
	}
 

	public int queryCountBySQL(SqlBean sql){
		Query query = getSession().createSQLQuery("SELECT COUNT(1) "+sql.getBody());
		//return Integer.parseInt(query.uniqueResult().toString());
		return Integer.parseInt(query.uniqueResult().toString());
		
	    
	}
 

	public List<E> queryBySQL(SqlBean sql) {
	     return queryBySQL(sql,null); 
	}
 

	public List<Map<String, Object>> queryMapByHQL(String hql,Object[] args,Pagination pagination) {
		Session session = getSession();
		Query query = session.createQuery(hql);
		if (args != null && args.length > 0) {
			for (int i = 0; i < args.length; i++)
				query.setParameter(i, args[i]);
		}
		if (pagination != null) {
			query.setFirstResult(pagination.getStart());
			query.setMaxResults(pagination.getEnd());
		}
		List<Map<String, Object>> list=query.list();
		return list;
	}
 

	public int countByPropertysFuzzy(String[] propertyNames, Object[] values) {
		Query query = getSession().createQuery(count() + whereFuzzy(propertyNames, values));
		return Integer.parseInt(query.uniqueResult().toString());
	}
 

	public List<E> queryBySQL(SqlBean sql, Pagination pagination) {
		//SQLQuery query = getSession().createSQLQuery(sql.getHead()+" "+sql.getBottom()+" "+sql.getBottom());
		 SQLQuery query = getSession().createSQLQuery(sql.getHead()+" "+sql.getBody()+" "+sql.getBottom());
		if (pagination != null) {
			System.out.println("=====s======"+pagination.getStart());
            System.out.println("=====e======"+pagination.getEnd());
			query.setFirstResult(pagination.getStart());
			query.setMaxResults(pagination.getEnd());
		}
		return query.list();
	}
 

	public int mapCountByHQL(String hql,Object[] args) {  
		Session session = getSession();
		Query query = session.createQuery(hql);
		if (args != null && args.length > 0) {
			for (int i = 0; i < args.length; i++)
				query.setParameter(i, args[i]);
		}
		return Integer.parseInt(query.list().get(0).toString());
	}
}
