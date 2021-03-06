package edu.gatech.omscs.dscars.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.classic.Session;

import edu.gatech.omscs.dscars.model.SectionTA;
import edu.gatech.omscs.dscars.util.HibernateUtil;

public class SectionTADAO {
	
	public SectionTA add(SectionTA sectionTA) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(sectionTA);
		session.getTransaction().commit();
		return sectionTA;
	}
	
	public SectionTA update(SectionTA sectionTA) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.update(sectionTA);
		session.getTransaction().commit();
		return sectionTA;
	}
	
	public SectionTA delete(Integer sectionTAId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		SectionTA sectionTA = (SectionTA) session.load(SectionTA.class, sectionTAId);
		if(null != sectionTA) {
			session.delete(sectionTA);
		}
		session.getTransaction().commit();
		return sectionTA;
	}
	
	public void delete(Integer sectionId, Integer instructorId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query query = session.createQuery("delete SectionTA where section.sectionId=:sectionId and instructor.instructorId=:instructorId");
		query.setParameter("sectionId", sectionId);
		query.setParameter("instructorId", instructorId);
		query.executeUpdate();
		session.getTransaction().commit();
	}
	
	/**
	 * Only used by solver to update recommendations.
	 * @param sectionId
	 * @param instructorIds
	 * @param isGenerated
	 * @return
	 */
	public List<SectionTA> merge(int sectionId,List<Integer> instructorIds,boolean isGenerated) {
		//delete existing section TAs that were previously generated by solver.
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query query = session.createQuery("delete SectionTA where section.sectionId=:sectionId and Generated='Y'");
		query.setParameter("sectionId", sectionId);
		int result = query.executeUpdate();
		if (result > 0) {
		    System.out.println("section TAs were removed");
		}
		//add new recommendations.
		for(int i=0;i<instructorIds.size();i++) {
			SectionTA sectionTA=new SectionTA(sectionId, instructorIds.get(i), isGenerated);
			session.save(sectionTA);
		}
		session.getTransaction().commit();
		return getSectionTAsBySection(sectionId);
	}
	
	public SectionTA getSectionTA(int staId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();/*lets hope an id of 1 exists!*/
		String queryString = "from Section where staId = :staId";
		Query query = session.createQuery(queryString);
		query.setInteger("staId", staId);
		Object queryResult = query.uniqueResult();
		SectionTA sectionTA = (SectionTA)queryResult;
		session.getTransaction().commit();
		return sectionTA;
	}
	
	@SuppressWarnings("unchecked")
	public List<SectionTA> getAllSectionTAs() {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<SectionTA> sectionTAs = null;
		try {
			
			sectionTAs = (List<SectionTA>) session.createQuery("from SectionTA").list();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return sectionTAs;
	}
	
	@SuppressWarnings("unchecked")
	public List<SectionTA> getSectionTAsBySection(int sectionId) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<SectionTA> sectionTAs = null;
		try {			
			Query query = session.createQuery("from SectionTA where section.sectionId = :sectionId ");
			query.setInteger("sectionId", sectionId);
			sectionTAs = (List<SectionTA>) query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return sectionTAs;
	}
	
	@SuppressWarnings("unchecked")
	public List<SectionTA> getSectionTasBySemester(int semesterId) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<SectionTA> sectionTAs = null;
		try {			
			Query query = session.createQuery("from Section where semester.semesterId = :semesterId ");
			query.setInteger("semesterId", semesterId);
			sectionTAs = (List<SectionTA>) query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return sectionTAs;
	}
}
