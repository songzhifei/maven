package cn.mrsong.user.core;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class UserTest {

	public static void main(String[] args) {
		Student s = new Student();
        s.setId(1);
        s.setAge(23);
        s.setName("hzucmj");
        
        Configuration cfg = new Configuration();
        SessionFactory sf = cfg.configure().buildSessionFactory();
        Session session = sf.openSession();
        session.beginTransaction();
        session.save(s);
        session.getTransaction().commit();
        session.close();
        sf.close();

	}

}
