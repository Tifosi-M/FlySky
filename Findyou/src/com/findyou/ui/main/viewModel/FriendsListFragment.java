package com.findyou.ui.main.viewModel;

import com.findyou.R;
import com.findyou.data.Adapter.FriendsCursorAdapter;
import com.findyou.domain.Service.FriendService;
import com.findyou.domain.Service.NewsService;
import com.findyou.ui.main.SlidingActivity;

import android.app.Fragment;
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
import android.widget.AdapterView.OnItemClickListener;

public class FriendsListFragment extends Fragment{
	private FriendService friendsService;
	private SQLiteDatabase db;
	private Cursor cursor;
	private ListView friends_list;
	private ImageButton btn_show;
	private FriendsCursorAdapter friendsCursorAdapter;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		db =((SlidingActivity)getActivity()).getDb();
		friendsService=new FriendService(db);

	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.activity_friends_list, null);
		friendsService=new FriendService(db);
		friends_list=(ListView) view.findViewById(R.id.friends_list);
		initFriendsList();
		
		btn_show = (ImageButton) view.findViewById(R.id.btn_show);
		btn_show.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				((SlidingActivity) getActivity()).getSlidingMenu().showMenu();
			}
		});
		return view;
	}
	
	public void initFriendsList()
	{
//		cursor=friendsService.FindFriends();
		friendsCursorAdapter = new FriendsCursorAdapter(((SlidingActivity)getActivity()),
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
