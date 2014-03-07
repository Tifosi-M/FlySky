package com.telc.ui.Memos;

import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.security.auth.PrivateCredentialPermission;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.SubMenu;
import com.telc.data.dbDriver.DBConstant;
import com.telc.domain.Emtity.Timing;
import com.telc.domain.IService.ITimingService;
import com.telc.domain.Service.TimingService;
import com.telc.domain.time.Service.TimeService;
import com.telc.resource.baidumap.LocationInfoTran;
import com.telc.resource.baidumap.getPoisitionActivity;
import com.telc.smartmemo.R;
import com.telc.ui.main.SlidingActivity;
import com.telc.ui.systemManagement.LoginAndRegisterActivity;

import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.content.Intent;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Switch;
import android.widget.TableRow;
import android.widget.TimePicker;
import android.widget.TimePicker.OnTimeChangedListener;
import android.widget.Toast;

public class TimingMemoActivity extends SherlockFragmentActivity {

	EditText ed_timing_time, ed_timing_loction, edit_Timing_Content, dateEt,
			timeEt;
	RatingBar ratingBarTimingPriority;
	Switch sw_timing;
	TimeService mTimeService;
	TimingService timingService;
	private SharedPreferences sp;
	TimingMemoActivity timingMemoActivity;
	TableRow tabl_location, tabl_content;
	Drawable drawable;
	String location = "";// 获取地点设置
	String locationDetail=" ";
	private Dialog dl;
	Context context;
	private int mHour; // 存放时间的小时
	private int mMinute; // 存放时间的分钟
	private int mSecond; // 存放时间的秒
	TimePicker timePicker;
	DatePicker datePicker;
	Button btn_time_dialog_ok, btn_time_dialog_cancel;
	String date = "", time = "";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_timing);
		sp = getSharedPreferences("Login", MODE_PRIVATE);
		timingMemoActivity = this;
		context = this;
		mTimeService=TimeService.getInstance();
		getActionBar().setDisplayHomeAsUpEnabled(true);
		init();

	}

	public void init1() {
		
		LocationInfoTran.startToUse = false;

		if (LocationInfoTran.StateFlag) {
			if (LocationInfoTran.selectFlag == 3) {
				if (LocationInfoTran.locationData.latitude == 0.0
						|| LocationInfoTran.locationData.longitude == 0.0) {
					Toast.makeText(getApplicationContext(), "地址获取失败，请检查当前网络！",
							Toast.LENGTH_SHORT).show();
					return;
				}
				ed_timing_loction.setText("我的位置");
			}
			if (LocationInfoTran.selectFlag == 2) {
				ed_timing_loction.setText("地图上的点");
			}
			if (LocationInfoTran.selectFlag == 1) {
				ed_timing_loction.setText(LocationInfoTran.positionNameString);
			}
			Toast.makeText(
					getApplicationContext(),
					"坐标点：" + LocationInfoTran.locationData.latitude + "\n"
							+ LocationInfoTran.locationData.longitude,
					Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	protected void onResume() {
		init1();
		super.onResume();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		MenuItem check;
		SubMenu menuCheck;
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
//			从列表中取出时间最近的提醒，并设置发送广播。
//			timingRemind();
			return true;
		} else if (item.getItemId() == 0) {
			saveTimingMemo();
//			从列表中取出时间最近的提醒，并设置发送广播。
//			timingRemind();
			return true;
		} else
			return false;
	}

	public void init() {
		SQLiteDatabase db = openOrCreateDatabase(DBConstant.DB_FILENAME,
				MODE_PRIVATE, null);
		timingService = new TimingService(db);

		ed_timing_time = (EditText) findViewById(R.id.ed_timing_time);

		ed_timing_loction = (EditText) findViewById(R.id.ed_timing_loction);

		edit_Timing_Content = (EditText) findViewById(R.id.edit_Timing_Content);

		ratingBarTimingPriority = (RatingBar) findViewById(R.id.ratingBarTimingPriority);
		tabl_location = (TableRow) findViewById(R.id.tabl_location);
		tabl_location.setVisibility(View.GONE);
		tabl_content = (TableRow) findViewById(R.id.tabl_content);
		sw_timing = (Switch) findViewById(R.id.sw_timing);
		
		ed_timing_time.clearFocus();
		ed_timing_time.setInputType(InputType.TYPE_NULL);

		Timing timing = obtainTimingInfo();

		ed_timing_time.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dl = new Dialog(context);
				dl.requestWindowFeature(Window.FEATURE_NO_TITLE);

				LayoutInflater inflater = LayoutInflater
						.from(TimingMemoActivity.this);
				final View dialogView = inflater.inflate(R.layout.time_dialog,
						null);
				InitDialog(dialogView);
				dl.setContentView(dialogView);
				dl.show();
			}
		});

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
		// 失去焦点
		ed_timing_loction.clearFocus();
		// 始终不弹出软键盘
		ed_timing_loction.setInputType(InputType.TYPE_NULL);
	}

	// 保存定时备忘录
	public void saveTimingMemo() {
		Timing timing = obtainTimingInfo();
		if (timing.getEnd_time().compareTo(timing.getStart_time()) < 0) {
			Toast.makeText(TimingMemoActivity.this, "提醒时间要比当前时间晚哦",
					Toast.LENGTH_SHORT).show();
			return;
		}

		if (timing.getContent() == null || timing.getContent().equals("")) {
			Toast.makeText(TimingMemoActivity.this, "提醒内容不能为空",
					Toast.LENGTH_SHORT).show();
			return;
		}
		timingService.addTiming(timing);
		Toast.makeText(context, "保存成功！", Toast.LENGTH_SHORT).show();

		finish();
	}

	private Timing obtainTimingInfo() {
		Timing timing = new Timing();
		TimeService service = TimeService.getInstance();

		// 定时提醒中主要包括提醒时间，提醒内容，定时的星级大小，定時的起始時間，定時的結束時間

		ed_timing_loction.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(TimingMemoActivity.this,
						getPoisitionActivity.class);
				startActivityForResult(intent, 0);
			}
		});
		// 定时提醒中主要包括提醒时间，提醒内容，定时的星级大小，定時的起始時間，定時的結束時間

		String start_time = service.getCurrentTime();
		String end_timeString = ed_timing_time.getText().toString();
		String contentString = edit_Timing_Content.getText().toString();
		int priority = (int) ratingBarTimingPriority.getRating();
		int isFinished = 0;

		timing.setUser_id(sp.getString("user", null));
		timing.setContent(contentString);
		timing.setEnd_time(end_timeString);
		timing.setIsfinish(isFinished);
		timing.setPriority(priority);
		timing.setLocation(location);
		timing.setLocation_detail(locationDetail);
		timing.setStart_time(start_time);
		timing.setTiming_id(String.valueOf(System.currentTimeMillis()));
		return timing;

	}

	private void InitDialog(View view) {
		dateEt = (EditText) view.findViewById(R.id.dateEt);
		timeEt = (EditText) view.findViewById(R.id.timeEt);
		datePicker = (DatePicker) view.findViewById(R.id.datePicker);
		timePicker = (TimePicker) view.findViewById(R.id.timePicker);
		btn_time_dialog_ok = (Button) view
				.findViewById(R.id.btn_time_dialog_ok);
		btn_time_dialog_cancel = (Button) view
				.findViewById(R.id.btn_time_dialog_cancel);
		timePicker.setIs24HourView(true);
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int monthOfYear = calendar.get(Calendar.MONTH);
		int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
		datePicker.setCalendarViewShown(false);
		datePicker.init(year, monthOfYear, dayOfMonth,
				new OnDateChangedListener() {

					public void onDateChanged(DatePicker view, int year,
							int monthOfYear, int dayOfMonth) {
                        String month,day;
						if(monthOfYear + 1<10)
						{
							month="0"+String.valueOf(monthOfYear + 1);
						}
						else
						{
							month=String.valueOf(monthOfYear + 1);
						}
						if(dayOfMonth<10)
						{
							day="0"+String.valueOf(dayOfMonth);
						}
						else
						{
							day=String.valueOf(dayOfMonth);
						}
						dateEt.setText(year + "年" + month + "月"
								+ day + "日");
						date = year + "-" + month + "-"
								+ day + " ";
					}

				});

		TimeService service=TimeService.getInstance();
		String currentTime=service.longSwithToString(System.currentTimeMillis());
		String currentHour=currentTime.substring(11, 13);
		timePicker.setCurrentHour(Integer.valueOf(currentHour));
		timePicker.setOnTimeChangedListener(new OnTimeChangedListener() {

			public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {

				String hour, min;
				if (hourOfDay < 10) {
					hour = "0" + String.valueOf(hourOfDay);
				} else {
					hour = String.valueOf(hourOfDay);
				}
				if (minute < 10) {
					min = "0" + String.valueOf(minute);
				} else {
					min = String.valueOf(minute);
				}
				timeEt.setText(hour + "时" + min + "分");
				time = hour + ":" + min;
			}
		});

		btn_time_dialog_cancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dl.dismiss();
			}
		});

		btn_time_dialog_ok.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (date.equals("")) {
					Toast toast = Toast.makeText(getApplicationContext(),
							"请选择日期", Toast.LENGTH_SHORT);
					toast.show();
				} else if (time.equals("")) {
					Toast toast = Toast.makeText(getApplicationContext(),
							"请选择时间", Toast.LENGTH_SHORT);
					toast.show();
				} else {
					ed_timing_time.setText(date + time + ":00");
					dl.dismiss();
				}
			}
		});

	}

