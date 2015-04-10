package edu.gatech.omscs.dscars.dao;

import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.classic.Session;

import edu.gatech.omscs.dscars.model.PchSub;
import edu.gatech.omscs.dscars.model.PreferredCourseHistory;
import edu.gatech.omscs.dscars.model.User;
import edu.gatech.omscs.dscars.util.HibernateUtil;

public class PchDAO {   
	public PreferredCourseHistory add(PreferredCourseHistory pch) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(pch); 
		Iterator<PchSub> iter=pch.getPchSubs().iterator();
		while(iter.hasNext()) {
			PchSub sub=iter.next();
			sub.setPreferredCourseHistory(pch);
			session.save(sub);
		}
		session.getTransaction().commit();
		return pch;
	}
	
	
	public PchSub addSub(PchSub sub) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(sub);
		session.getTransaction().commit();
		return sub;
	}
	
	public PreferredCourseHistory update(PreferredCourseHistory pch) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.update(pch);
		session.getTransaction().commit();
		return pch;
	}
	
	public PreferredCourseHistory delete(Integer pchId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		PreferredCourseHistory pch = (PreferredCourseHistory) session.load(PreferredCourseHistory.class, pchId);
		if(null != pch) {
			session.delete(pch);
		}
		session.getTransaction().commit();
		return pch;
	}
	
	public void deleteSub(Integer pchSubId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		PchSub sub = (PchSub) session.load(PchSub.class, pchSubId);
		if(null != sub) {
			session.delete(sub);
		}
		session.getTransaction().commit();
	}
	
	public PchSub updateSub(PchSub sub) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.update(sub);
		session.getTransaction().commit();
		return sub;
	}
	
	public PreferredCourseHistory getpch(int pchId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();/*lets hope an id of 1 exists!*/
		String queryString = "from PreferredCourseHistory where pchId = :pchId";
		Query query = session.createQuery(queryString);
		query.setInteger("pchId", pchId);
		Object queryResult = query.uniqueResult();
		PreferredCourseHistory PreferredCourseHistory = (PreferredCourseHistory)queryResult;
		session.getTransaction().commit();
		return PreferredCourseHistory;
	}	

	public PreferredCourseHistory getStudentPch(int programId, int semesterId, int studentId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();/*lets hope an id of 1 exists!*/
		String queryString = "from PreferredCourseHistory where programId = :programId and semesterId = :semesterId and studentId=:studentId ";
		Query query = session.createQuery(queryString);
		query.setInteger("programId", programId);
		query.setInteger("semesterId", semesterId);
		query.setInteger("studentId", studentId);
		Object queryResult = query.uniqueResult();
		PreferredCourseHistory PreferredCourseHistory = (PreferredCourseHistory)queryResult;
		session.getTransaction().commit();
		return PreferredCourseHistory;
	}	
	
	@SuppressWarnings("unchecked")
	public List<PreferredCourseHistory> getPch() {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<PreferredCourseHistory> PreferredCourseHistorys = null;
		try {
			
			PreferredCourseHistorys = (List<PreferredCourseHistory>) session.createQuery("from PreferredCourseHistory").list();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return PreferredCourseHistorys;
	}
	

}
