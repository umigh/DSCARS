package edu.gatech.omscs.dscars.test;
// default package
// Generated Mar 28, 2015 1:24:52 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class RecommendedSection.
 * @see .RecommendedSection
 * @author Hibernate Tools
 */
@Stateless
public class RecommendedSectionHome {

	private static final Log log = LogFactory
			.getLog(RecommendedSectionHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(RecommendedSection transientInstance) {
		log.debug("persisting RecommendedSection instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(RecommendedSection persistentInstance) {
		log.debug("removing RecommendedSection instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public RecommendedSection merge(RecommendedSection detachedInstance) {
		log.debug("merging RecommendedSection instance");
		try {
			RecommendedSection result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public RecommendedSection findById(Integer id) {
		log.debug("getting RecommendedSection instance with id: " + id);
		try {
			RecommendedSection instance = entityManager.find(
					RecommendedSection.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
