package com.findyou.domain.Service;

import java.sql.SQLException;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.findyou.data.dbDriver.DataContext;
import com.findyou.data.dbDriver.IDataContext;
import com.findyou.domain.IService.IFriendService;
import com.findyou.domain.entity.Friend;

public class FriendService implements IFriendService {
	private SQLiteDatabase db;
	private IDataContext ctx=null;
	private String sql="";
	private Cursor cursor;
	
	public FriendService(SQLiteDatabase db) {
		this.db=db;
		ctx=new DataContext();
	}

	@Override
	public boolean add(Friend friend) {
		// TODO Auto-generated method stub
		try {
			ctx.add(friend, Friend.class, String.class);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Friend getFriendById(String id) {
		// TODO Auto-generated method stub
		Friend friend;
		try {
			friend=ctx.queryById(Friend.class, String.class, id);
			return friend;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Cursor findFriends() {
		// TODO Auto-generated method stub
		sql = "select a.[rowid] as _id,* from Friend as a";
		cursor = db.rawQuery(sql, null);
		return cursor;
	}

}
