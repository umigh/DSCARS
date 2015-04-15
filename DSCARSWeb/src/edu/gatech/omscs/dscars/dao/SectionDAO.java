package edu.gatech.omscs.dscars.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.classic.Session;

import edu.gatech.omscs.dscars.model.Section;
import edu.gatech.omscs.dscars.util.HibernateUtil;

public class SectionDAO {
	
	public Section add(Section section) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(section);
		session.getTransaction().commit();
		return section;
	}
	
	public Section update(Section section) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.update(section);
		session.getTransaction().commit();
		return section;
	}
	
	public Section delete(Integer sectionId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Section section = (Section) session.load(Section.class, sectionId);
		if(null != section) {
			session.delete(section);
		}
		session.getTransaction().commit();
		return section;
	}
	
	public Section getSection(int sectionId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();/*lets hope an id of 1 exists!*/
		String queryString = "from Section where SectionId = :SectionId";
		Query query = session.createQuery(queryString);
		query.setInteger("SectionId", sectionId);
		Object queryResult = query.uniqueResult();
		Section section = (Section)queryResult;
		session.getTransaction().commit();
		return section;
	}
	
	@SuppressWarnings("unchecked")
	public List<Section> getSections() {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<Section> sections = null;
		try {
			
			sections = (List<Section>) session.createQuery("from Section").list();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return sections;
	}
	
	@SuppressWarnings("unchecked")
	public List<Section> getAllSections(int semesterId, int programId) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<Section> sections = null;
		try {			
			Query query = session.createQuery("from Section where semester.semesterId = :semesterId and program.programId=:programId");
			query.setInteger("semesterId", semesterId);
			query.setInteger("programId", programId);
			sections = (List<Section>) query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return sections;
	}
	
	@SuppressWarnings("unchecked")
	public List<Section> getSectionsOffered(int semesterId) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<Section> sections = null;
		try {			
			Query query = session.createQuery("from Section where semester.semesterId = :semesterId and offered='Y'");
			query.setInteger("semesterId", semesterId);
			sections = (List<Section>) query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return sections;
	}
}
