package com.findyou.ui.main;

import com.findyou.R;
import com.findyou.data.Adapter.FriendsCursorAdapter;
import com.findyou.data.Adapter.LatestNewsCursorAdapter;
import com.findyou.domain.Service.FriendsService;
import com.findyou.domain.Service.NewsService;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class FriendsListActivity extends Activity {
	private SQLiteDatabase db;
	private ListView friends_list;
	private FriendsCursorAdapter friendsCursorAdapter;
	private FriendsService friendsService;
	private Cursor cursor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_friends_list);
		db = openOrCreateDatabase("FindYou.db3", MODE_PRIVATE, null);
		friends_list=(ListView) findViewById(R.id.friends_list);
		friendsService=new FriendsService(db);
		initFriendsList();
	}
	public void initFriendsList()
	{
//		cursor=friendsService.FindFriends();
		friendsCursorAdapter = new FriendsCursorAdapter(this,
				R.layout.item_friends_list, cursor, new String[] {"FriendName"},
				new int[] {R.id.tv_card_list_item_name});
		friends_list.setAdapter(friendsCursorAdapter);
		friends_list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				
			}
		});
	}


}
