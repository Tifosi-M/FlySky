package com.findyou.domain.Service;

import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.IBinder;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.map.LocationData;
import com.baidu.mapapi.search.MKAddrInfo;
import com.baidu.mapapi.search.MKBusLineResult;
import com.baidu.mapapi.search.MKDrivingRouteResult;
import com.baidu.mapapi.search.MKGeocoderAddressComponent;
import com.baidu.mapapi.search.MKPoiResult;
import com.baidu.mapapi.search.MKSearch;
import com.baidu.mapapi.search.MKSearchListener;
import com.baidu.mapapi.search.MKShareUrlResult;
import com.baidu.mapapi.search.MKSuggestionResult;
import com.baidu.mapapi.search.MKTransitRouteResult;
import com.baidu.mapapi.search.MKWalkingRouteResult;
import com.baidu.platform.comapi.basestruct.GeoPoint;
import com.findyou.app.FindYouApplication;

public class locationService extends Service {
	
	//定位相关
	private LocationClient mLocClient;
	private LocationData locData =  null;
	private FindYouApplication app;
	private MKSearch mSearch = null;

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
//		Toast.makeText(getApplicationContext(), "service 被创建", Toast.LENGTH_SHORT).show();
		
		app = (FindYouApplication)this.getApplication();
        if (app.mBMapManager == null) {
            app.mBMapManager = new BMapManager(getApplicationContext());
            app.mBMapManager.init(FindYouApplication.strKey,null);
        }
		
		//定位初始化
		mLocClient = new LocationClient(getApplicationContext());
		locData = new LocationData();
		mainLocationListenner mainListener = new mainLocationListenner();
		mLocClient.registerLocationListener(mainListener);
		LocationClientOption option = new LocationClientOption();
		option.setOpenGps(true);// 打开gps
		option.setCoorType("bd09ll"); // 设置坐标类型
		option.setScanSpan(5000);//设置定位时间
		mLocClient.setLocOption(option);
		mLocClient.start();
		
		//初始化搜索模块  注册搜索事件监听
		mSearch = new MKSearch();
		mSearch.init(app.mBMapManager, new MySearchListenner());

		super.onCreate();
		
	}

	public class mainLocationListenner implements BDLocationListener {

		@Override
		public void onReceiveLocation(BDLocation location) {
			
			if (location == null)
				return;
			
//			Toast.makeText(getApplicationContext(), "i am service ", Toast.LENGTH_LONG).show();
			
			
			locData.longitude = location.getLongitude();
			locData.latitude = location.getLatitude();
			
			
			mSearch.reverseGeocode(new GeoPoint((int) (location.getLatitude() * 1e6),(int) (location.getLongitude() * 1e6)));
			
		}

		@Override
		public void onReceivePoi(BDLocation arg0) {
			// TODO Auto-generated method stub
			
		}
	}
	
	
		
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		
		mLocClient.stop();
		super.onDestroy();
		
//		locationServiceInfoTran.canBeDestroy = true;
//		if(locationServiceInfoTran.canBeDestroy){
////			stopService(new Intent("com.telc.domain.Service.locationService"));
//			super.onDestroy();
//		}else{
//			Intent localIntent = new Intent();
//			localIntent.setAction("com.telc.domain.Service.locationService");
//			this.startService(localIntent);
//		}
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub

		mLocClient.start();
//		flags =  START_STICKY;
//		Toast.makeText(getApplicationContext(), "start servet", Toast.LENGTH_SHORT).show();
		
		return super.onStartCommand(intent, flags, startId);
	}

	//搜索
		public class MySearchListenner implements MKSearchListener{

			@Override
			public void onGetAddrResult(MKAddrInfo result, int iError) {
				
				
				
				MKGeocoderAddressComponent kk = result.addressComponents;
				String mylocationplcaeString = kk.city + kk.district + kk.street + kk.streetNumber;
//				Toast.makeText(getApplicationContext(), mylocationplcaeString, Toast.LENGTH_SHORT).show();
				
				 mLocClient.stop();
				
				 SharedPreferences MyPreferences = getSharedPreferences("LOCATION_INFO", Activity.MODE_PRIVATE);
			     SharedPreferences.Editor editor = MyPreferences.edit();
			     editor.putString("Location_name", mylocationplcaeString);
			     editor.putFloat("latitude", (float) locData.latitude);
			     editor.putFloat("longitude", (float) locData.longitude);
			     editor.commit();
			     
			     locationService.this.stopSelf();
			}

			@Override
			public void onGetBusDetailResult(MKBusLineResult arg0, int arg1) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onGetDrivingRouteResult(MKDrivingRouteResult arg0, int arg1) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onGetPoiDetailSearchResult(int arg0, int arg1) {
			}

			@Override
			public void onGetPoiResult(MKPoiResult arg0, int arg1, int arg2) {
				//返回 poi 搜索结果
			}

			@Override
			public void onGetShareUrlResult(MKShareUrlResult arg0, int arg1,
					int arg2) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onGetSuggestionResult(MKSuggestionResult arg0, int arg1) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onGetTransitRouteResult(MKTransitRouteResult arg0, int arg1) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onGetWalkingRouteResult(MKWalkingRouteResult arg0, int arg1) {
				// TODO Auto-generated method stub
			}
		}
	
	
}
