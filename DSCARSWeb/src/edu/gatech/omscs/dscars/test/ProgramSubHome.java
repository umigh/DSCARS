package edu.gatech.omscs.dscars.test;
// default package
// Generated Mar 28, 2015 1:24:52 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class ProgramSub.
 * @see .ProgramSub
 * @author Hibernate Tools
 */
@Stateless
public class ProgramSubHome {

	private static final Log log = LogFactory.getLog(ProgramSubHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(ProgramSub transientInstance) {
		log.debug("persisting ProgramSub instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(ProgramSub persistentInstance) {
		log.debug("removing ProgramSub instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public ProgramSub merge(ProgramSub detachedInstance) {
		log.debug("merging ProgramSub instance");
		try {
			ProgramSub result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public ProgramSub findById(Integer id) {
		log.debug("getting ProgramSub instance with id: " + id);
		try {
			ProgramSub instance = entityManager.find(ProgramSub.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
