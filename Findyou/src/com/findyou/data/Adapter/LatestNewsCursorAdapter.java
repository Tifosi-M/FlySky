package com.findyou.data.Adapter;

import com.findyou.R;
import com.findyou.ui.main.viewModel.NewsListView;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class LatestNewsCursorAdapter extends SimpleCursorAdapter {
	private Context context;
	private int layout;
	private NewsListView newsListView;

	@SuppressWarnings("deprecation")
	public LatestNewsCursorAdapter(Context context, int layout, Cursor c,
			String[] from, int[] to) {
		super(context, layout, c, from, to);
		// TODO Auto-generated constructor stub
		this.context = context;
		this.layout = layout;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		LayoutInflater lf = LayoutInflater.from(context);
		View view = lf.inflate(layout, null);
		TextView tv_friend_info_name, tv_date, tv_style, tv_location, tv_content;

		tv_friend_info_name = (TextView) view
				.findViewById(R.id.tv_friend_info_name);
		tv_date = (TextView) view.findViewById(R.id.tv_date);
		tv_style = (TextView) view.findViewById(R.id.tv_style);
		tv_location = (TextView) view.findViewById(R.id.tv_location);
		tv_content = (TextView) view.findViewById(R.id.tv_content);

		newsListView = new NewsListView(tv_friend_info_name, tv_date, tv_style,
				tv_location, tv_content);

		view.setTag(newsListView);
		
		Cursor cursor = (Cursor) getItem(position);
		int newsUserNameIndex = cursor.getColumnIndex("NewsUserName");
		String newsUserName = cursor.getString(newsUserNameIndex);
		tv_friend_info_name.setText(newsUserName);

		int newsStyleIndex = cursor.getColumnIndex("NewsStyle");
		String newsStyle = cursor.getString(newsStyleIndex);
		tv_style.setText(newsStyle);
		
		int newsContentIndex = cursor.getColumnIndex("NewsContent");
		String newsContent = cursor.getString(newsContentIndex);
		tv_content.setText(newsContent);

		int newsLocationIndex = cursor.getColumnIndex("NewsLocation");
		String newsLocation = cursor.getString(newsLocationIndex);
		tv_location.setText(newsLocation);
		
		int newsTimeIndex = cursor.getColumnIndex("NewsTime");
		String newsTime = cursor.getString(newsTimeIndex);
		tv_date.setText(newsTime);

		return view;

	}
}
