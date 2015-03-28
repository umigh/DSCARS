package edu.gatech.omscs.dscars.test;
// default package
// Generated Mar 28, 2015 1:24:52 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Semester.
 * @see .Semester
 * @author Hibernate Tools
 */
@Stateless
public class SemesterHome {

	private static final Log log = LogFactory.getLog(SemesterHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Semester transientInstance) {
		log.debug("persisting Semester instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Semester persistentInstance) {
		log.debug("removing Semester instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Semester merge(Semester detachedInstance) {
		log.debug("merging Semester instance");
		try {
			Semester result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Semester findById(int id) {
		log.debug("getting Semester instance with id: " + id);
		try {
			Semester instance = entityManager.find(Semester.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
