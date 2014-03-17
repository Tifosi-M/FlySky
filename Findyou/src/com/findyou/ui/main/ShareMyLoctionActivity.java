package com.findyou.ui.main;

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
import com.findyou.R;
import com.findyou.app.FindYouApplication;
import com.findyou.domain.Service.MyLocationService;
import com.findyou.model.LocationInfo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ShareMyLoctionActivity extends Activity{
	
	private String categoryString = null;
	private String mylocationplcaeString = null;
	private MyLocationService mLocationService=null;
	private LocationClient mLocationClient;
	private LocationData locationData = null;
	private MKSearch mSearch = null;
	private FindYouApplication app;
	private LocationInfo mLocation;
	private ArrayAdapter<String> categoryAdapter= null;
	
	private Button sharemylocation_publishButton;
	private TextView sharemylocation_placetTextView;
	private Spinner sharemylocation_categorySpinner;
	private EditText sharemylocation_shareinfoEditText;
	
	private static final String []category = {"心情","美食"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	
		 app = (FindYouApplication)this.getApplication();
	        if (app.mBMapManager == null) {
	            app.mBMapManager = new BMapManager(getApplicationContext());
	            app.mBMapManager.init(FindYouApplication.strKey,null);
	        }
		
		setContentView(R.layout.activity_share_mylocation);

		init();

		//定位初始化
		mLocationClient = new LocationClient(getApplicationContext());
		locationData = new LocationData();
		MyLocationListenner myListenner = new MyLocationListenner();
		mLocationClient.registerLocationListener(myListenner);
		LocationClientOption option = new LocationClientOption();
		option.setOpenGps(true);
		option.setScanSpan(5000);
		mLocationClient.setLocOption(option);
		mLocationClient.start();
		
		//初始化搜索模块  注册搜索事件监听
		mSearch = new MKSearch();
		mSearch.init(app.mBMapManager, new MySearchListenner());
		
		sharemylocation_publishButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String shareinfoString =  sharemylocation_shareinfoEditText.getText().toString().trim();
				if(shareinfoString.equals("") || shareinfoString == null){
					Toast.makeText(getApplicationContext(), "亲，说点什么吧！（分享内容不能为空哦！）", Toast.LENGTH_SHORT).show();
					return;
				}
				//在这里 将获取到的位置  还有经纬度  还有分享的内容 类别 发布到服务器
				if(mLocationService.shareMyLocation(mLocation.getLatitude(), mLocation.getLontitude(), categoryString, shareinfoString)){
					Toast.makeText(ShareMyLoctionActivity.this, "发布成功！", Toast.LENGTH_SHORT).show();
					finish();
				}else{
					Toast.makeText(ShareMyLoctionActivity.this, "发布失败！", Toast.LENGTH_SHORT).show();
				}
			}
		});
		
	}
	
	//初始化各控件
	private void init() {
		mLocationService=new MyLocationService();
		sharemylocation_publishButton = (Button) findViewById(R.id.bt_sharemylocation_publish);
		
		mLocation=new LocationInfo();
		mLocation.setName(app.userName);
		sharemylocation_placetTextView = (TextView)findViewById(R.id.tv_sharemylocation_place);
		
		sharemylocation_categorySpinner =  (Spinner) findViewById(R.id.sp_sharemylocation_category);
		categoryAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item,category);
		categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sharemylocation_categorySpinner.setAdapter(categoryAdapter);
		sharemylocation_categorySpinner.setOnItemSelectedListener(new SpinnerCategorySelected());
		
		sharemylocation_shareinfoEditText = (EditText) findViewById(R.id.ed_sharemylocation_shareinfo);

	}
	
	//spinner  下拉框响应事件
	class SpinnerCategorySelected implements OnItemSelectedListener{

		@Override
		public void onItemSelected(AdapterView<?> arg0, View view, int num,
				long arg3) {
			
			TextView tv = (TextView)view;
			tv.setTextColor(getResources().getColor(R.color.black));//设置显示字体的颜色
//			tv.setTextSize(12.0f);//设置字体大小 
			
			switch (num) {
			case 0:
				categoryString = "心情";
				break;
			case 1:
				categoryString = "美食";
				break;

			}			
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {	
			categoryString = "心情";
		}
		
	}
	
	//定位
	public class MyLocationListenner implements BDLocationListener{

		@Override
		public void onReceiveLocation(BDLocation location) {
			
			if(location == null)
				return;
			
			Toast.makeText(getApplicationContext(), location.getLongitude() + " "+location.getLatitude(), Toast.LENGTH_SHORT).show();
			
			locationData.longitude = location.getLongitude();
			locationData.latitude = location.getLatitude();
			mLocation.setLontitude(locationData.longitude);
			mLocation.setLatitude(locationData.latitude);
			if(locationData != null){
			
				mSearch.reverseGeocode(new GeoPoint((int) (location.getLatitude() * 1e6),(int) (location.getLongitude() * 1e6)));
			}
		}

		@Override
		public void onReceivePoi(BDLocation poiLocation) {

			if(poiLocation ==  null)
				return;
		}
		
	}
	
	
	//搜索
	public class MySearchListenner implements MKSearchListener{

		@Override
		public void onGetAddrResult(MKAddrInfo result, int iError) {
			MKGeocoderAddressComponent kk = result.addressComponents;
			mylocationplcaeString = kk.city + kk.district + kk.street + kk.streetNumber;
			Toast.makeText(getApplicationContext(), mylocationplcaeString, Toast.LENGTH_SHORT).show();
			
			sharemylocation_placetTextView.setText(mylocationplcaeString);
			
			mLocationClient.stop();
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
	
	@Override
	protected void onDestroy() {
		if(mLocationClient != null)
			mLocationClient.stop();
		super.onDestroy();
	}
	
}
