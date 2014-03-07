package com.businesscard.ui.main.systemManagement;

import com.baidu.frontia.Frontia;
import com.baidu.frontia.FrontiaApplication;
import com.businesscard.data.dbDriver.CreateDB;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class BusinessCardApplication extends FrontiaApplication {
	@SuppressLint("SdCardPath")
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
	
		
		SharedPreferences sharedPreferences = getSharedPreferences("itcast",
				MODE_PRIVATE);
		if (sharedPreferences.getString("pathload", "").equals(""))
		{
			String path = "/data/data/com.businesscard.mobile/databases/";
			new CreateDB(path,BusinessCardApplication.this);
			SharedPreferences sharedPreferences2 = getSharedPreferences(
					"itcast", MODE_PRIVATE);
			Editor editor = sharedPreferences2.edit();
			editor.putString("pathload", path);
			editor.commit();		
		}
	}
}
