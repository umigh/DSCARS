package edu.gatech.omscs.dscars.dao;


import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.classic.Session;

import edu.gatech.omscs.dscars.model.Instructor;
import edu.gatech.omscs.dscars.util.HibernateUtil;

public class InstructorDao extends HibernateUtil {
	/**
	 * Add a new Instructor.
	 * @param Instructor
	 * @return
	 */
	public Instructor add(Instructor instructor) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(instructor);
		session.getTransaction().commit();
		return instructor;
	}
	
	public Instructor update(Instructor instructor) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.update(instructor);
		session.getTransaction().commit();
		return instructor;
	}
	
	public Instructor delete(Integer instructorId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Instructor instructor = (Instructor) session.load(Instructor.class, instructorId);
		if(null != instructor) {
			session.delete(instructor);
		}
		session.getTransaction().commit();
		return instructor;
	}

	@SuppressWarnings("unchecked")
	public List<Instructor> list() {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<Instructor> instructors = null;
		try {
			
			instructors = (List<Instructor>) session.createQuery("from Instructor").list();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return instructors;
	}
	
	@SuppressWarnings("unchecked")
	public List<Instructor> professorList() {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<Instructor> instructors = null;
		try {
			
			instructors = (List<Instructor>) session.createQuery("from Instructor where IsProfessor = 1").list();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return instructors;
	}
	
	@SuppressWarnings("unchecked")
	public List<Instructor> taList() {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<Instructor> instructors = null;
		try {
			
			instructors = (List<Instructor>) session.createQuery("from Instructor where IsProfessor = 0").list();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return instructors;
	}
	
	public Instructor getInstructor(int instructorId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();/*lets hope an id of 1 exists!*/
		String queryString = "from Instructor where InstructorId = :InstructorId";
		Query query = session.createQuery(queryString);
		query.setInteger("InstructorId", instructorId);
		Object queryResult = query.uniqueResult();
		Instructor instructor = (Instructor)queryResult;
		session.getTransaction().commit();
		return instructor;
	}
	
	@SuppressWarnings("unchecked")
	public List<Instructor> listTAs() {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<Instructor> instructors = null;
		try {
			
			instructors = (List<Instructor>) session.createQuery("from Instructor where isProfessor='N'").list();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return instructors;
	}
}
