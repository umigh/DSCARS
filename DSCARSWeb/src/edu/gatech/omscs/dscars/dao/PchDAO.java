package edu.gatech.omscs.dscars.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.classic.Session;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import edu.gatech.omscs.dscars.model.PchSub;
import edu.gatech.omscs.dscars.model.PreferredCourseHistory;
import edu.gatech.omscs.dscars.util.HibernateUtil;

public class PchDAO {   
	public PreferredCourseHistory add(PreferredCourseHistory pch) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		pch.setDateCreated(new Date());
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
	
	public void deletePchSubBySectionId(Integer sectionId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query query = session.createQuery("delete PchSub where section.sectionId=:sectionId");
		query.setParameter("sectionId", sectionId);
		query.executeUpdate();
		session.getTransaction().commit();
	}
	
	@SuppressWarnings({ "deprecation" })
	public List<Integer> getEligibleCourses(int studentId) {	
		List<Integer> list=new ArrayList<Integer>();
		try {
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			Connection con=session.connection();
			PreparedStatement st=con.prepareStatement("select distinct courseId from SectionAvailableToStudent where studentId=?");
			st.setInt(1, studentId);
			ResultSet rs=st.executeQuery();
			
			while(rs.next()) {
				list.add(rs.getInt(1));
			}
			session.getTransaction().commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return  list;
	}
	
	@SuppressWarnings("rawtypes")
	public Map<Integer,Integer> getDemand() {
		Map<Integer,Integer> demand = new HashMap<Integer,Integer>();
		try {
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
		    Criteria criteria = session.createCriteria(PchSub.class);
		    //criteria.add(Restrictions.eq("section.semester", semesterId));
		    ProjectionList projectionList = Projections.projectionList();
		    projectionList.add(Projections.groupProperty("section.sectionId"));
		    projectionList.add(Projections.rowCount());
		    criteria.setProjection(projectionList);
		    List list = criteria.list();
		    Iterator iter = list.iterator();
	        while (iter.hasNext()) {
	            Object[] obj = (Object[]) iter.next();
	            Integer sectionId = (Integer) obj[0];	            
		        demand.put(sectionId, (Integer) obj[1]);
	        }
		    session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}	    
	    return demand;
	}
	
	public PchSub updateSub(PchSub sub) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.update(sub);
		session.getTransaction().commit();
		return sub;
	}
	
	public void recommendPchSub(int studentId, int sectionId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();/*lets hope an id of 1 exists!*/
		String queryString = "from PchSub where section.sectionId = :sectionId and preferredCourseHistory.student.studentId=:studentId";
		Query query = session.createQuery(queryString);
		query.setInteger("sectionId", sectionId);
		query.setInteger("studentId", studentId);
		Object queryResult = query.uniqueResult();
		PchSub pchSub = (PchSub)queryResult;
		pchSub.setRecommendedDate(new Date());
		session.update(pchSub);
		session.getTransaction().commit();
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

	public PreferredCourseHistory getStudentPchBySemester(int semesterId, int studentId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();/*lets hope an id of 1 exists!*/
		String queryString = "from PreferredCourseHistory where semesterId = :semesterId and studentId=:studentId ";
		Query query = session.createQuery(queryString);
		query.setInteger("semesterId", semesterId);
		query.setInteger("studentId", studentId);
		Object queryResult = query.uniqueResult();
		PreferredCourseHistory PreferredCourseHistory = (PreferredCourseHistory)queryResult;
		session.getTransaction().commit();
		return PreferredCourseHistory;
	}	
		
	
	@SuppressWarnings("unchecked")
	public List<PreferredCourseHistory> getStudentPch(int semesterId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();	
		List<PreferredCourseHistory> preferredCourseHistorys = null;
		session.beginTransaction();/*lets hope an id of 1 exists!*/
		String queryString = "from PreferredCourseHistory where semesterId = :semesterId";
		Query query = session.createQuery(queryString);
		query.setInteger("semesterId", semesterId);
		preferredCourseHistorys = query.list();
		session.getTransaction().commit();
		return preferredCourseHistorys;
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
