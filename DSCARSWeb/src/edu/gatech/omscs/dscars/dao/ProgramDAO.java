package edu.gatech.omscs.dscars.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.classic.Session;

import edu.gatech.omscs.dscars.model.Program;
import edu.gatech.omscs.dscars.util.HibernateUtil;

public class ProgramDAO {
	
	public Program add(Program Program) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(Program);
		session.getTransaction().commit();
		return Program;
	}
	
	public Program update(Program Program) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.update(Program);
		session.getTransaction().commit();
		return Program;
	}
	
	public Program delete(Integer ProgramId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Program Program = (Program) session.load(Program.class, ProgramId);
		if(null != Program) {
			session.delete(Program);
		}
		session.getTransaction().commit();
		return Program;
	}
	
	public Program getProgram(String ProgramId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();/*lets hope an id of 1 exists!*/
		String queryString = "from Program where ProgramId = :ProgramId";
		Query query = session.createQuery(queryString);
		query.setString("ProgramId", ProgramId);
		Object queryResult = query.uniqueResult();
		Program Program = (Program)queryResult;
		session.getTransaction().commit();
		return Program;
	}
	
	@SuppressWarnings("unchecked")
	public List<Program> getPrograms() {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<Program> programs = null;
		try {
			
			programs = (List<Program>) session.createQuery("from Program").list();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return programs;
	}
}
