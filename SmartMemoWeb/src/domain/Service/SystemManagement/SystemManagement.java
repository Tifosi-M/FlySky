package domain.Service.SystemManagement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import domain.Helper.UserHelper;
import domain.SystemManagement.Account;
import domain.SystemManagement.AccountDAO;
import domain.SystemManagement.User;
import domain.SystemManagement.UserDAO;

public class SystemManagement implements ISystemManagement {
	private UserDAO userDao;
	private AccountDAO accountDao;
	private ApplicationContext ctx;

	@Override
	public boolean saveUser(UserHelper userHelper) {
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		userDao = UserDAO.getFromApplicationContext(ctx);
		User user = new User();
		user.setUserId(Integer.parseInt(userHelper.getUserId()));
		user.setTel(userHelper.getTel());
		user.setPwd(userHelper.getPwd());
		user.setNickname(userHelper.getNickname());
		user.setAge(Integer.parseInt(userHelper.getAge()));
		user.setSex(userHelper.getSex());
		try {
			userDao.save(user);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	@Override
	public boolean updateUser(UserHelper userHelper) {
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		userDao = UserDAO.getFromApplicationContext(ctx);
		User user = new User();
		user.setUserId(Integer.parseInt(userHelper.getUserId()));
		user.setTel(userHelper.getTel());
		user.setPwd(userHelper.getPwd());
		user.setNickname(userHelper.getNickname());
		user.setAge(Integer.parseInt(userHelper.getAge()));
		user.setSex(userHelper.getSex());
		try {
			userDao.update(user);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public UserHelper getUserByTel(String tel) {
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		userDao = UserDAO.getFromApplicationContext(ctx);
		List<User> list = new ArrayList<User>();
		list = userDao.findByTel(tel);
		User user = list.get(0);

		UserHelper userHelper = null;
		if (user != null) {
			userHelper = new UserHelper();
			userHelper.setUserId(user.getUserId().toString());
			userHelper.setTel(user.getTel());
			userHelper.setPwd(user.getPwd());
			userHelper.setNickname(user.getNickname());
			userHelper.setAge(user.getAge().toString());
			userHelper.setSex(user.getSex());
		}
		return userHelper;
	}

	@Override
	public UserHelper getUserById(String id) {
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		userDao = UserDAO.getFromApplicationContext(ctx);
		User user = userDao.findById(Integer.parseInt(id));
		UserHelper userHelper = null;
		if (user != null) {
			userHelper = new UserHelper();
			userHelper.setUserId(user.getUserId().toString());
			userHelper.setTel(user.getTel());
			userHelper.setPwd(user.getPwd());
			userHelper.setNickname(user.getNickname());
			userHelper.setAge(user.getAge().toString());
			userHelper.setSex(user.getSex());
		}
		return userHelper;
	}

	@Override
	public boolean login(String tel, String pwd) {
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		accountDao = AccountDAO.getFromApplicationContext(ctx);
		Account account = accountDao.findById(tel);
		if(account==null){
			return false;
		}
		if(account.getPwd().equals(pwd))
			return true;
		else {
			return false;
		}
	}

	@Override
	public boolean register(String tel, String pwd) {
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		accountDao = AccountDAO.getFromApplicationContext(ctx);
		Account account = new Account();
		account.setTel(tel);
		account.setPwd(pwd);
		try {
			accountDao.save(account);
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}
	
}
