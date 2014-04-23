package com.telc.ui.main.viewModel;

import java.util.List;
import java.util.Map;

import com.telc.data.dbDriver.DBConstant;
import com.telc.domain.Service.CampusService;
import com.telc.smartmemo.R;
import com.telc.ui.Memos.CampusDetailActivity;

import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;


public class CampusFragment extends Fragment{
	private SQLiteDatabase db;
	private ListView campusList;
	private CampusService campusService;
	// 保存list中的item的列表
	List<Map<String, Object>> mList;
	// listView适配器
	SimpleAdapter mAdapter = null;
	// 适配器中的key
	String[] from = { "campusname", "campusstate",
			"campusby" };
	// value
	int[] to = { R.id.textListContent, R.id.btn_state,
			R.id.textListCategory};
	Cursor cursor;
	Button btn_state;
	CampusCursorAdapter campusCursorAdapter;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.activity_campus_list, null);
		
		campusList=(ListView) view.findViewById(R.id.list_campus);
		// 实例化Adapter
		// 打开数据库
		db = getActivity().openOrCreateDatabase(DBConstant.DB_FILENAME,
				getActivity().MODE_PRIVATE, null);
		// 实例化数据库服务
		campusService=new CampusService(db);
		cursor=campusService.findCampus();
		campusCursorAdapter=new CampusCursorAdapter(getActivity(),
				R.layout.listview_campus_layout, cursor, new String[] {
						"campusid","campusname", "campusstate","campusby" }, new int[] {
				R.id.textCampusId,R.id.textListContent, R.id.btn_state ,R.id.textListCategory});
		campusList.setAdapter(campusCursorAdapter);
		
		campusList.setOnItemClickListener(new OnItemClickListener() {
			
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Toast toast = Toast.makeText(getActivity(),
						"sessss", Toast.LENGTH_SHORT);
				toast.show();
				String campus_id=CampusCursorAdapter.list_campusid.get(arg2);

				Intent intent=new Intent(getActivity(),CampusDetailActivity.class);
				Bundle bundle = new Bundle(); 
				bundle.putString("campusid", campus_id); 
				intent.putExtras(bundle);
				startActivity(intent);
			}
		});
		return view;
	}
	
	//注册一个广播接收类
	public void registerBroadcastReceiver(){
		IntentFilter mIntentFilter = new IntentFilter();
		mIntentFilter.addAction("UPDATE_ADAPTER");
		getActivity().registerReceiver(mBroadcastReceiver, mIntentFilter);
	}
	
	//定义一个广播接收类
	private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver(){
		
		public void onReceive(android.content.Context context, Intent intent) {
			String action = intent.getAction();  
            if(action.equals("UPDATE_ADAPTER")){  
                cursor=campusService.findCampus();
        		campusCursorAdapter=new CampusCursorAdapter(getActivity(),
        				R.layout.listview_campus_layout, cursor, new String[] {
        						"campusid","campusname", "campusstate","campusby" }, new int[] {
        				R.id.textCampusId,R.id.textListContent, R.id.btn_state ,R.id.textListCategory});
        		campusList.setAdapter(campusCursorAdapter);
            }  
		}
	};

	@Override
	public void onResume() {
		registerBroadcastReceiver();//注册广播类
		super.onResume();
	}
	
	@Override
	public void onStop() {

		getActivity().unregisterReceiver(mBroadcastReceiver);
		super.onStop();
	}
	
}
