package edu.gatech.omscs.dscars.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.classic.Session;

import edu.gatech.omscs.dscars.model.Semester;
import edu.gatech.omscs.dscars.util.HibernateUtil;

public class SemesterDAO {
	
	public Semester add(Semester semester) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(semester);
		session.getTransaction().commit();
		return semester;
	}
	
	public Semester update(Semester semester) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.update(semester);
		session.getTransaction().commit();
		return semester;
	}
	
	public Semester delete(Integer semesterId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Semester semester = (Semester) session.load(Semester.class, semesterId);
		if(null != semester) {
			session.delete(semester);
		}
		session.getTransaction().commit();
		return semester;
	}
	
	public Semester getSemester(int semesterId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();/*lets hope an id of 1 exists!*/
		String queryString = "from Semester where semesterId = :semesterId";
		Query query = session.createQuery(queryString);
		query.setInteger("semesterId", semesterId);
		Object queryResult = query.uniqueResult();
		Semester semester = (Semester)queryResult;
		session.getTransaction().commit();
		return semester;
	}
	
	@SuppressWarnings("unchecked")
	public List<Semester> getSemesters() {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<Semester> semesters = null;
		try {
			
			semesters = (List<Semester>) session.createQuery("from Semester").list();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return semesters;
	}
}
