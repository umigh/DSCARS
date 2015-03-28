package edu.gatech.omscs.dscars.test;
// default package
// Generated Mar 28, 2015 1:24:52 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class CoreEngine.
 * @see .CoreEngine
 * @author Hibernate Tools
 */
@Stateless
public class CoreEngineHome {

	private static final Log log = LogFactory.getLog(CoreEngineHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(CoreEngine transientInstance) {
		log.debug("persisting CoreEngine instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(CoreEngine persistentInstance) {
		log.debug("removing CoreEngine instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public CoreEngine merge(CoreEngine detachedInstance) {
		log.debug("merging CoreEngine instance");
		try {
			CoreEngine result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public CoreEngine findById(Integer id) {
		log.debug("getting CoreEngine instance with id: " + id);
		try {
			CoreEngine instance = entityManager.find(CoreEngine.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
