package edu.gatech.omscs.dscars.test;
// default package
// Generated Mar 28, 2015 1:24:52 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class RecommendedStudents.
 * @see .RecommendedStudents
 * @author Hibernate Tools
 */
@Stateless
public class RecommendedStudentsHome {

	private static final Log log = LogFactory
			.getLog(RecommendedStudentsHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(RecommendedStudents transientInstance) {
		log.debug("persisting RecommendedStudents instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(RecommendedStudents persistentInstance) {
		log.debug("removing RecommendedStudents instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public RecommendedStudents merge(RecommendedStudents detachedInstance) {
		log.debug("merging RecommendedStudents instance");
		try {
			RecommendedStudents result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public RecommendedStudents findById(Integer id) {
		log.debug("getting RecommendedStudents instance with id: " + id);
		try {
			RecommendedStudents instance = entityManager.find(
					RecommendedStudents.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
