package edu.gatech.omscs.dscars.test;
// default package
// Generated Mar 28, 2015 1:24:52 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class RecommendedTas.
 * @see .RecommendedTas
 * @author Hibernate Tools
 */
@Stateless
public class RecommendedTasHome {

	private static final Log log = LogFactory.getLog(RecommendedTasHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(RecommendedTas transientInstance) {
		log.debug("persisting RecommendedTas instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(RecommendedTas persistentInstance) {
		log.debug("removing RecommendedTas instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public RecommendedTas merge(RecommendedTas detachedInstance) {
		log.debug("merging RecommendedTas instance");
		try {
			RecommendedTas result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public RecommendedTas findById(Integer id) {
		log.debug("getting RecommendedTas instance with id: " + id);
		try {
			RecommendedTas instance = entityManager.find(RecommendedTas.class,
					id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
