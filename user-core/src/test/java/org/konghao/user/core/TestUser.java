package org.konghao.user.core;

import org.hibernate.Session;
import org.junit.Assert;
import org.junit.Test;
import org.konghao.util.HibernateUtil;

import cn.mrsong.user.core.User;

public class TestUser {
	@Test
	public void testAdd() {
		
		Session session = HibernateUtil.openSession();
		session.beginTransaction();
		
//		User user = new User();
//		user.setUsername("admin");
//		user.setPassword("123");
//		user.setEmail("admin@admin.com");
//		
//		session.save(user);
//		
//		Assert.assertTrue(user.getId()>0);
		
		session.getTransaction().commit();
		
	}

}
