package edu.gatech.omscs.dscars.dao;


import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.classic.Session;

import edu.gatech.omscs.dscars.model.User;
import edu.gatech.omscs.dscars.util.HibernateUtil;

public class UserDao extends HibernateUtil {

	/**
	 * Add a new user.
	 * @param user
	 * @return
	 */
	public User add(User user) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(user);
		session.getTransaction().commit();
		return user;
	}
	public User delete(Integer userId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		User user = (User) session.load(User.class, userId);
		if(null != user) {
			session.delete(user);
		}
		session.getTransaction().commit();
		return user;
	}

	@SuppressWarnings("unchecked")
	public List<User> list() {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<User> users = null;
		try {
			
			users = (List<User>) session.createQuery("from User").list();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return users;
	}

}
