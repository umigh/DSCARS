package edu.gatech.omscs.dscars.dao;


import org.hibernate.Query;
import org.hibernate.classic.Session;

import edu.gatech.omscs.dscars.model.SectionStudent;
import edu.gatech.omscs.dscars.util.HibernateUtil;

public class SectionStudentDao extends HibernateUtil {
	/**
	 * Add a new SectionStudent.
	 * @param SectionStudent
	 * @return
	 */
	public SectionStudent add(SectionStudent SectionStudent) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(SectionStudent);
		session.getTransaction().commit();
		return SectionStudent;
	}
	
	public void delete(int sectionId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query query = session.createQuery("delete SectionStudent where section.sectionId=:sectionId");
		query.setParameter("sectionId", sectionId);
		int result = query.executeUpdate();
		if (result > 0) {
		    //System.out.println("section students were removed");
		}
		session.getTransaction().commit();
	}
	
	public void deleteAll() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query query = session.createQuery("delete SectionStudent");

		int result = query.executeUpdate();
		if (result > 0) {
		    //System.out.println("section students were removed");
		}
		session.getTransaction().commit();
	}
	

}
