package com.findyou.app;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.MKGeneralListener;
import com.baidu.mapapi.map.MKEvent;
import com.findyou.data.dbDriver.DataHelper;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.widget.Toast;

public class FindYouApplication extends  Application{
	
	// 数据库助手
	public static DataHelper DATAHELPER;

	// 数据库名
	public static String DATAFILENAME = "findyou.db";
	
    private static FindYouApplication mInstance = null;
    public boolean m_bKeyRight = true;
    public BMapManager mBMapManager = null;

    /**
     * wwb-社交名片debug： pDGDqKMr1KzNnz8RCveGnTSP
     * wwb-社交名片发布：yr9ulLsBASVistGaNQQH2zUi
     * 
     * zyc-社交名片debug：9wbm32lD9NKtV7vNttUYZMKu
     * zyc-社交名片发布：AbUlyP3e9eDcfUGEb7DAvGO5
     * 
     * flysky.keystore  发布版  Z1n2a6wHwseSMvHeOy9LfkAP 
     */

    public static final String strKey = "Z1n2a6wHwseSMvHeOy9LfkAP";	

	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		
		SharedPreferences sharedPreferences = getSharedPreferences("database_init",
				MODE_PRIVATE);
		if (sharedPreferences.getString("database_init", "").equals("")){
			// 初始化全局变量
			DATAHELPER = new DataHelper(getApplicationContext(), DATAFILENAME);

			SharedPreferences sharedPreferences2 = getSharedPreferences(
					"itcast", MODE_PRIVATE);
			Editor editor = sharedPreferences2.edit();
			editor.putString("database_init", "true");
			editor.commit();
		}
		
		mInstance = this;
		initEngineManager(this);
	}
	
	public void initEngineManager(Context context) {
	    if (mBMapManager == null) {
	        mBMapManager = new BMapManager(context);
	    }
	
	    if (!mBMapManager.init(strKey,new MyGeneralListener())) {
	        Toast.makeText(FindYouApplication.getInstance().getApplicationContext(), 
	                "BMapManager  初始化错误!", Toast.LENGTH_LONG).show();
	    }
	}
	
	public static FindYouApplication getInstance() {
		return mInstance;
	}
	
	
	// 常用事件监听，用来处理通常的网络错误，授权验证错误等
	public static class MyGeneralListener implements MKGeneralListener {
	    
	    @Override
	    public void onGetNetworkState(int iError) {
	        if (iError == MKEvent.ERROR_NETWORK_CONNECT) {
	            Toast.makeText(FindYouApplication.getInstance().getApplicationContext(), "网络异常，请检查！",
	                Toast.LENGTH_LONG).show();
	        }
	        else if (iError == MKEvent.ERROR_NETWORK_DATA) {
	            Toast.makeText(FindYouApplication.getInstance().getApplicationContext(), "输入正确的检索条件！",
	                    Toast.LENGTH_LONG).show();
	        }
	        // ...
	    }
	
	    @Override
	    public void onGetPermissionState(int iError) {
	    	//非零值表示key验证未通过
	        if (iError != 0) {
	            //授权Key错误：
	            Toast.makeText(FindYouApplication.getInstance().getApplicationContext(), 
	                    "请在 FindYouApplication.java文件输入正确的授权Key,并检查您的网络连接是否正常！error: "+iError, Toast.LENGTH_LONG).show();
	            FindYouApplication.getInstance().m_bKeyRight = false;
	        }
	        else{
	        	FindYouApplication.getInstance().m_bKeyRight = true;
	        	Toast.makeText(FindYouApplication.getInstance().getApplicationContext(), 
	                    "key认证成功", Toast.LENGTH_LONG).show();
	        }
	    }
	}
	
}
