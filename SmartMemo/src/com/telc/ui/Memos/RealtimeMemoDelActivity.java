package com.telc.ui.Memos;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.SubMenu;
import com.telc.data.dbDriver.DBConstant;
import com.telc.domain.Service.RealTimeService;
import com.telc.resource.baidumap.LocationInfoTran;
import com.telc.resource.baidumap.getPoisitionActivity;
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
import android.widget.Toast;

public class RealtimeMemoDelActivity extends SherlockFragmentActivity {
	//数据库
	private SQLiteDatabase db;
	private RealTimeService realTimeHelper;
		
	//xml 保存userid
	private SharedPreferences sp;
	private RatingBar rb_priority;
	private EditText et_location;
	private Spinner spinner_time;
	private EditText et_content;
	private ArrayAdapter<String> adapter;
	private String mIndex;
	private static final String[] spinnerSelect={"1天","2天","3天","1周","2周","1月(30天)"};
	private int flag;
	//实时提醒对象
	private RealTime mRealTime ;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_realtime_memo);
//		获取传入的bundle数据
		Intent intent=getIntent();
		Bundle bundle=intent.getExtras();
		mIndex=bundle.getString("index");
		
		
		db=openOrCreateDatabase(DBConstant.DB_FILENAME,MODE_PRIVATE, null);
		realTimeHelper=new RealTimeService(db);
		
		mRealTime = new RealTime();
		mRealTime = realTimeHelper.findRealTimeByStart(mIndex);
		
		
		rb_priority = (RatingBar) findViewById(R.id.rb_priority);
		rb_priority.setEnabled(false);

		spinner_time = (Spinner) findViewById(R.id.spinner_time);
		 //将可选内容与ArrayAdapter连接起来
		adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,spinnerSelect);
		//设置下拉列表的风格
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		//将adapter 添加到spinner中  
		spinner_time.setAdapter(adapter);
        //设置默认值  
		spinner_time.setVisibility(View.VISIBLE); 
		spinner_time.setEnabled(false);
        
		
		et_content = (EditText) findViewById(R.id.et_content);
		et_content.setEnabled(false);

		et_location = (EditText) findViewById(R.id.et_location);
		et_location.setEnabled(false);
		// 失去焦点
		et_location.clearFocus();
		// 始终不弹出软键盘
		et_location.setInputType(InputType.TYPE_NULL);

		
		rb_priority.setRating(mRealTime.getPriority());
		et_content.setText(mRealTime.getContent());
		et_location.setText(mRealTime.getLocation_detail());
		
		switch(mRealTime.getAging()/24){
			case 1: flag =0;break;
			case 2:flag=1;break;
			case 3:flag=2;break;
			case 7:flag= 3;break;
			case 14:flag = 4;break;
			case 30:flag=5;break;
		}
		
		spinner_time.setSelection(flag);
		
		getActionBar().setDisplayHomeAsUpEnabled(true);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		SubMenu menuCheck;
		MenuItem check;
		menuCheck = menu.addSubMenu("删除");
		check = menuCheck.getItem();
		check.setIcon(R.drawable.ic_recycle);
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
				Toast.makeText(getApplicationContext(), "删除成功！", Toast.LENGTH_SHORT).show();
				realTimeHelper.removeRealTime(mIndex);
				finish();
				return true;
		}else {
			Toast.makeText(getApplicationContext(), "删除！", Toast.LENGTH_SHORT).show();
			return false;
		}
	}
		
}
