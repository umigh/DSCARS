package edu.gatech.omscs.dscars.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.classic.Session;

import edu.gatech.omscs.dscars.model.Course;
import edu.gatech.omscs.dscars.util.HibernateUtil;

public class CourseDAO {   
	public Course add(Course Course) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(Course);
		session.getTransaction().commit();
		return Course;
	}
	
	public Course update(Course Course) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.update(Course);
		session.getTransaction().commit();
		return Course;
	}
	
	public Course delete(Integer CourseId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Course Course = (Course) session.load(Course.class, CourseId);
		if(null != Course) {
			session.delete(Course);
		}
		session.getTransaction().commit();
		return Course;
	}
	
	public Course getCourse(String courseId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();/*lets hope an id of 1 exists!*/
		String queryString = "from Course where courseId = :courseId";
		Query query = session.createQuery(queryString);
		query.setString("courseId", courseId);
		Object queryResult = query.uniqueResult();
		Course Course = (Course)queryResult;
		session.getTransaction().commit();
		return Course;
	}	

	@SuppressWarnings("unchecked")
	public List<Course> getCourses() {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<Course> Courses = null;
		try {
			
			Courses = (List<Course>) session.createQuery("from Course").list();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return Courses;
	}
	

}
