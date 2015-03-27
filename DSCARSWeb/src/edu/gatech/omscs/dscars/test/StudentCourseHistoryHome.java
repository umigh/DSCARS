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
 * Home object for domain model class StudentCourseHistory.
 * @see .StudentCourseHistory
 * @author Hibernate Tools
 */
public class StudentCourseHistoryHome {

	private static final Log log = LogFactory
			.getLog(StudentCourseHistoryHome.class);

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

	public void persist(StudentCourseHistory transientInstance) {
		log.debug("persisting StudentCourseHistory instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(StudentCourseHistory instance) {
		log.debug("attaching dirty StudentCourseHistory instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(StudentCourseHistory instance) {
		log.debug("attaching clean StudentCourseHistory instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(StudentCourseHistory persistentInstance) {
		log.debug("deleting StudentCourseHistory instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public StudentCourseHistory merge(StudentCourseHistory detachedInstance) {
		log.debug("merging StudentCourseHistory instance");
		try {
			StudentCourseHistory result = (StudentCourseHistory) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public StudentCourseHistory findById(int id) {
		log.debug("getting StudentCourseHistory instance with id: " + id);
		try {
			StudentCourseHistory instance = (StudentCourseHistory) sessionFactory
					.getCurrentSession().get("StudentCourseHistory", id);
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

	public List<StudentCourseHistory> findByExample(
			StudentCourseHistory instance) {
		log.debug("finding StudentCourseHistory instance by example");
		try {
			List<StudentCourseHistory> results = (List<StudentCourseHistory>) sessionFactory
					.getCurrentSession().createCriteria("StudentCourseHistory")
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
