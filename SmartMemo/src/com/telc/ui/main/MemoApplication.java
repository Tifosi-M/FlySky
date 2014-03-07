package com.telc.ui.main;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.StringTokenizer;

import com.telc.data.dbDriver.CreateDB;
import com.telc.data.dbDriver.DBConstant;
import com.telc.data.dbDriver.MyDBHelp;
import android.app.Application;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

public class MemoApplication extends Application implements DBConstant {
	String path;
	CreateDB createdb;
	MemoApplication memoApplication;
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		createdb=new CreateDB();
		memoApplication=this;
		SharedPreferences sharedPreferences = getSharedPreferences("itcast",
				MODE_PRIVATE);
		if (sharedPreferences.getString("pathload", "").equals("")) {
			path = "/data/data/com.telc.smartmemo/databases/";
			createdb.createDB(path, memoApplication);
			SharedPreferences sharedPreferences2 = getSharedPreferences(
					"itcast", MODE_PRIVATE);
			Editor editor = sharedPreferences2.edit();
			editor.putString("pathload", path);
			editor.commit();
		}
	}
}
