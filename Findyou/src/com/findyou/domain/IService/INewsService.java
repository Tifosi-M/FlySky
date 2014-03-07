package com.findyou.domain.IService;

import android.database.Cursor;

public interface INewsService {
	public Cursor FindNews();

	public Cursor FindNewsByFriendId(String FriendId);
}
