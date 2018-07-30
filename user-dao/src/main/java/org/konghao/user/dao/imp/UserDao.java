package org.konghao.user.dao.imp;

import org.hibernate.Session;
import org.konghao.user.dao.IUserDao;
import org.konghao.util.HibernateUtil;

import cn.mrsong.user.core.User;

public class UserDao implements IUserDao {

	public User GetUserInfo(String userId) {
		Session session = HibernateUtil.openSession();
		session.beginTransaction();
		
		return null;
	}

}
