package edu.gatech.omscs.dscars.test;
// default package
// Generated Mar 28, 2015 1:24:52 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class PchSub.
 * @see .PchSub
 * @author Hibernate Tools
 */
@Stateless
public class PchSubHome {

	private static final Log log = LogFactory.getLog(PchSubHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(PchSub transientInstance) {
		log.debug("persisting PchSub instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(PchSub persistentInstance) {
		log.debug("removing PchSub instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public PchSub merge(PchSub detachedInstance) {
		log.debug("merging PchSub instance");
		try {
			PchSub result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public PchSub findById(Integer id) {
		log.debug("getting PchSub instance with id: " + id);
		try {
			PchSub instance = entityManager.find(PchSub.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
