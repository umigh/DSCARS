package edu.gatech.omscs.dscars.test;
// default package
// Generated Mar 28, 2015 1:24:52 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class PreferredCourseHistory.
 * @see .PreferredCourseHistory
 * @author Hibernate Tools
 */
@Stateless
public class PreferredCourseHistoryHome {

	private static final Log log = LogFactory
			.getLog(PreferredCourseHistoryHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(PreferredCourseHistory transientInstance) {
		log.debug("persisting PreferredCourseHistory instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(PreferredCourseHistory persistentInstance) {
		log.debug("removing PreferredCourseHistory instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public PreferredCourseHistory merge(PreferredCourseHistory detachedInstance) {
		log.debug("merging PreferredCourseHistory instance");
		try {
			PreferredCourseHistory result = entityManager
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public PreferredCourseHistory findById(Integer id) {
		log.debug("getting PreferredCourseHistory instance with id: " + id);
		try {
			PreferredCourseHistory instance = entityManager.find(
					PreferredCourseHistory.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
