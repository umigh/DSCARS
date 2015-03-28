package edu.gatech.omscs.dscars.test;
// default package
// Generated Mar 28, 2015 1:24:52 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class StudentCourseHistory.
 * @see .StudentCourseHistory
 * @author Hibernate Tools
 */
@Stateless
public class StudentCourseHistoryHome {

	private static final Log log = LogFactory
			.getLog(StudentCourseHistoryHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(StudentCourseHistory transientInstance) {
		log.debug("persisting StudentCourseHistory instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(StudentCourseHistory persistentInstance) {
		log.debug("removing StudentCourseHistory instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public StudentCourseHistory merge(StudentCourseHistory detachedInstance) {
		log.debug("merging StudentCourseHistory instance");
		try {
			StudentCourseHistory result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public StudentCourseHistory findById(Integer id) {
		log.debug("getting StudentCourseHistory instance with id: " + id);
		try {
			StudentCourseHistory instance = entityManager.find(
					StudentCourseHistory.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
