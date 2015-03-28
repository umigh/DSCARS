package edu.gatech.omscs.dscars.test;
// default package
// Generated Mar 28, 2015 1:24:52 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class SectionTas.
 * @see .SectionTas
 * @author Hibernate Tools
 */
@Stateless
public class SectionTasHome {

	private static final Log log = LogFactory.getLog(SectionTasHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(SectionTas transientInstance) {
		log.debug("persisting SectionTas instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(SectionTas persistentInstance) {
		log.debug("removing SectionTas instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public SectionTas merge(SectionTas detachedInstance) {
		log.debug("merging SectionTas instance");
		try {
			SectionTas result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public SectionTas findById(Integer id) {
		log.debug("getting SectionTas instance with id: " + id);
		try {
			SectionTas instance = entityManager.find(SectionTas.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
