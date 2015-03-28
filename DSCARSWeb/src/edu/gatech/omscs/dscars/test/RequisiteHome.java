package edu.gatech.omscs.dscars.test;
// default package
// Generated Mar 28, 2015 1:24:52 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Requisite.
 * @see .Requisite
 * @author Hibernate Tools
 */
@Stateless
public class RequisiteHome {

	private static final Log log = LogFactory.getLog(RequisiteHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Requisite transientInstance) {
		log.debug("persisting Requisite instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Requisite persistentInstance) {
		log.debug("removing Requisite instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Requisite merge(Requisite detachedInstance) {
		log.debug("merging Requisite instance");
		try {
			Requisite result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Requisite findById(Integer id) {
		log.debug("getting Requisite instance with id: " + id);
		try {
			Requisite instance = entityManager.find(Requisite.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
