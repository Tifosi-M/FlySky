package com.telc.ui.main.viewModel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.telc.data.dbDriver.DBConstant;
import com.telc.domain.Service.CampusService;
import com.telc.smartmemo.R;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class CampusCursorAdapter extends SimpleCursorAdapter {
	private Context context;
	private int layout;
	private CampusListView  campusListView;
	public static List<String> list_campusid=new ArrayList<String>();
	private Button btn_state;
	private CampusService campusService=null;
	private Cursor cursor;
	private SQLiteDatabase db;
	@SuppressWarnings("deprecation")
	public CampusCursorAdapter(Context context, int layout, Cursor c,
			String[] from, int[] to) {
		super(context, layout, c, from, to);
		// TODO Auto-generated constructor stub
		db = context.openOrCreateDatabase(DBConstant.DB_FILENAME,
				context.MODE_PRIVATE, null);
		campusService=new CampusService(db);
		this.context = context;
		this.layout = layout;
	}

	@SuppressWarnings("deprecation")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		LayoutInflater lf = LayoutInflater.from(context);
		final int mPositon = position;
		View view = lf.inflate(layout, null);
		
		
		TextView textListContent,textListCategory;
		textListContent = (TextView) view
				.findViewById(R.id.textListContent);
		textListCategory = (TextView) view.findViewById(R.id.textListCategory);
		btn_state = (Button) view.findViewById(R.id.btn_state);
		btn_state.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String campusId=cursor.getString(cursor.getColumnIndex("campusid"));
				String flag=cursor.getString(cursor.getColumnIndex("campusstate"));
				if("Y".equals(flag)){
					campusService.updateCampusStatus(campusId,false);
				}else if("N".equals(flag)){
					campusService.updateCampusStatus(campusId,true);
				}else{
					Toast.makeText(context, "状态异常，请重试", Toast.LENGTH_SHORT).show();
				}
			}
		});
		campusListView=new CampusListView(textListContent, textListCategory, btn_state);
		view.setTag(campusListView);

		cursor = (Cursor) getItem(position);
		int CampusnameIndex = cursor.getColumnIndex("campusname");
		String Campusname = cursor.getString(CampusnameIndex);
		textListContent.setText(Campusname);

		int CampusidIndex = cursor.getColumnIndex("campusid");
		String Campusid = cursor.getString(CampusidIndex);
		
		list_campusid.add(Campusid);
		
		int CampusbyIndex = cursor.getColumnIndex("campusby");
		String Campusby = cursor.getString(CampusbyIndex);
	

		int CampustimeIndex = cursor.getColumnIndex("campustime");
		String Campustime = cursor.getString(CampustimeIndex);

		textListCategory.setText(Campusby+" "+Campustime);
		
		int CampusstateIndex = cursor.getColumnIndex("campusstate");
		String Campusstate = cursor.getString(CampusstateIndex);
		if(Campusstate.equals("N"))
		{
			btn_state.setText("未关注");
			btn_state.setBackgroundColor(context.getResources().getColor(R.color.main_bg));
		}
		else 
		{
			btn_state.setText("已关注");
			btn_state.setBackgroundColor(context.getResources().getColor(R.color.red));
		}
		
		return view;
	}
}
