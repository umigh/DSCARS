package edu.gatech.omscs.dscars.test;
// default package
// Generated Mar 28, 2015 1:24:52 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class RecommendedSchedule.
 * @see .RecommendedSchedule
 * @author Hibernate Tools
 */
@Stateless
public class RecommendedScheduleHome {

	private static final Log log = LogFactory
			.getLog(RecommendedScheduleHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(RecommendedSchedule transientInstance) {
		log.debug("persisting RecommendedSchedule instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(RecommendedSchedule persistentInstance) {
		log.debug("removing RecommendedSchedule instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public RecommendedSchedule merge(RecommendedSchedule detachedInstance) {
		log.debug("merging RecommendedSchedule instance");
		try {
			RecommendedSchedule result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public RecommendedSchedule findById(Integer id) {
		log.debug("getting RecommendedSchedule instance with id: " + id);
		try {
			RecommendedSchedule instance = entityManager.find(
					RecommendedSchedule.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
