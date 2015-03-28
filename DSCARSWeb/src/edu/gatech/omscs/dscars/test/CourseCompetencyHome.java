package edu.gatech.omscs.dscars.test;
// default package
// Generated Mar 28, 2015 1:24:52 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class CourseCompetency.
 * @see .CourseCompetency
 * @author Hibernate Tools
 */
@Stateless
public class CourseCompetencyHome {

	private static final Log log = LogFactory
			.getLog(CourseCompetencyHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(CourseCompetency transientInstance) {
		log.debug("persisting CourseCompetency instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(CourseCompetency persistentInstance) {
		log.debug("removing CourseCompetency instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public CourseCompetency merge(CourseCompetency detachedInstance) {
		log.debug("merging CourseCompetency instance");
		try {
			CourseCompetency result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public CourseCompetency findById(Integer id) {
		log.debug("getting CourseCompetency instance with id: " + id);
		try {
			CourseCompetency instance = entityManager.find(
					CourseCompetency.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
