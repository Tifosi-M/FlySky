package com.findyou.domain.Service;

import java.sql.SQLException;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.findyou.app.FindYouApplication;
import com.findyou.data.dbDriver.DataContext;
import com.findyou.data.dbDriver.IDataContext;
import com.findyou.domain.IService.IUserInfoService;
import com.findyou.domain.entity.UserInfo;

public class UserInfoService implements IUserInfoService {
	private SQLiteDatabase db;
	private IDataContext ctx=null;
	private String sql="";
	private Cursor cursor;

	public UserInfoService(SQLiteDatabase db) {
		this.db=db;
		ctx=new DataContext();
	}

	@Override
	public boolean add(UserInfo friend) {
		// TODO Auto-generated method stub
		try {
			ctx.add(friend, UserInfo.class, String.class);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public UserInfo getFriendById(String id) {
		// TODO Auto-generated method stub
		UserInfo friend;
		try {
			friend=ctx.queryById(UserInfo.class, String.class, id);
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
		sql = "select a.[rowid] as _id,* from userInfo as a";
		cursor = db.rawQuery(sql, null);
		return cursor;
	}

	@Override
	public UserInfo getMyUserInfo(String userPhone) {
		// TODO Auto-generated method stub
		UserInfo myInfo = null;
		try {
			myInfo=ctx.queryById(UserInfo.class, String.class,userPhone);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return myInfo;
	}

	@Override
	public void setMyUserInfo(UserInfo user) {
		// TODO Auto-generated method stub
		try {
			ctx.add(user, UserInfo.class, String.class);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
