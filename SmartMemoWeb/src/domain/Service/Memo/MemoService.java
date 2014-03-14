package domain.Service.Memo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import domain.Helper.RTMemoHelper;
import domain.Helper.TMMemoHelper;
import domain.Memo.Realtime;
import domain.Memo.RealtimeDAO;
import domain.Memo.Timing;
import domain.Memo.TimingDAO;
import domain.Memo.UserDb;
import domain.Memo.UserDbDAO;
import domain.SystemManagement.User;
import domain.SystemManagement.UserDAO;

public class MemoService implements IMemoService {
	private UserDAO userDao;
	private RealtimeDAO realtimeDao;
	private TimingDAO timingDao;
	private UserDbDAO userDbDao;
	private ApplicationContext ctx;

	@Override
	public boolean saveRealTimeMemo(RTMemoHelper rtHelper) {
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		userDao = UserDAO.getFromApplicationContext(ctx);
		realtimeDao = RealtimeDAO.getFromApplicationContext(ctx);
		Realtime realtime = new Realtime();
		User user = userDao.findById(Integer.parseInt(rtHelper.getUserId()));
		realtime.setStartTime(rtHelper.getStartTime());
		realtime.setLocation(rtHelper.getLocation());
		realtime.setAging(Integer.parseInt(rtHelper.getAging()));
		realtime.setContent(rtHelper.getContent());
		realtime.setUser(user);
		realtime.setPriority(Integer.parseInt(rtHelper.getPriority()));

		try {
			realtimeDao.save(realtime);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	@Override
	public boolean saveTimingMemo(TMMemoHelper tmHelper) {
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		userDao = UserDAO.getFromApplicationContext(ctx);
		timingDao = TimingDAO.getFromApplicationContext(ctx);
		User user = userDao.findById(Integer.parseInt(tmHelper.getUserId()));
		Timing timing = new Timing();
		timing.setContent(tmHelper.getContent());
		timing.setEndTime(tmHelper.getEndTime());
		timing.setLocation(tmHelper.getLocation());
		timing.setPriority(Integer.parseInt(tmHelper.getPriority()));
		timing.setStartTime(tmHelper.getStartTime());
		timing.setUser(user);
		try {
			timingDao.save(timing);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<RTMemoHelper> getRealTimeMemoByTel(String tel) {
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		realtimeDao = RealtimeDAO.getFromApplicationContext(ctx);
		List<Realtime> list_r = realtimeDao.findByTel(tel);
		List<RTMemoHelper> list_helper = new ArrayList<RTMemoHelper>();
		Iterator it = list_r.iterator();
		while (it.hasNext()) {
			RTMemoHelper rt = new RTMemoHelper();
			Realtime realtime = (Realtime) it.next();
			rt.setAging(realtime.getAging().toString());
			rt.setContent(realtime.getContent());
			rt.setId(realtime.getId().toString());
			rt.setLocation(realtime.getLocation());
			rt.setPriority(realtime.getPriority().toString());
			rt.setStartTime(realtime.getStartTime());
			rt.setUserId(realtime.getUser().getUserId().toString());
			list_helper.add(rt);
		}
		return list_helper;
	}

	@Override
	public List<TMMemoHelper> getTimingMemoByTel(String tel) {
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		timingDao = timingDao.getFromApplicationContext(ctx);
		List<Timing> list_t = timingDao.findByTel(tel);
		List<TMMemoHelper> list_helper = new ArrayList<TMMemoHelper>();
		Iterator it = list_t.iterator();
		while (it.hasNext()) {
			Timing timing = (Timing) it.next();
			TMMemoHelper tm = new TMMemoHelper();
			tm.setContent(timing.getContent());
			tm.setEndTime(timing.getEndTime());
			tm.setLocation(timing.getLocation());
			tm.setPriority(timing.getPriority().toString());
			tm.setStartTime(timing.getStartTime());
			tm.setTimingId(timing.getTimingId().toString());
			tm.setUserId(timing.getUser().getUserId().toString());
			list_helper.add(tm);
		}
		return list_helper;
	}

//	@Override
//	public boolean saveAllRealTimeMemo(List<RTMemoHelper> list_rt, String tel) {
//		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
//		realtimeDao = RealtimeDAO.getFromApplicationContext(ctx);
//		userDao = UserDAO.getFromApplicationContext(ctx);
//		// User user = (User) userDao.findByTel(tel).get(0);
//		User user = userDao.findById(Integer.parseInt(tel));
//		Iterator it_rt = list_rt.iterator();
//		try {
//			while (it_rt.hasNext()) {
//				RTMemoHelper rtHelper = new RTMemoHelper();
//				Realtime real = new Realtime();
//				rtHelper = (RTMemoHelper) it_rt.next();
//				real.setAging(Integer.parseInt(rtHelper.getAging()));
//				real.setContent(rtHelper.getContent());
//				real.setId(Integer.parseInt(rtHelper.getId()));
//				real.setLocation(rtHelper.getLocation());
//				real.setPriority(Integer.parseInt(rtHelper.getPriority()));
//				real.setStartTime(rtHelper.getStartTime());
//				real.setUser(user);
//				realtimeDao.save(real);
//			}
//			return true;
//		} catch (Exception e) {
//			return false;
//		}
//
//	}

//	@Override
//	public boolean saveAllTimingMemo(List<TMMemoHelper> list_tm, String tel) {
////		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
////		timingDao = TimingDAO.getFromApplicationContext(ctx);
////		userDao = UserDAO.getFromApplicationContext(ctx);
////		User user = userDao.findById(Integer.parseInt(tel));
////		Iterator it = list_tm.iterator();
////		try {
////			while (it.hasNext()) {
////				TMMemoHelper tmHelper = (TMMemoHelper) it.next();
////				Timing tm = new Timing();
////				tm.setContent(tmHelper.getContent());
////				tm.setEndTime(tmHelper.getEndTime());
////				tm.setLocation(tmHelper.getLocation());
////				tm.setPriority(Integer.parseInt(tmHelper.getPriority()));
////				tm.setStartTime(tmHelper.getStartTime());
////				tm.setTimingId(Integer.getInteger(tmHelper.getTimingId()));
////				tm.setUser(user);
////				timingDao.save(tm);
////			}
////			return true;
////		} catch (Exception e) {
////			return false;
////		}
//		return false;
//
//	}
//
	@Override
	public boolean updateRealTimeMemo(RTMemoHelper rtHelper) {
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		realtimeDao = RealtimeDAO.getFromApplicationContext(ctx);
		userDao = UserDAO.getFromApplicationContext(ctx);
		User user = userDao.findById(Integer.parseInt(rtHelper.getUserId()));
		Realtime real = new Realtime();
		real.setAging(Integer.parseInt(rtHelper.getAging()));
		real.setContent(rtHelper.getContent());
		real.setId(Integer.parseInt(rtHelper.getId()));
		real.setLocation(rtHelper.getLocation());
		real.setPriority(Integer.parseInt(rtHelper.getPriority()));
		real.setStartTime(rtHelper.getStartTime());
		real.setUser(user);
		try {
			realtimeDao.merge(real);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	@Override
	public boolean uploadMemoDBFile(String tel,byte[] db) {
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		userDbDao = UserDbDAO.getFromApplicationContext(ctx);
		UserDb userDb = new UserDb(tel, db);
		try {
			userDbDao.merge(userDb);
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}

	@Override
	public byte[] downloadMemoDBFile(String tel) {
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		userDbDao = UserDbDAO.getFromApplicationContext(ctx);
		UserDb userDb = userDbDao.findById(tel);
		byte[] db = userDb.getMemoDb();
		return db;
	}

	@Override
	public boolean deleteMemoDBFile(String tel) {
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		userDbDao = UserDbDAO.getFromApplicationContext(ctx);
		UserDb userDb = userDbDao.findById(tel);
		try {
			userDbDao.delete(userDb);
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}

}
