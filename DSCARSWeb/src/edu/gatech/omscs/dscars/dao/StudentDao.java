package edu.gatech.omscs.dscars.dao;


import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.classic.Session;

import edu.gatech.omscs.dscars.model.Student;
import edu.gatech.omscs.dscars.util.HibernateUtil;

public class StudentDao extends HibernateUtil {
	/**
	 * Add a new Student.
	 * @param Student
	 * @return
	 */
	public Student add(Student Student) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(Student);
		session.getTransaction().commit();
		return Student;
	}
	
	public Student update(Student Student) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.update(Student);
		session.getTransaction().commit();
		return Student;
	}
	
	public Student delete(Integer StudentId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Student Student = (Student) session.load(Student.class, StudentId);
		if(null != Student) {
			session.delete(Student);
		}
		session.getTransaction().commit();
		return Student;
	}

	@SuppressWarnings("unchecked")
	public List<Student> list() {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<Student> Students = null;
		try {
			
			Students = (List<Student>) session.createQuery("from Student").list();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return Students;
	}
	
	public Student getStudent(String studentId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();/*lets hope an id of 1 exists!*/
		String queryString = "from Student where StudentId = :StudentId";
		Query query = session.createQuery(queryString);
		query.setString("studentId", studentId);
		Object queryResult = query.uniqueResult();
		Student Student = (Student)queryResult;
		session.getTransaction().commit();
		return Student;
	}
}
