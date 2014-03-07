package com.telc.domain.Service;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.telc.domain.Emtity.Periodic;
import com.telc.domain.IService.IPeriodicService;

/**
 * 周期性提醒数据库操作
 * 
 * @author evil
 * 
 */
public class PeriodicService implements IPeriodicService {

	private SQLiteDatabase db;
	private Cursor cursor;

	public PeriodicService(SQLiteDatabase db) {
		this.db = db;
	}

	@Override
	public List<Periodic> getPeriodicByUserID(String user_id) {
		// TODO Auto-generated method stub
		List<Periodic> periodic_list = new ArrayList<Periodic>();
		String sql = "select a.[rowid] as _id,* from PERIODIC as a where user_id='"
				+ user_id + "'";
		cursor = db.rawQuery(sql, null);
		if (cursor.moveToFirst() == false) {
			return null;
		} else {
			for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor
					.moveToNext()) {
				Periodic periodic = new Periodic();
				int periodicIDColumn = cursor.getColumnIndex("periodic_id");
				String periodic_id = cursor.getString(periodicIDColumn);
				periodic.setPeriodic_id(periodic_id);

				int periodColumn = cursor.getColumnIndex("period");
				String period = cursor.getString(periodColumn);
				periodic.setPeriod(period);

				int periodDetailColumn = cursor.getColumnIndex("period_detail");
				String period_detail = cursor.getString(periodDetailColumn);
				periodic.setPeriod_detail(period_detail);

				periodic.setUser_id(user_id);

				int contentColumn = cursor.getColumnIndex("content");
				String content = cursor.getString(contentColumn);
				periodic.setContent(content);

				int priorityColumn = cursor.getColumnIndex("priority");
				int priority = cursor.getInt(priorityColumn);
				periodic.setPriority(priority);

				int infinishColumn = cursor.getColumnIndex("isfinish");
				int infinish = cursor.getInt(infinishColumn);
				periodic.setIsfinish(infinish);

				int start_timeColumn = cursor.getColumnIndex("start_time");
				String start_time = cursor.getString(start_timeColumn);
				periodic.setStart_time(start_time);
				
				int end_timeColumn = cursor.getColumnIndex("end_time");
				String end_time = cursor.getString(end_timeColumn);
				periodic.setEnd_time(end_time);

				periodic_list.add(periodic);
			}
			return periodic_list;
		}
	}

	@Override
	public boolean addPeriodic(Periodic periodic) {
		// TODO Auto-generated method stub
		String periodic_id = periodic.getPeriodic_id();
		String period = periodic.getPeriod();
		String period_detail = periodic.getPeriod_detail();
		String content = periodic.getContent();
		String user_id = periodic.getUser_id();
		int priority = periodic.getPriority();
		int isfinish = periodic.getIsfinish();
		String start_time = periodic.getStart_time();
		String end_time = periodic.getEnd_time();
		String sql = "insert into PERIODIC (periodic_id,period,period_detail,user_id,content,priority,isfinish,start_time,end_time) values ('"
				+ periodic_id
				+ "','"
				+ period
				+ "','"
				+ period_detail
				+ "','"
				+ user_id
				+ "','"
				+ content
				+ "','"
				+ priority
				+ "','"
				+ isfinish + "','" + start_time + "','"+end_time+"')";

		db.execSQL(sql);
		return true;
	}

	@Override
	public boolean removePeriodic(String periodic_id) {
		// TODO Auto-generated method stub
		String sql = "delete from PERIODIC where periodic_id='" + periodic_id
				+ "'";
		db.execSQL(sql);
		return true;
	}

	@Override
	public Periodic findPeriodicById(String id) {
		// TODO Auto-generated method stub
		String sql = "select a.[rowid] as _id,* from PERIODIC as a where periodic_id='"
				+ id + "'";
		cursor = db.rawQuery(sql, null);
		Periodic periodic = new Periodic();
		if (cursor.moveToFirst() == false) {
			return null;
		} else {
			for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor
					.moveToNext()) {

				int periodicIDColumn = cursor.getColumnIndex("periodic_id");
				String periodic_id = cursor.getString(periodicIDColumn);
				periodic.setPeriodic_id(periodic_id);

				int periodColumn = cursor.getColumnIndex("period");
				String period = cursor.getString(periodColumn);
				periodic.setPeriod(period);

				int periodDetailColumn = cursor.getColumnIndex("period_detail");
				String period_detail = cursor.getString(periodDetailColumn);
				periodic.setPeriod_detail(period_detail);

				int user_idColumn = cursor.getColumnIndex("user_id");
				String user_id = cursor.getString(user_idColumn);
				periodic.setUser_id(user_id);

				int contentColumn = cursor.getColumnIndex("content");
				String content = cursor.getString(contentColumn);
				periodic.setContent(content);

				int priorityColumn = cursor.getColumnIndex("priority");
				int priority = cursor.getInt(priorityColumn);
				periodic.setPriority(priority);

				int infinishColumn = cursor.getColumnIndex("isfinish");
				int infinish = cursor.getInt(infinishColumn);
				periodic.setIsfinish(infinish);
				
				int starttimeColumn = cursor.getColumnIndex("start_time");
				String starttime = cursor.getString(starttimeColumn);
				periodic.setStart_time(starttime);
				
				int endtimeColunm = cursor.getColumnIndex("end_time");
				String end_time = cursor.getString(starttimeColumn);
				periodic.setEnd_time(end_time);

			}
			return periodic;
		}

	}

	//修改备忘录内容
	@Override
	public boolean updatePeriodic(Periodic periodic) {
		// TODO Auto-generated method stub
		String sql = "update PERIODIC set period='"+periodic.getPeriod()+"',period_detail='"+periodic.getPeriod_detail()+"',content='"+periodic.getContent()+"',priority='"+periodic.getPriority()+"',end_time='"+periodic.getEnd_time()+"' where periodic_id='"+periodic.getPeriodic_id()+"'";
		db.execSQL(sql);
		return true;
	}

	//标志已完成
	@Override
	public boolean updateIsfinish(String periodic_id) {
		// TODO Auto-generated method stub
		String sql = "update PERIODIC set isfinish="+1+" where periodic_id='"+periodic_id+"'";
		db.execSQL(sql);
		return true;
	}

}
