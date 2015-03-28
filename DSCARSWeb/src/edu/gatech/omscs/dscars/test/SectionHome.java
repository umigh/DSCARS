package edu.gatech.omscs.dscars.test;
// default package
// Generated Mar 28, 2015 1:24:52 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Section.
 * @see .Section
 * @author Hibernate Tools
 */
@Stateless
public class SectionHome {

	private static final Log log = LogFactory.getLog(SectionHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Section transientInstance) {
		log.debug("persisting Section instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Section persistentInstance) {
		log.debug("removing Section instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Section merge(Section detachedInstance) {
		log.debug("merging Section instance");
		try {
			Section result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Section findById(Integer id) {
		log.debug("getting Section instance with id: " + id);
		try {
			Section instance = entityManager.find(Section.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
