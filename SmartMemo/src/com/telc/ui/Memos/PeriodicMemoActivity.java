package com.telc.ui.Memos;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.SubMenu;
import com.telc.data.dbDriver.DBConstant;
import com.telc.domain.Emtity.Periodic;
import com.telc.domain.Service.PeriodicService;
import com.telc.domain.time.Service.TimeService;
import com.telc.smartmemo.R;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.MergeCursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.TimePicker.OnTimeChangedListener;

public class PeriodicMemoActivity extends SherlockFragmentActivity {
	// 数据库
	private SQLiteDatabase db;
	private PeriodicService periodicHelper;
	// xml保存userid
	private SharedPreferences sp;
	private String userid;
	RatingBar ratingBarPeriodicPriority;
	Spinner spinnerPeriodic, periodic_week_select;
	EditText editPeriodicContent, editPeriodicDetail, timeEt;
	TimePicker timePicker;
	private ArrayAdapter<String> spinnerAdapter;
	TimeService mTime =TimeService.getInstance();
	// 周期性提醒对象
	private Periodic mPeriodic = new Periodic();

	private Dialog periodic_day_dialog, periodic_week_dialog;
	// 保存spinner中的位置
	private int periodicPosition = 1;

	Context context = null;
	String time = "",week="星期日";
	Button btn_time_dialog_ok, btn_time_dialog_cancel;

