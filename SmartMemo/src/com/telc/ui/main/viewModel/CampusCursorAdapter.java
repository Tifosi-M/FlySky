package com.telc.ui.main.viewModel;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Text;

import com.telc.smartmemo.R;

import android.content.Context;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class CampusCursorAdapter extends SimpleCursorAdapter {
	private Context context;
	private int layout;
	private CampusListView  campusListView;
	public static List<String> list_campusid=new ArrayList<String>();
	Button btn_state;
	@SuppressWarnings("deprecation")
	public CampusCursorAdapter(Context context, int layout, Cursor c,
			String[] from, int[] to) {
		super(context, layout, c, from, to);
		// TODO Auto-generated constructor stub
		this.context = context;
		this.layout = layout;
	}

	@SuppressWarnings("deprecation")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		LayoutInflater lf = LayoutInflater.from(context);
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
				
				int i = pp;
				
//				Toast.makeText(context,list_campusid.get(i) , Toast.LENGTH_SHORT).show();
				Toast.makeText(context,list_campusid.get(i)  + "    " + v.getTag().toString() , Toast.LENGTH_SHORT).show();

				
			}
		});
		
		
		
		campusListView=new CampusListView(textListContent, textListCategory, btn_state);
		convertView.setTag(campusListView);

		Cursor cursor = (Cursor) getItem(position);
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
