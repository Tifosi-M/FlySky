package com.telc.ui.Memos;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.SubMenu;
import com.telc.data.dbDriver.DBConstant;
import com.telc.domain.Emtity.Periodic;
import com.telc.domain.IService.IPeriodicService;
import com.telc.domain.Service.PeriodicService;
import com.telc.smartmemo.R;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

public class PeriodicMemoDelActivity extends SherlockFragmentActivity {
//	数据库
	private SQLiteDatabase db;
	private PeriodicService periodicHelper;
//	xml保存userid
	RatingBar ratingBarPeriodicPriority;
	Spinner spinnerPeriodic;
	EditText editPeriodicContent,editPeriodicDetail;
	private ArrayAdapter<String> spinnerAdapter;
//	周期性提醒对象
	private Periodic mPeriodic=new Periodic();
	private String mIndex;
	
//	private final String[] spinnerPeriodicItem={"每天","每周","每月"};
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_periodic);
		db=openOrCreateDatabase(DBConstant.DB_FILENAME,MODE_PRIVATE, null);
		periodicHelper=new PeriodicService(db);
		
		Intent intent=getIntent();
		Bundle bundle=intent.getExtras();
		mIndex=bundle.getString("index");
		
		ratingBarPeriodicPriority=(RatingBar) findViewById(R.id.ratingBarPeriodicPriority);
		ratingBarPeriodicPriority.setEnabled(false);
		
		spinnerPeriodic=(Spinner) findViewById(R.id.spinnerPeriodic);
		spinnerPeriodic.setEnabled(false);
		
		
		editPeriodicDetail=(EditText) findViewById(R.id.editTextPeriodicDetail);
		//设置为不可编辑
		editPeriodicDetail.setEnabled(false);
		editPeriodicDetail.setFocusable(false);
		
		editPeriodicContent=(EditText) findViewById(R.id.editPeriodicContent);
		editPeriodicContent.setEnabled(false);
		editPeriodicContent.setFocusable(false);

		
		mPeriodic=periodicHelper.findPeriodicById(mIndex);
		
		ratingBarPeriodicPriority.setRating((float)mPeriodic.getPriority());
		editPeriodicDetail.setText(mPeriodic.getPeriod_detail());
		editPeriodicContent.setText(mPeriodic.getContent());
		 //将可选内容与ArrayAdapter连接起来
		
		String[] spinnerPeriodicItem={mPeriodic.getPeriod()};
		spinnerAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,spinnerPeriodicItem);
		//设置下拉列表的风格
		spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerPeriodic.setAdapter(spinnerAdapter);
		
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		MenuItem check;
		SubMenu menuCheck;
		menuCheck=menu.addSubMenu("删除");
		check=menuCheck.getItem();
		check.setIcon(R.drawable.ic_recycle);
		check.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS | MenuItem.SHOW_AS_ACTION_WITH_TEXT);
		return true;
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		if(item.getItemId()==android.R.id.home){
			finish();
			return true;
		}else if(item.getItemId()==0){
			deletePeriodicMemo();
			return true;
		}else
			return false;
	}

	private boolean deletePeriodicMemo(){

		if(periodicHelper.removePeriodic(mIndex)){
			Toast.makeText(getApplicationContext(), "删除成功！", Toast.LENGTH_SHORT).show();
			finish();
			return true;
		}else {
			Toast.makeText(getApplicationContext(), "删除失败！", Toast.LENGTH_SHORT).show();
			return false;
		}
	}

}