	private final String[] spinnerPeriodicItem = { "每天", "每周" };
	private final String[] spinnerPeriodicWeekSelectItem = { "星期日", "星期一",
			"星期二", "星期三", "星期四", "星期五", "星期六" };

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_periodic);
		context = this;
		sp = getSharedPreferences("Login", MODE_PRIVATE);
		db = openOrCreateDatabase(DBConstant.DB_FILENAME, MODE_PRIVATE, null);
		periodicHelper = new PeriodicService(db);

		ratingBarPeriodicPriority = (RatingBar) findViewById(R.id.ratingBarPeriodicPriority);
		spinnerPeriodic = (Spinner) findViewById(R.id.spinnerPeriodic);
		editPeriodicDetail = (EditText) findViewById(R.id.editTextPeriodicDetail);
		editPeriodicDetail.setEnabled(false);
		EditPeriodicDetailListener();

		editPeriodicContent = (EditText) findViewById(R.id.editPeriodicContent);

		// 将可选内容与ArrayAdapter连接起来
		spinnerAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, spinnerPeriodicItem);
		// 设置下拉列表的风格
		spinnerAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerPeriodic.setAdapter(spinnerAdapter);
		spinnerPeriodic
				.setOnItemSelectedListener(new SpinnerSelectedListener());

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}

	// 使用数组形式操作
	class SpinnerSelectedListener implements OnItemSelectedListener {

		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			switch (arg2) {
			case 0:
				periodicPosition = 1;
				editPeriodicDetail.setEnabled(true);
				editPeriodicDetail.setText("");
				break;
			case 1:
				periodicPosition = 2;
				editPeriodicDetail.setEnabled(true);
				editPeriodicDetail.setText("");
				break;
			}
		}

		public void onNothingSelected(AdapterView<?> arg0) {
		}
	}

	// 设置周期详细编辑框监听
	public void EditPeriodicDetailListener() {

		editPeriodicDetail.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (periodicPosition == 1) {
					periodic_day_dialog = new Dialog(context);
					periodic_day_dialog
							.requestWindowFeature(Window.FEATURE_NO_TITLE);

					LayoutInflater inflater = LayoutInflater
							.from(PeriodicMemoActivity.this);
					final View dialogView = inflater.inflate(
							R.layout.periodic_day_dialog, null);
					InitDayDialog(dialogView);
					periodic_day_dialog.setContentView(dialogView);
					periodic_day_dialog.show();
				}
				if (periodicPosition == 2) {
					periodic_week_dialog = new Dialog(context);
					periodic_week_dialog
							.requestWindowFeature(Window.FEATURE_NO_TITLE);

					LayoutInflater inflater = LayoutInflater
							.from(PeriodicMemoActivity.this);
					final View dialogView = inflater.inflate(
							R.layout.periodic_week_dialog, null);
					InitWeekDialog(dialogView);
					periodic_week_dialog.setContentView(dialogView);
					periodic_week_dialog.show();
				}
			}
		});

	}

	// 初始化每天详细时间对话框
	public void InitDayDialog(View view) {
		time="";
		timeEt = (EditText) view.findViewById(R.id.timeEt);
		timePicker = (TimePicker) view.findViewById(R.id.timePicker);
		btn_time_dialog_ok = (Button) view
				.findViewById(R.id.btn_time_dialog_ok);
		btn_time_dialog_cancel = (Button) view
				.findViewById(R.id.btn_time_dialog_cancel);
		timePicker.setIs24HourView(true);
		TimeService service = TimeService.getInstance();
		String currentTime = service.longSwithToString(System
				.currentTimeMillis());
		String currentHour = currentTime.substring(11, 13);

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
				periodic_day_dialog.dismiss();
			}
		});

		btn_time_dialog_ok.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (time.equals("")) {
					Toast toast = Toast.makeText(getApplicationContext(),
							"请选择时间", Toast.LENGTH_SHORT);
					toast.show();
				} else {
					editPeriodicDetail.setText(time + ":00");
					periodic_day_dialog.dismiss();
				}
			}
		});

	}

	// 初始化每周详细时间对话框
	public void InitWeekDialog(View view) {
		time="";
		periodic_week_select = (Spinner) view
				.findViewById(R.id.periodic_week_select);
		timeEt = (EditText) view.findViewById(R.id.timeEt);
		timePicker = (TimePicker) view.findViewById(R.id.timePicker);
		btn_time_dialog_ok = (Button) view
				.findViewById(R.id.btn_time_dialog_ok);
		btn_time_dialog_cancel = (Button) view
				.findViewById(R.id.btn_time_dialog_cancel);

		// 将可选内容与ArrayAdapter连接起来
		spinnerAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item,
				spinnerPeriodicWeekSelectItem);
		// 设置下拉列表的风格
		spinnerAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		periodic_week_select.setAdapter(spinnerAdapter);
		periodic_week_select
				.setOnItemSelectedListener(new SpinnerWeekSelectedListener());

		timePicker.setIs24HourView(true);
		TimeService service = TimeService.getInstance();
		String currentTime = service.longSwithToString(System
				.currentTimeMillis());
		String currentHour = currentTime.substring(11, 13);

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
				periodic_week_dialog.dismiss();
			}
		});

		btn_time_dialog_ok.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (time.equals("")) {
					Toast toast = Toast.makeText(getApplicationContext(),
							"请选择时间", Toast.LENGTH_SHORT);
					toast.show();
				} else {
					editPeriodicDetail.setText(week+" "+time + ":00");
					periodic_week_dialog.dismiss();
				}
			}
		});

	}
	// 使用数组形式操作
	class SpinnerWeekSelectedListener implements OnItemSelectedListener {

		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			switch (arg2) {
			case 0:
				week="星期日";
				break;
			case 1:
				week="星期一";
				break;
			case 2:
				week="星期二";
				break;
			case 3:
				week="星期三";
				break;
			case 4:
				week="星期四";
				break;
			case 5:
				week="星期五";
				break;
			case 6:
				week="星期六";
				break;
			}
		}

		public void onNothingSelected(AdapterView<?> arg0) {
		}
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
			return true;
		} else if (item.getItemId() == 0) {
			savePeriodicMemo();
			return true;
		} else
			return false;
	}

	private String getPeriodicContent() {
		return editPeriodicContent.getText().toString();
	}

	private String getSpinnerPeriodic(int position) {
		return (String) spinnerPeriodic.getItemAtPosition(position-1);
	}

	private String getPeriodicDetail() {
		return editPeriodicDetail.getText().toString();
	}

	private int getRatingBarPeriodicPriority() {
		return (int) ratingBarPeriodicPriority.getRating();
	}

	private boolean savePeriodicMemo() {
		// 设置从界面输入的数据
		mPeriodic.setContent(getPeriodicContent());
		mPeriodic.setPeriod(getSpinnerPeriodic(periodicPosition));
		mPeriodic.setPeriod_detail(getPeriodicDetail());
		mPeriodic.setPriority(getRatingBarPeriodicPriority());

		// 设置隐藏属性
		userid = sp.getString("user", null);
		mPeriodic.setUser_id(userid);
		mPeriodic.setIsfinish(0);
		String startTime = mTime.longSwithToString(System.currentTimeMillis());
		String idString = String.valueOf(mTime.getSecondsFromDate(startTime));
		mPeriodic.setStart_time(startTime);
		mPeriodic.setPeriodic_id(idString);
		switch (spinnerPeriodic.getSelectedItemPosition()) {
		case 0:
			mPeriodic.setEnd_time((mTime.longSwithToString(System.currentTimeMillis()).substring(0, 10))+" "+(editPeriodicDetail.getText().toString()));
			break;
		}
		if (periodicHelper.addPeriodic(mPeriodic)) {
			Toast.makeText(getApplicationContext(), "保存成功！", Toast.LENGTH_SHORT)
					.show();
			finish();
			return true;
		} else {
			Toast.makeText(getApplicationContext(), "保存失败！", Toast.LENGTH_SHORT)
					.show();
			return false;
		}
	}
}
