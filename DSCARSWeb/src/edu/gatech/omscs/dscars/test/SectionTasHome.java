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
 * Home object for domain model class SectionTas.
 * @see .SectionTas
 * @author Hibernate Tools
 */
public class SectionTasHome {

	private static final Log log = LogFactory.getLog(SectionTasHome.class);

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

	public void persist(SectionTas transientInstance) {
		log.debug("persisting SectionTas instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(SectionTas instance) {
		log.debug("attaching dirty SectionTas instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(SectionTas instance) {
		log.debug("attaching clean SectionTas instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(SectionTas persistentInstance) {
		log.debug("deleting SectionTas instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public SectionTas merge(SectionTas detachedInstance) {
		log.debug("merging SectionTas instance");
		try {
			SectionTas result = (SectionTas) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public SectionTas findById(int id) {
		log.debug("getting SectionTas instance with id: " + id);
		try {
			SectionTas instance = (SectionTas) sessionFactory
					.getCurrentSession().get("SectionTas", id);
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

	public List<SectionTas> findByExample(SectionTas instance) {
		log.debug("finding SectionTas instance by example");
		try {
			List<SectionTas> results = (List<SectionTas>) sessionFactory
					.getCurrentSession().createCriteria("SectionTas")
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
