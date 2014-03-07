package com.telc.ui.Memos;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.SubMenu;
import com.telc.data.dbDriver.DBConstant;
import com.telc.domain.Emtity.Timing;
import com.telc.domain.IService.ITimingService;
import com.telc.domain.Service.TimingService;
import com.telc.smartmemo.R;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.database.sqlite.SQLiteDatabase;
import android.content.Intent;
import android.text.InputType;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Switch;
import android.widget.TableRow;
import android.widget.Toast;

public class TimingMemoDelActivity extends SherlockFragmentActivity {

	private SQLiteDatabase db;
	private EditText ed_timing_time, ed_timing_loction, edit_Timing_Content;
	private RatingBar ratingBarTimingPriority;
	private Switch sw_timing;
	private Timing timing = new Timing();
	private ITimingService timingService = new TimingService(db);
	
	private String mIndex;
	TableRow tabl_location, tabl_content;
	Drawable drawable;
	String location = "";//获取地点设置
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_timing);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		init();
		
		Intent intent=getIntent();
		Bundle bundle=intent.getExtras();
		mIndex=bundle.getString("index");
		
		timing = timingService.findTimingByStart(mIndex);
		setRegWithContent(timing);
	}	
	//控件加载设置要显示的内容
	private void setRegWithContent(Timing timing) {
		ed_timing_time.setText(timing.getEnd_time());
		ed_timing_loction.setText(timing.getLocation_detail());
		edit_Timing_Content.setText(timing.getContent());
		ratingBarTimingPriority.setRating(timing.getPriority());
	}
	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
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
			Toast.makeText(getApplicationContext(), "删除成功！", Toast.LENGTH_SHORT).show();
			timingService.removeTiming(mIndex);
			finish();
			return true;
		}else
			return false;
	}

//查找要发现的按钮，并未相应的按钮添加响应事件
	public void init() {
		SQLiteDatabase db = openOrCreateDatabase(DBConstant.DB_FILENAME,
				MODE_PRIVATE, null);
		timingService = new TimingService(db);
	
		ed_timing_time = (EditText) findViewById(R.id.ed_timing_time);    
		ed_timing_time.setEnabled(false);
		ed_timing_loction = (EditText) findViewById(R.id.ed_timing_loction);
		ed_timing_loction.setEnabled(false);
		edit_Timing_Content = (EditText) findViewById(R.id.edit_Timing_Content);
		edit_Timing_Content.setEnabled(false);
		
		ratingBarTimingPriority = (RatingBar) findViewById(R.id.ratingBarTimingPriority);
		
		tabl_location = (TableRow) findViewById(R.id.tabl_location);
		tabl_location.setVisibility(View.GONE);
		tabl_content = (TableRow) findViewById(R.id.tabl_content);
		sw_timing = (Switch) findViewById(R.id.sw_timing);
		
		// 失去焦点，不接受用户的输入
		ed_timing_loction.clearFocus();
		ed_timing_loction.setInputType(InputType.TYPE_NULL);
		
		ed_timing_time.clearFocus();
		ed_timing_time.setInputType(InputType.TYPE_NULL);
	
		edit_Timing_Content.clearFocus();
		edit_Timing_Content.setInputType(InputType.TYPE_NULL);
		
		ratingBarTimingPriority.setEnabled(false);
		//通过开关的选择设置地点的显示
		sw_timing.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@SuppressWarnings("deprecation")
			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					tabl_location.setVisibility(View.VISIBLE);
					drawable = getResources().getDrawable(
							R.drawable.table_button_bottom_bg);
					tabl_content.setBackgroundDrawable(drawable);
				} else {
					drawable = getResources().getDrawable(
							R.drawable.table_button_single_bg);
					tabl_content.setBackgroundDrawable(drawable);
					tabl_location.setVisibility(View.GONE);
				}
			}
		});
	}
}
