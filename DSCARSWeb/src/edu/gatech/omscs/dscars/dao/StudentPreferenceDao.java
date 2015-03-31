package edu.gatech.omscs.dscars.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.classic.Session;

import edu.gatech.omscs.dscars.model.StudentPreference;
import edu.gatech.omscs.dscars.util.HibernateUtil;

public class StudentPreferenceDao {
	
	public StudentPreference add(StudentPreference studentPreference) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(studentPreference);
		session.getTransaction().commit();
		return studentPreference;
	}
	
	public StudentPreference update(StudentPreference studentPreference) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.update(studentPreference);
		session.getTransaction().commit();
		return studentPreference;
	}
	
	public StudentPreference delete(Integer studentPreferenceId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		StudentPreference studentPreference = (StudentPreference) session.load(StudentPreference.class, studentPreferenceId);
		if(null != studentPreference) {
			session.delete(studentPreference);
		}
		session.getTransaction().commit();
		return studentPreference;
	}
	
	public StudentPreference getStudentPreference(String studentPreferenceId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();/*lets hope an id of 1 exists!*/
		String queryString = "from StudentPreference where studentPreferenceId = :studentPreferenceId";
		Query query = session.createQuery(queryString);
		query.setString("studentPreferenceId", studentPreferenceId);
		Object queryResult = query.uniqueResult();
		StudentPreference studentPreference = (StudentPreference)queryResult;
		session.getTransaction().commit();
		return studentPreference;
	}
	
	@SuppressWarnings("unchecked")
	public List<StudentPreference> getStudentPreferences() {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<StudentPreference> studentPreferences = null;
		try {
			
			studentPreferences = (List<StudentPreference>) session.createQuery("from StudentPreference").list();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return studentPreferences;
	}
}
