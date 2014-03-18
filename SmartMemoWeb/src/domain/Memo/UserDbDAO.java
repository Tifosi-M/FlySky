package domain.Memo;

import java.util.List;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * UserDb entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see domain.Memo.UserDb
 * @author MyEclipse Persistence Tools
 */
public class UserDbDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory.getLogger(UserDbDAO.class);
	// property constants
	public static final String MEMO_DB = "memoDb";

	protected void initDao() {
		// do nothing
	}

	public void save(UserDb transientInstance) {
		log.debug("saving UserDb instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(UserDb persistentInstance) {
		log.debug("deleting UserDb instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public UserDb findById(java.lang.String id) {
		log.debug("getting UserDb instance with id: " + id);
		try {
			UserDb instance = (UserDb) getHibernateTemplate().get(
					"domain.Memo.UserDb", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(UserDb instance) {
		log.debug("finding UserDb instance by example");
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
		log.debug("finding UserDb instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from UserDb as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByMemoDb(Object memoDb) {
		return findByProperty(MEMO_DB, memoDb);
	}

	public List findAll() {
		log.debug("finding all UserDb instances");
		try {
			String queryString = "from UserDb";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public UserDb merge(UserDb detachedInstance) {
		log.debug("merging UserDb instance");
		try {
			UserDb result = (UserDb) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(UserDb instance) {
		log.debug("attaching dirty UserDb instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(UserDb instance) {
		log.debug("attaching clean UserDb instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static UserDbDAO getFromApplicationContext(ApplicationContext ctx) {
		return (UserDbDAO) ctx.getBean("UserDbDAO");
	}
}