package com.telc.ui.Memos;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.SubMenu;
import com.telc.data.dbDriver.DBConstant;
import com.telc.domain.Emtity.Campus;
import com.telc.domain.Service.CampusService;
import com.telc.smartmemo.R;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CampusDetailActivity extends SherlockFragmentActivity {
	TextView txt_title;
	EditText et_location, et_time, ed_content, et_campusby;
	Button btn_state;
    String campusid;
    CampusService campusService;
    Campus campus;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_campus_memo);
		campusid = this.getIntent().getExtras().getString("campusid");
		SQLiteDatabase db = openOrCreateDatabase(DBConstant.CAMPUSDBFILENAME,
				MODE_PRIVATE, null);
		campusService=new CampusService(db);
		campus=campusService.getCampusById(campusid);
		txt_title = (TextView) findViewById(R.id.txt_title);
		et_location = (EditText) findViewById(R.id.et_location);
		et_time = (EditText) findViewById(R.id.et_time);
		ed_content = (EditText) findViewById(R.id.ed_content);
		et_campusby = (EditText) findViewById(R.id.et_campusby);
		btn_state = (Button) findViewById(R.id.btn_state);
		txt_title.setText(campus.getCampusname());
		et_location.setText(campus.getCampusaddress());
		et_time.setText(campus.getCampustime());
		ed_content.setText(campus.getCampuscontent());
		et_campusby.setText(campus.getCampusby());
		if(campus.getCampusstate().equals("N")){
			btn_state.setText("未关注");
			btn_state.setBackgroundColor(getResources().getColor(R.color.main_bg));
		}else {
			btn_state.setText("已关注");
			btn_state.setBackgroundColor(getResources().getColor(R.color.red));
		}
		btn_state.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(campus.getCampusstate().equals("Y")){
					campusService.updateCampusStatus(campusid, false);
					btn_state.setText("未关注");
					btn_state.setBackgroundColor(getResources().getColor(R.color.main_bg));
				}else {
					campusService.updateCampusStatus(campusid, true);
					btn_state.setText("已关注");
					btn_state.setBackgroundColor(getResources().getColor(R.color.red));
				}
			}
		});
		getActionBar().setDisplayHomeAsUpEnabled(true);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		if (item.getItemId() == android.R.id.home) {
			finish();
			return true;
		} else{
			return false;
		}
	}
}
