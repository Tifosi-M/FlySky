package com.businesscard.domain.Emtity;

import java.util.Hashtable;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

public class UserCard implements KvmSerializable{
	private String userId;
	private String userPhone;
	private String userName;
	private String userNameFirstAlpha;
	private String userTel;
	private String userEmail;
	private String userProfessional;
	private String userAddress;
	private String userImage;
	private String userQQ;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserNameFirstAlpha() {
		return userNameFirstAlpha;
	}
	public void setUserNameFirstAlpha(String userNameFirstAlpha) {
		this.userNameFirstAlpha = userNameFirstAlpha;
	}
	public String getUserTel() {
		return userTel;
	}
	public void setUserTel(String userTel) {
		this.userTel = userTel;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserProfessional() {
		return userProfessional;
	}
	public void setUserProfessional(String userProfessional) {
		this.userProfessional = userProfessional;
	}
	public String getUserAddress() {
		return userAddress;
	}
	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}
	public String getImage() {
		return userImage;
	}
	public void setImage(String userImage) {
		this.userImage = userImage;
	}
	public String getUserQQ() {
		return userQQ;
	}
	public void setUserQQ(String userQQ) {
		this.userQQ = userQQ;
	}
	@Override
	public Object getProperty(int arg0) {
		// TODO Auto-generated method stub
		Object object=null;
		switch (arg0) {
		case 0:
			object=this.userId;
			break;
		case 1:
			object = this.userPhone;
			break;
		case 2:
			object = this.userName;
			break;
		case 3:
			object = this.userNameFirstAlpha;
			break;
		case 4:
			object = this.userTel;
			break;
		case 5:
			object = this.userEmail;
			break;
		case 6:
			object = this.userProfessional;
			break;
		case 7:
			object = this.userAddress;
			break;
		case 8:
			object = this.userImage;
			break;
		case 9:
			object = this.userQQ;
			break;
		}
		return object;
	}
	@Override
	public int getPropertyCount() {
		// TODO Auto-generated method stub
		return 10;
	}
	@Override
	public void getPropertyInfo(int arg0, Hashtable arg1, PropertyInfo arg2) {
		// TODO Auto-generated method stub
		switch (arg0) {
		case 0:
			arg2.type = PropertyInfo.STRING_CLASS;
			arg2.name = "userId";
			break;
		case 1:
			arg2.type = PropertyInfo.STRING_CLASS;
			arg2.name = "userPhone";
			break;
		case 2:
			arg2.type = PropertyInfo.STRING_CLASS;
			arg2.name = "userName";
			break;
		case 3:
			arg2.type = PropertyInfo.STRING_CLASS;
			arg2.name = "userNameFirstAlpha";
			break;
		case 4:
			arg2.type = PropertyInfo.STRING_CLASS;
			arg2.name = "userTel";
			break;
		case 5:
			arg2.type = PropertyInfo.STRING_CLASS;
			arg2.name = "userEmail";
			break;
		case 6:
			arg2.type = PropertyInfo.STRING_CLASS;
			arg2.name = "userProfessional";
			break;
		case 7:
			arg2.type = PropertyInfo.STRING_CLASS;
			arg2.name = "userAddress";
			break;
		case 8:
			arg2.type = PropertyInfo.STRING_CLASS;
			arg2.name = "userImage";
			break;
		case 9:
			arg2.type = PropertyInfo.STRING_CLASS;
			arg2.name = "userQQ";
			break;
		default:
			break;
		}
	}
	@Override
	public void setProperty(int arg0, Object arg1) {
		// TODO Auto-generated method stub
		switch (arg0) {
		case 0:
			userId = arg1.toString();
			break;
		case 1:
			userPhone = arg1.toString();
			break;
		case 2:
			userName = arg1.toString();
			break;
		case 3:
			userNameFirstAlpha = arg1.toString();
			break;
		case 4:
			userTel = arg1.toString();
			break;
		case 5:
			userEmail = arg1.toString();
			break;
		case 6:
			userProfessional = arg1.toString();
			break;
		case 7:
			userAddress = arg1.toString();
			break;
		case 8:
			userImage = arg1.toString();
			break;
		case 9:
			userQQ = arg1.toString();
			break;
		default:
			break;
		}
	}
}
