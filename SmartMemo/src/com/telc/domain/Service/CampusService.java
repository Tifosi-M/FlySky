package com.telc.domain.Service;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.telc.domain.Emtity.Campus;
import com.telc.domain.IService.ICampusService;

public class CampusService implements ICampusService {
	private SQLiteDatabase db;
	private Cursor cursor;
   private String sql;
   Campus campus;
	public CampusService(SQLiteDatabase db) {
		this.db = db;
	}
	@Override
	public Campus getCampusById(String id) {
		// TODO Auto-generated method stub
		sql = "select a.[rowid] as _id,* from CAMPUS as a where campusid='"+id+"'";
		cursor = db.rawQuery(sql, null);
		if (cursor.moveToFirst() == false) {
			return null;
		} else {
			for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor
					.moveToNext()) {
				campus=new Campus();
				int campusidColumn = cursor.getColumnIndex("campusid");
				String campusid = cursor.getString(campusidColumn);
				campus.setCampusid(campusid);
				int campusnameColumn = cursor.getColumnIndex("campusname");
				String campusname = cursor.getString(campusnameColumn);
				campus.setCampusname(campusname);
				int campusbyColumn = cursor.getColumnIndex("campusby");
				String campusby = cursor.getString(campusbyColumn);
				campus.setCampusby(campusby);
				int campustimeColumn = cursor.getColumnIndex("campustime");
				String campustime = cursor.getString(campustimeColumn);
				campus.setCampustime(campustime);
				int campusstateColumn = cursor.getColumnIndex("campusstate");
				String campusstate = cursor.getString(campusstateColumn);
				campus.setCampusstate(campusstate);
				int campusaddressColumn = cursor.getColumnIndex("campusaddress");
				String campusaddress = cursor.getString(campusaddressColumn);
				campus.setCampusaddress(campusaddress);
				int campuscontentColumn = cursor.getColumnIndex("campuscontent");
				String campuscontent = cursor.getString(campuscontentColumn);
				campus.setCampuscontent(campuscontent);
			}
			return campus;
		}
	}
	public Cursor findCampus() {
		sql = "select a.[rowid] as _id,* from CAMPUS as a";
		cursor = db.rawQuery(sql, null);
		return cursor;

	}
	@Override
	public boolean updateCampusStatus(String campusId,boolean flag) {
		// TODO Auto-generated method stub
		if(true==flag){
			sql="update CAMPUS set campusstate='Y' where campusid='"+campusId+"'";
		}else{
			sql="update CAMPUS set campusstate='N' where campusid='"+campusId+"'";
		}
		db.execSQL(sql);
		return true;
	}
	@Override
	public List<Campus> getAllCampus() {
		// TODO Auto-generated method stub
		List<Campus> mList = new ArrayList<Campus>();
		sql = "select a.[rowid] as _id,* from CAMPUS as a";
		cursor = db.rawQuery(sql, null);
		for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor
				.moveToNext()) {
			campus=new Campus();
			int campusidColumn = cursor.getColumnIndex("campusid");
			String campusid = cursor.getString(campusidColumn);
			campus.setCampusid(campusid);
			int campusnameColumn = cursor.getColumnIndex("campusname");
			String campusname = cursor.getString(campusnameColumn);
			campus.setCampusname(campusname);
			int campusbyColumn = cursor.getColumnIndex("campusby");
			String campusby = cursor.getString(campusbyColumn);
			campus.setCampusby(campusby);
			int campustimeColumn = cursor.getColumnIndex("campustime");
			String campustime = cursor.getString(campustimeColumn);
			campus.setCampustime(campustime);
			int campusstateColumn = cursor.getColumnIndex("campusstate");
			String campusstate = cursor.getString(campusstateColumn);
			campus.setCampusstate(campusstate);
			int campusaddressColumn = cursor.getColumnIndex("campusaddress");
			String campusaddress = cursor.getString(campusaddressColumn);
			campus.setCampusaddress(campusaddress);
			int campuscontentColumn = cursor.getColumnIndex("campuscontent");
			String campuscontent = cursor.getString(campuscontentColumn);
			campus.setCampuscontent(campuscontent);
			mList.add(campus);
		}
		return mList;
	}
}
