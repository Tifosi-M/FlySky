package com.findyou.domain.Service;

import java.sql.SQLException;

import android.database.sqlite.SQLiteDatabase;
import com.findyou.data.dbDriver.DataContext;
import com.findyou.data.dbDriver.IDataContext;
import com.findyou.domain.IService.IFriendsService;
import com.findyou.domain.entity.Friends;

public class FriendsService implements IFriendsService {

	private IDataContext ctx=null;

	public FriendsService(SQLiteDatabase db) {
		ctx=new DataContext();
	}

	@Override
	public boolean add(Friends friends) {
		// TODO Auto-generated method stub
		try {
			ctx.add(friends, Friends.class, String.class);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Friends getFriendById(String id) {
		// TODO Auto-generated method stub
		Friends friend;
		try {
			friend=ctx.queryById(Friends.class, String.class, id);
			return friend;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
