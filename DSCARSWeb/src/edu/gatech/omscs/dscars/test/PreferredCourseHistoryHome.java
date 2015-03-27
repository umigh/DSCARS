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
 * Home object for domain model class PreferredCourseHistory.
 * @see .PreferredCourseHistory
 * @author Hibernate Tools
 */
public class PreferredCourseHistoryHome {

	private static final Log log = LogFactory
			.getLog(PreferredCourseHistoryHome.class);

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

	public void persist(PreferredCourseHistory transientInstance) {
		log.debug("persisting PreferredCourseHistory instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(PreferredCourseHistory instance) {
		log.debug("attaching dirty PreferredCourseHistory instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(PreferredCourseHistory instance) {
		log.debug("attaching clean PreferredCourseHistory instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(PreferredCourseHistory persistentInstance) {
		log.debug("deleting PreferredCourseHistory instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public PreferredCourseHistory merge(PreferredCourseHistory detachedInstance) {
		log.debug("merging PreferredCourseHistory instance");
		try {
			PreferredCourseHistory result = (PreferredCourseHistory) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public PreferredCourseHistory findById(int id) {
		log.debug("getting PreferredCourseHistory instance with id: " + id);
		try {
			PreferredCourseHistory instance = (PreferredCourseHistory) sessionFactory
					.getCurrentSession().get("PreferredCourseHistory", id);
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

	public List<PreferredCourseHistory> findByExample(
			PreferredCourseHistory instance) {
		log.debug("finding PreferredCourseHistory instance by example");
		try {
			List<PreferredCourseHistory> results = (List<PreferredCourseHistory>) sessionFactory
					.getCurrentSession()
					.createCriteria("PreferredCourseHistory")
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
