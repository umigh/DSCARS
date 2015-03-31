package edu.gatech.omscs.dscars.dao;


import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
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
	
	public User update(User user) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.update(user);
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
	
	public User getUser(String userId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();/*lets hope an id of 1 exists!*/
		String queryString = "from User where userId = :userId";
		Query query = session.createQuery(queryString);
		query.setString("userId", userId);
		Object queryResult = query.uniqueResult();
		User user = (User)queryResult;
		session.getTransaction().commit();
		return user;
	}
}
