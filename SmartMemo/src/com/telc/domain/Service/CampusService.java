package com.telc.domain.Service;

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
}
