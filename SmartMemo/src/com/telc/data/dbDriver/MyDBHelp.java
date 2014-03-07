package com.telc.data.dbDriver;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
/**
 * 数据库创建
 * @author CZW
 *
 */
public class MyDBHelp extends SQLiteOpenHelper implements DBConstant{

	public MyDBHelp(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
//		Log.v("MyDBHelp", "数据库创建");
//		//用户信息表
//		String sql_law = "create table "
//				+ USER_MA
//				+ "(userID text, userName text, phoneNum text,userPwd text, age integer,sex text,hob text,prof text,image text);";
//		db.execSQL(sql_law);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}
}
