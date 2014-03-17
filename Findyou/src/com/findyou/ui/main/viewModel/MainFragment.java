package com.findyou.ui.main.viewModel;
import com.findyou.R;
import com.findyou.data.Adapter.FriendsCursorAdapter;
import com.findyou.data.Adapter.LatestNewsCursorAdapter;
import com.findyou.domain.Service.UserInfoService;
import com.findyou.domain.Service.NewsService;
import com.findyou.ui.main.SlidingActivity;

import android.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class MainFragment extends Fragment{
	private ImageButton btn_show;
	private SQLiteDatabase db;

	private Cursor cursor;

	private NewsService newsService;
	private ListView lastet_news_list;
	private LatestNewsCursorAdapter latestNewsCursorAdapter;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		db =((SlidingActivity)getActivity()).getDb();
		newsService = new NewsService(db);

		
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.activity_latest_info, null);
		btn_show = (ImageButton) view.findViewById(R.id.btn_show);
		btn_show.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				((SlidingActivity) getActivity()).getSlidingMenu().showMenu();
			}
		});
		lastet_news_list = (ListView) view.findViewById(R.id.lastet_news_list);
		initLastetNewsList();
	
		return view;
	}
	public void initLastetNewsList() {
//		cursor = newsService.FindNews();
//		latestNewsCursorAdapter = new LatestNewsCursorAdapter(((SlidingActivity)getActivity()),
//				R.layout.item_friend_find, cursor, new String[] {
//						"newsUserName", "newsType", "newsContent",
//						"newsLocation", "newsTime" }, new int[] {
//						R.id.tv_friend_info_name, R.id.tv_style,
//						R.id.tv_location, R.id.tv_content,
//						R.id.tv_date });
//		lastet_news_list.setAdapter(latestNewsCursorAdapter);
	}

}
