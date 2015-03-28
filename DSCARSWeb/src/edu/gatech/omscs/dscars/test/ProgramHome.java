package edu.gatech.omscs.dscars.test;
// default package
// Generated Mar 28, 2015 1:24:52 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Program.
 * @see .Program
 * @author Hibernate Tools
 */
@Stateless
public class ProgramHome {

	private static final Log log = LogFactory.getLog(ProgramHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Program transientInstance) {
		log.debug("persisting Program instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Program persistentInstance) {
		log.debug("removing Program instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Program merge(Program detachedInstance) {
		log.debug("merging Program instance");
		try {
			Program result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Program findById(int id) {
		log.debug("getting Program instance with id: " + id);
		try {
			Program instance = entityManager.find(Program.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