//	private void timingRemind(){
//		List<Timing> timingList=timingService.getTimingByUserID(sp.getString("user", null));
//		if (timingList != null) {
//			Collections.sort(timingList, new Comparator<Timing>() {
//				@Override
//				public int compare(Timing lhs, Timing rhs) {
//					long timingEndtime1 = mTimeService.getSecondsFromDate(lhs.getEnd_time());
//					long timingEndtime2 = mTimeService.getSecondsFromDate(rhs.getEnd_time());
//					if (timingEndtime1<=timingEndtime2) {
//						return -1;
//					} else 
//						return 1;
//				}
//			});
//			for(int i=0;i<timingList.size();i++){
//				if(timingList.get(i).getIsfinish()==0){
//					String content=timingList.get(i).getContent();
//					String userId=sp.getString("user", null);
//					long endTime= mTimeService.getSecondsFromDate(timingList.get(i).getEnd_time());
//					
//					Intent timingAlarm=new Intent(TimingMemoActivity.this,TimingReceiver.class);
//					Bundle bund=new Bundle();
//					bund.putString("user", userId);
//					bund.putString("content", content);
//					bund.putString("timingId", timingList.get(i).getTiming_id());
//					timingAlarm.putExtras(bund);
//					PendingIntent pendingIntent = PendingIntent.getBroadcast(TimingMemoActivity.this, 0, timingAlarm, PendingIntent.FLAG_UPDATE_CURRENT);
//					AlarmManager timingManager=(AlarmManager) getSystemService(ALARM_SERVICE);
//					timingManager.cancel(pendingIntent);
//					timingManager.set(AlarmManager.RTC_WAKEUP, endTime, pendingIntent);
//					return;
//				}
//			}
//		}
//
//	}
	
}
