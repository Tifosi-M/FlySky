package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import domain.Helper.UserHelper;
import domain.Service.SystemManagement.SystemManagement;

public class TestSystemManagement extends SystemManagement {
	private UserHelper userHelper;
	@Before
	public void init(){
		userHelper = new UserHelper();
		userHelper.setUserId("2");
		userHelper.setTel("123333332");
		userHelper.setSex("M");
		userHelper.setPwd("123");
		userHelper.setNickname("shumi");
		userHelper.setAge("19");
	}
	@Ignore
	public void testSaveUser() {
		
	}
	@Ignore
	public void testUpdate(){
		userHelper.setNickname("shumifans");
		updateUser(userHelper);
	}
	@Ignore
	public void testgetUserById(){
		UserHelper userHelper = getUserById("112");
		System.out.println();
	}
	@Ignore
	public void testgetUserByTel(){
		UserHelper userHelper = getUserByTel("123");
		System.out.println();
	}
	@Test
	public void testLogin(){
		System.out.println(login("123","123"));
	}
	

}
