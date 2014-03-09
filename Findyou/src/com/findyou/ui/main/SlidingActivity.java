package com.findyou.ui.main;

import android.app.FragmentTransaction;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.findyou.R;
import com.findyou.ui.main.viewModel.FriendsListFragment;
import com.findyou.ui.main.viewModel.MainFragment;
import com.findyou.ui.main.viewModel.MenuFragment;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

public class SlidingActivity extends SlidingFragmentActivity {
	public MenuFragment menuFragment;
	public MainFragment mainFragment;
	public FriendsListFragment friendsListFragment;
	public FragmentTransaction fragmentTransaction;
	SlidingMenu sm;
	SQLiteDatabase db;
	public String flag;
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_latest_info);
		setBehindContentView(R.layout.activity_menu);
		flag="activity_latest_info";
		fragmentTransaction = getFragmentManager()
				.beginTransaction();
		menuFragment = new MenuFragment();
		mainFragment = new MainFragment();
		friendsListFragment=new FriendsListFragment();
		fragmentTransaction.replace(R.id.main, mainFragment);
		fragmentTransaction.replace(R.id.menu, menuFragment);
		fragmentTransaction.commit();
		sm = getSlidingMenu();
		sm.setBehindOffset(130);
		sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		sm.setMode(SlidingMenu.LEFT);
		db = openOrCreateDatabase("findyou.db",
				MODE_PRIVATE, null);
		setDb(db);
	}
	public SQLiteDatabase getDb() {
		return db;
	}
	public void setDb(SQLiteDatabase db) {
		this.db = db;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	
}
