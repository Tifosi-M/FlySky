package com.findyou.domain.Service;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.findyou.domain.IService.INewsService;

public class NewsService implements INewsService{

	private SQLiteDatabase db;
	private Cursor cursor;
	private String sql;

	public NewsService(SQLiteDatabase db) {
		this.db = db;
	}
	
	@Override
	public Cursor FindNews() {
		// TODO Auto-generated method stub
		sql = "select a.[rowid] as _id,* from News as a";
		cursor = db.rawQuery(sql, null);
		return cursor;
	}

	@Override
	public Cursor FindNewsByFriendId(String FriendId) {
		// TODO Auto-generated method stub
		return null;
	}

}
