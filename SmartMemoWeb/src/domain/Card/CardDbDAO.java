package domain.Card;

import java.util.List;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * CardDb entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.educube.domain.plan.CardDb
 * @author MyEclipse Persistence Tools
 */
public class CardDbDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory.getLogger(CardDbDAO.class);
	// property constants
	public static final String DBFILE = "dbfile";

	protected void initDao() {
		// do nothing
	}

	public void save(CardDb transientInstance) {
		log.debug("saving CardDb instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(CardDb persistentInstance) {
		log.debug("deleting CardDb instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public CardDb findById(java.lang.String id) {
		log.debug("getting CardDb instance with id: " + id);
		try {
			CardDb instance = (CardDb) getHibernateTemplate().get(
					"com.educube.domain.plan.CardDb", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(CardDb instance) {
		log.debug("finding CardDb instance by example");
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
		log.debug("finding CardDb instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from CardDb as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByDbfile(Object dbfile) {
		return findByProperty(DBFILE, dbfile);
	}

	public List findAll() {
		log.debug("finding all CardDb instances");
		try {
			String queryString = "from CardDb";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public CardDb merge(CardDb detachedInstance) {
		log.debug("merging CardDb instance");
		try {
			CardDb result = (CardDb) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(CardDb instance) {
		log.debug("attaching dirty CardDb instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(CardDb instance) {
		log.debug("attaching clean CardDb instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static CardDbDAO getFromApplicationContext(ApplicationContext ctx) {
		return (CardDbDAO) ctx.getBean("CardDbDAO");
	}
}