package domain.Memo;

import java.util.List;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * Realtime entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see domain.Memo.Realtime
 * @author MyEclipse Persistence Tools
 */
public class RealtimeDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(RealtimeDAO.class);
	// property constants
	public static final String START_TIME = "startTime";
	public static final String LOCATION = "location";
	public static final String AGING = "aging";
	public static final String CONTENT = "content";
	public static final String PRIORITY = "priority";

	protected void initDao() {
		// do nothing
	}

	public void save(Realtime transientInstance) {
		log.debug("saving Realtime instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Realtime persistentInstance) {
		log.debug("deleting Realtime instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Realtime findById(java.lang.Integer id) {
		log.debug("getting Realtime instance with id: " + id);
		try {
			Realtime instance = (Realtime) getHibernateTemplate().get(
					"domain.Memo.Realtime", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Realtime instance) {
		log.debug("finding Realtime instance by example");
		try {
			List results = getHibernateTemplate().findByExample(instance);
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Realtime instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Realtime as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByStartTime(Object startTime) {
		return findByProperty(START_TIME, startTime);
	}

	public List findByLocation(Object location) {
		return findByProperty(LOCATION, location);
	}

	public List findByAging(Object aging) {
		return findByProperty(AGING, aging);
	}

	public List findByContent(Object content) {
		return findByProperty(CONTENT, content);
	}

	public List findByPriority(Object priority) {
		return findByProperty(PRIORITY, priority);
	}

	public List findByTel(String tel) {
		log.debug("finding Realtime by tel");
		try {
			String queryString = "from Realtime where user = '" + tel + "'";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("finding failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all Realtime instances");
		try {
			String queryString = "from Realtime";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Realtime merge(Realtime detachedInstance) {
		log.debug("merging Realtime instance");
		try {
			Realtime result = (Realtime) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Realtime instance) {
		log.debug("attaching dirty Realtime instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Realtime instance) {
		log.debug("attaching clean Realtime instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static RealtimeDAO getFromApplicationContext(ApplicationContext ctx) {
		return (RealtimeDAO) ctx.getBean("RealtimeDAO");
	}
}