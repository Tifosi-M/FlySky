package com.telc.ui.main.viewModel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.telc.data.dbDriver.DBConstant;
import com.telc.domain.Service.CampusService;
import com.telc.smartmemo.R;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class CampusCursorAdapter extends SimpleCursorAdapter {
	private Context context;
	private int layout;
	private CampusListView  campusListView;
	private ListView list;
	public static List<String> list_campusid=new ArrayList<String>();
	private Button btn_state;
	private CampusService campusService=null;
	private Cursor cursor;
	private SQLiteDatabase db;
	private Handler messageHandler;

	
	@SuppressWarnings("deprecation")
	public CampusCursorAdapter(Context context, int layout, Cursor c,
			String[] from, int[] to,ListView list) {
		super(context, layout, c, from, to);
		// TODO Auto-generated constructor stub
		db = context.openOrCreateDatabase(DBConstant.CAMPUSDBFILENAME,
				context.MODE_PRIVATE, null);
		campusService=new CampusService(db);
		this.context = context;
		this.layout = layout;
		this.list=list;
	}

	@SuppressWarnings("deprecation")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		LayoutInflater lf = LayoutInflater.from(context);
		final int mPosition=position;
//		View view=null;
//		if(convertView!=null){
//			view=convertView;
//		}

		convertView = lf.inflate(layout, null);
		final int pp = position;

		
		TextView textListContent,textListCategory;
		textListContent = (TextView) convertView
				.findViewById(R.id.textListContent);
		textListCategory = (TextView) convertView.findViewById(R.id.textListCategory);
		btn_state = (Button) convertView.findViewById(R.id.btn_state);

		btn_state.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				// TODO Auto-generated method stub
				String campusId=list_campusid.get(mPosition);
				String flag=v.getTag().toString();
				if("Y".equals(flag)){
					campusService.updateCampusStatus(campusId,false);
				}else if("N".equals(flag)){
					campusService.updateCampusStatus(campusId,true);
				}else{
					Toast.makeText(context, "状态异常，请重试", Toast.LENGTH_SHORT).show();
				}
				
				int i = pp;
				
				Intent mIntent = new Intent("UPDATE_ADAPTER");
				context.sendBroadcast(mIntent);
				
			}
		});
		campusListView=new CampusListView(textListContent, textListCategory, btn_state);
		convertView.setTag(campusListView);

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
		btn_state.setTag(Campusstate);//aaa
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
		
		return convertView;
	}
	
		
}
