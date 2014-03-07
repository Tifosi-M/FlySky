package com.telc.domain.Emtity;

/**
 * User业务实体类
 * 
 * @author CZW
 * 
 */
public class User {
	private String image;
	public static String userID;
	
	private String userName;
	private String userPwd;
	
	private int age;
	private String sex;

	private String prof;
	private String hob;
	private String phoneNum;
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public static String getUserID() {
		return userID;
	}
	public static void setUserID(String userID) {
		User.userID = userID;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getProf() {
		return prof;
	}
	public void setProf(String prof) {
		this.prof = prof;
	}
	public String getHob() {
		return hob;
	}
	public void setHob(String hob) {
		this.hob = hob;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	


}
