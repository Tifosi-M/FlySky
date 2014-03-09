package com.findyou.domain.Service;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.findyou.data.dbDriver.DataContext;
import com.findyou.domain.IService.INewsService;

public class NewsService implements INewsService{

	private DataContext database;
	private SQLiteDatabase db;
	private Cursor cursor;
	private String sql;

	public NewsService(SQLiteDatabase db) {
		database=new DataContext();
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
