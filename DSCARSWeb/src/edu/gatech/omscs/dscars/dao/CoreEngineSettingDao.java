package edu.gatech.omscs.dscars.dao;


import java.util.Date;

import org.hibernate.Query;
import org.hibernate.classic.Session;

import edu.gatech.omscs.dscars.exception.SettingLockedException;
import edu.gatech.omscs.dscars.model.CoreEngineSetting;
import edu.gatech.omscs.dscars.util.HibernateUtil;

public class CoreEngineSettingDao extends HibernateUtil {
	/**
	 * Only used by UI. Core engine should not use this.It does the folllowing.
	 * 1. Add a new setting record if there is no setting present for the active semester provided.
	 * 2. Updates existing setting record, if the setting is not locked by core engine.
	 * 3. Throws exception if it is locked locked by core engine.
	 * @param coreEngineSetting
	 * @return
	 * @throws SettingLockedException 
	 */
	public CoreEngineSetting addOrUpdate(CoreEngineSetting coreEngineSetting) throws SettingLockedException {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		String queryString = "from CoreEngineSetting where activeSemester.semesterId = :activeSemesterId";
		Query query = session.createQuery(queryString);
		query.setInteger("activeSemesterId", coreEngineSetting.getActiveSemester().getSemesterId());
		Object queryResult = query.uniqueResult();
		CoreEngineSetting setting = (CoreEngineSetting)queryResult;
		
		if(setting==null) {
			coreEngineSetting.setStatus(CoreEngineSetting.NEW);
			coreEngineSetting.setUpdateDate(new Date());
			session.save(coreEngineSetting);
		}
		else if(setting.isLocked()) {
			throw new SettingLockedException("Core Engine Setting is locked");
		}
		else {
			setting.setMaxClassSizeDefault(coreEngineSetting.getMaxClassSizeDefault());
			setting.setStudentsPerInstructor(coreEngineSetting.getStudentsPerInstructor());
			setting.setUser(coreEngineSetting.getUser());
			setting.setShadow(coreEngineSetting.isShadow());
			setting.setStatus(CoreEngineSetting.NEW);
			setting.setUpdateDate(new Date());
			coreEngineSetting=null;
			session.update(setting);			
		}
		session.getTransaction().commit();
		return coreEngineSetting;
	}
	

	
	
	/**
	 * Used by core engine only.
	 * @param coreEngineSetting
	 * @return
	 */
	public CoreEngineSetting lock(CoreEngineSetting coreEngineSetting) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		coreEngineSetting.setStatus(CoreEngineSetting.LOCKED);
		coreEngineSetting.setUpdateDate(new Date());
		session.update(coreEngineSetting);
		session.getTransaction().commit();
		return coreEngineSetting;
	}
		
	/**
	 * Used by core engine only.
	 * @param coreEngineSetting
	 * @return
	 */
	public CoreEngineSetting complete(CoreEngineSetting coreEngineSetting) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		coreEngineSetting.setStatus(CoreEngineSetting.COMPLETE);
		coreEngineSetting.setUpdateDate(new Date());
		session.update(coreEngineSetting);
		session.getTransaction().commit();
		return coreEngineSetting;
	}
	
	public CoreEngineSetting getCoreEngineSetting(int coreEngineSettingId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();/*lets hope an id of 1 exists!*/
		String queryString = "from CoreEngineSetting where coreEngineSettingId = :coreEngineSettingId";
		Query query = session.createQuery(queryString);
		query.setInteger("CoreEngineSettingId", coreEngineSettingId);
		Object queryResult = query.uniqueResult();
		CoreEngineSetting coreEngineSetting = (CoreEngineSetting)queryResult;
		session.getTransaction().commit();
		return coreEngineSetting;
	}
	
	public CoreEngineSetting getCoreEngineSettingBySemester(int activeSemesterId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();/*lets hope an id of 1 exists!*/
		String queryString = "from CoreEngineSetting where activeSemester.semesterId = :activeSemesterId and status='New'";
		Query query = session.createQuery(queryString);
		query.setInteger("activeSemesterId", activeSemesterId);
		Object queryResult = query.uniqueResult();
		CoreEngineSetting coreEngineSetting = (CoreEngineSetting)queryResult;
		session.getTransaction().commit();
		return coreEngineSetting;
	}
}
