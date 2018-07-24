package org.konghao.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	private static SessionFactory factory = null;
	
	static{
		factory = new Configuration().configure().buildSessionFactory();
	}
	
	public static Session openSession(){
		return factory.openSession();
	}
}