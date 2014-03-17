package com.findyou.domain.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName="userInfo")
public class UserInfo {

//	private static final long serialVersionUID = 7874823823497497357L;
			
	public UserInfo(){
		userId=0;
		userName="";
		userSex="";
		userMail="";
		userQQ="";
		userHobby="";
		userCity="";
		userNameFirstAlpha="";
	}
	
	@DatabaseField(generatedId=true)
	private int userId;
	
	@DatabaseField(canBeNull=false)
	private String userName;
	
	@DatabaseField(canBeNull=false)
	private String userSex;
	
	@DatabaseField(canBeNull=false)
	private String userMail;
	
	@DatabaseField(canBeNull=false)
	private String userQQ;
	
	@DatabaseField(canBeNull=false)
	private String userHobby;
	
	@DatabaseField(canBeNull=false)
	private String userCity;
	
	@DatabaseField(canBeNull=false)
	private String userNameFirstAlpha;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserSex() {
		return userSex;
	}

	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}

	public String getUserMail() {
		return userMail;
	}

	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}

	public String getUserQQ() {
		return userQQ;
	}

	public void setUserQQ(String userQQ) {
		this.userQQ = userQQ;
	}

	public String getUserHobby() {
		return userHobby;
	}

	public void setUserHobby(String userHobby) {
		this.userHobby = userHobby;
	}

	public String getUserCity() {
		return userCity;
	}

	public void setUserCity(String userCity) {
		this.userCity = userCity;
	}

	public String getUserNameFirstAlpha() {
		return userNameFirstAlpha;
	}

	public void setUserNameFirstAlpha(String userNameFirstAlpha) {
		this.userNameFirstAlpha = userNameFirstAlpha;
	}

	
	
	
}
