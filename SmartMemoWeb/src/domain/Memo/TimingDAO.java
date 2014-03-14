package domain.Memo;

import java.util.List;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * Timing entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see domain.Memo.Timing
 * @author MyEclipse Persistence Tools
 */
public class TimingDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory.getLogger(TimingDAO.class);
	// property constants
	public static final String START_TIME = "startTime";
	public static final String END_TIME = "endTime";
	public static final String LOCATION = "location";
	public static final String CONTENT = "content";
	public static final String PRIORITY = "priority";

	protected void initDao() {
		// do nothing
	}

	public void save(Timing transientInstance) {
		log.debug("saving Timing instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Timing persistentInstance) {
		log.debug("deleting Timing instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Timing findById(java.lang.Integer id) {
		log.debug("getting Timing instance with id: " + id);
		try {
			Timing instance = (Timing) getHibernateTemplate().get(
					"domain.Memo.Timing", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Timing instance) {
		log.debug("finding Timing instance by example");
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
		log.debug("finding Timing instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Timing as model where model."
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

	public List findByEndTime(Object endTime) {
		return findByProperty(END_TIME, endTime);
	}

	public List findByLocation(Object location) {
		return findByProperty(LOCATION, location);
	}

	public List findByContent(Object content) {
		return findByProperty(CONTENT, content);
	}

	public List findByPriority(Object priority) {
		return findByProperty(PRIORITY, priority);
	}

	public List findAll() {
		log.debug("finding all Timing instances");
		try {
			String queryString = "from Timing";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	public List findByTel(String tel){
		log.debug("finding Timing by tel");
		try {
			String queryString = "from Timing where user = '" + tel + "'";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("finding failed", re);
			throw re;
		}
	}

	public Timing merge(Timing detachedInstance) {
		log.debug("merging Timing instance");
		try {
			Timing result = (Timing) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Timing instance) {
		log.debug("attaching dirty Timing instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Timing instance) {
		log.debug("attaching clean Timing instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static TimingDAO getFromApplicationContext(ApplicationContext ctx) {
		return (TimingDAO) ctx.getBean("TimingDAO");
	}
}