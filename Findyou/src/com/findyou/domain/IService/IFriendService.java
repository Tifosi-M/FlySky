package com.findyou.domain.IService;

import com.findyou.domain.entity.Friend;

import android.database.Cursor;

public interface IFriendService {

	public boolean add(Friend friends);
	public Friend getFriendById(String id);
	
	public Cursor findFriends();

	
}
