package edu.gatech.omscs.dscars.test;
// default package
// Generated Mar 27, 2015 4:54:22 PM by Hibernate Tools 4.0.0

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class RecommendedSection.
 * @see .RecommendedSection
 * @author Hibernate Tools
 */
public class RecommendedSectionHome {

	private static final Log log = LogFactory
			.getLog(RecommendedSectionHome.class);

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext()
					.lookup("SessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException(
					"Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(RecommendedSection transientInstance) {
		log.debug("persisting RecommendedSection instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(RecommendedSection instance) {
		log.debug("attaching dirty RecommendedSection instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(RecommendedSection instance) {
		log.debug("attaching clean RecommendedSection instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(RecommendedSection persistentInstance) {
		log.debug("deleting RecommendedSection instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public RecommendedSection merge(RecommendedSection detachedInstance) {
		log.debug("merging RecommendedSection instance");
		try {
			RecommendedSection result = (RecommendedSection) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public RecommendedSection findById(int id) {
		log.debug("getting RecommendedSection instance with id: " + id);
		try {
			RecommendedSection instance = (RecommendedSection) sessionFactory
					.getCurrentSession().get("RecommendedSection", id);
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<RecommendedSection> findByExample(RecommendedSection instance) {
		log.debug("finding RecommendedSection instance by example");
		try {
			List<RecommendedSection> results = (List<RecommendedSection>) sessionFactory
					.getCurrentSession().createCriteria("RecommendedSection")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
