package com.findyou.domain.IService;

import com.findyou.domain.entity.Friends;

import android.database.Cursor;

public interface IFriendsService {

	public boolean add(Friends friends);
	public Friends getFriendById(String id);
	
}
