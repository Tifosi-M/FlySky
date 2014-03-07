package com.telc.ui.Memos;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.SubMenu;
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
import com.baidu.mapapi.search.MKSuggestionInfo;
import com.baidu.mapapi.search.MKSuggestionResult;
import com.baidu.mapapi.search.MKTransitRouteResult;
import com.baidu.mapapi.search.MKWalkingRouteResult;
import com.baidu.platform.comapi.basestruct.GeoPoint;
import com.telc.data.dbDriver.DBConstant;
import com.telc.domain.Service.RealTimeService;
import com.telc.domain.Service.locationService;
import com.telc.resource.baidumap.LocationInfoTran;
import com.telc.resource.baidumap.getPoisitionActivity;
import com.telc.resource.baidumap.getPoisitionActivity.MySearchListener;
import com.telc.smartmemo.R;
import com.telc.domain.Emtity.RealTime;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class RealtimeMemoActivity extends SherlockFragmentActivity {

	
	//数据库
	private SQLiteDatabase db;
	private RealTimeService realTimeHelper;
		
	//实时提醒对象
	private RealTime realTime = new RealTime();
	
	//xml 保存userid
	private SharedPreferences sp;
	private String userid;
	
	private TextView textImportant;
	private RatingBar rb_priority;
	private TextView textLocation;
	private EditText et_location;
	// ImageView iv_maps;
	private TextView textAging;
	private Spinner spinner_time;
	private EditText et_content;
	private ArrayAdapter<String> adapter;

	//存储数据的参数
	//重要程度
	private int priority = 0;
	//时效
	private int aging = 0;
	//定位的名称
	private String locationName = "";
	//定位的经纬度
	private String locationLatLon = "";
	//提醒的内容
	private String content = "";
	//当前时间
	private String start_Time = "";
	
	private static final String[] spinnerSelect={"1天","2天","3天","1周","2周","1月(30天)"};
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_realtime_memo);
		
		
		rb_priority = (RatingBar) findViewById(R.id.rb_priority);
		et_location = (EditText) findViewById(R.id.et_location);

		spinner_time = (Spinner) findViewById(R.id.spinner_time);
		 //将可选内容与ArrayAdapter连接起来
		adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,spinnerSelect);
		//设置下拉列表的风格
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		//将adapter 添加到spinner中  
		spinner_time.setAdapter(adapter);
		
		//添加事件Spinner事件监听    
		spinner_time.setOnItemSelectedListener(new SpinnerSelectedListener());  
          
        //设置默认值  
		spinner_time.setVisibility(View.VISIBLE); 
        
		
		et_content = (EditText) findViewById(R.id.et_content);

		// 失去焦点
		et_location.clearFocus();
		// 始终不弹出软键盘
		et_location.setInputType(InputType.TYPE_NULL);

		et_location.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(RealtimeMemoActivity.this,
						getPoisitionActivity.class);
				startActivityForResult(intent, 0);
			}
		});
		
		sp = getSharedPreferences("Login", MODE_PRIVATE);
		db=openOrCreateDatabase(DBConstant.DB_FILENAME,MODE_PRIVATE, null);
		realTimeHelper=new RealTimeService(db);

		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		LocationInfoTran.StateFlag =false;
//		init();
	}
	
	 //使用数组形式操作  
    class SpinnerSelectedListener implements OnItemSelectedListener{  
  
        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,  
                long arg3) {  
        	switch (arg2) {
			case 0:
				aging = 24;
				break;
			case 1:
				aging = 24*2;
				break;
			case 2:
				aging = 24*3;
				break;
			case 3:
				aging = 24*7;
				break;
			case 4:
				aging = 24*14;
				break;
			case 5:
				aging = 24*30;
				break;
			}
        }  
  
        public void onNothingSelected(AdapterView<?> arg0) {  
        }  
    } 

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		SubMenu menuCheck;
		MenuItem check;
		menuCheck = menu.addSubMenu("保存");
		check = menuCheck.getItem();
		check.setIcon(R.drawable.ic_right);
		check.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS
				| MenuItem.SHOW_AS_ACTION_WITH_TEXT);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		if (item.getItemId() == android.R.id.home) {
			finish();
			return true;
		} else if (item.getItemId() == 0) {

			priority = (int)rb_priority.getRating();
			
			locationName = et_location.getText().toString().trim();// 可以删除
			if(LocationInfoTran.StateFlag)
				locationLatLon = String.valueOf(LocationInfoTran.locationData.latitude) +"-"+ String.valueOf(LocationInfoTran.locationData.longitude);

			if(locationName.equals("")){
				Toast.makeText(getApplicationContext(), "请选择提醒地点！", Toast.LENGTH_SHORT).show();
				return false;
			}
			
			
			content = et_content.getText().toString().trim();
			if(content.equals("")){
				Toast.makeText(getApplicationContext(), "请输入提醒内容！", Toast.LENGTH_SHORT).show();
				return false;
			}
			
			
			SimpleDateFormat formatter  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date   curDate  = new Date(System.currentTimeMillis()); //获取当前时间 
			start_Time = formatter.format(curDate);
			if(start_Time.equals("")){
				Toast.makeText(getApplicationContext(), "时间获取出错，请重试！", Toast.LENGTH_SHORT).show();
				return false;
			}
			
			
			//设置隐藏属性
			userid = sp.getString("user", null);
			
			realTime.setPriority(priority);
			realTime.setContent(content);
			realTime.setAging(aging);
			realTime.setLocation(locationLatLon);
			realTime.setStart_time(start_Time);
			realTime.setUser_id(userid);
			realTime.setLocation_detail(LocationInfoTran.positionNameString);
			realTime.setReal_id(String.valueOf(System.currentTimeMillis()));
			

			if(realTimeHelper.addRealTime(realTime)){
				Toast.makeText(getApplicationContext(), "保存成功！", Toast.LENGTH_SHORT).show();
				finish();
				return true;
			}
			else {
				Toast.makeText(getApplicationContext(), "保存失败！", Toast.LENGTH_SHORT).show();
				return false;
			}
		} else
			return false;
	}

	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		init();
	}

	public void init() {

		LocationInfoTran.startToUse = false;

		if (LocationInfoTran.StateFlag) {
			if (LocationInfoTran.selectFlag == 3) {
				if (LocationInfoTran.locationData.latitude == 0.0
						|| LocationInfoTran.locationData.longitude == 0.0) {
					Toast.makeText(getApplicationContext(), "地址获取失败，请检查当前网络！",
							Toast.LENGTH_SHORT).show();
					return;
				}
				et_location.setText("我的位置");
			}
			if (LocationInfoTran.selectFlag == 2) {
				et_location.setText("地图上的点");
			}
			if (LocationInfoTran.selectFlag == 1) {
				et_location.setText(LocationInfoTran.positionNameString);
			}
			
			Toast.makeText(
					getApplicationContext(),
					"坐标点：" + LocationInfoTran.locationData.latitude + "\n"
							+ LocationInfoTran.locationData.longitude,
					Toast.LENGTH_SHORT).show();
		}
	}

}
