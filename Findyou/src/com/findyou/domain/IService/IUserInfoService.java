package com.findyou.domain.IService;

import com.findyou.domain.entity.UserInfo;

import android.database.Cursor;

public interface IUserInfoService {

	public boolean add(UserInfo friends);//增加朋友
	public UserInfo getFriendById(String id);//查找朋友信息
	public UserInfo getMyUserInfo(String userPhone);//查找我的信息
	public void setMyUserInfo(UserInfo user);
	public Cursor findFriends();

	
}
