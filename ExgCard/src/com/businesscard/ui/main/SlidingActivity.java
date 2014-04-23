package com.businesscard.ui.main;

import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.widget.ImageButton;
import android.widget.Toast;

import com.businesscard.mobile.R;
import com.businesscard.ui.main.viewModel.MainFragment;
import com.businesscard.ui.main.viewModel.MenuFragment;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

public class SlidingActivity extends SlidingFragmentActivity {

	MenuFragment menuFragment;
	MainFragment mainFragment;
	ImageButton btn_show;
	SlidingMenu sm;
	String get_userPhone;
	public SharedPreferences sp;
	private boolean isClickBack=false;
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		SharedPreferences sharedPreferences = getSharedPreferences("current_user",
				MODE_PRIVATE);
		get_userPhone=sharedPreferences.getString("userPhone","");
		setUserPhone(get_userPhone);
		setContentView(R.layout.activity_main);
		setBehindContentView(R.layout.activity_menu);
		sp = getSharedPreferences("Login", MODE_PRIVATE);
		// Fragment�¼���ʼ
		FragmentTransaction fragmentTransaction = getFragmentManager()
				.beginTransaction();
		// ��������fragment
		menuFragment = new MenuFragment();
		mainFragment = new MainFragment();

		// ���ö�Ӧ��framelayout��ID
		fragmentTransaction.replace(R.id.menu, menuFragment);
		fragmentTransaction.replace(R.id.main, mainFragment);
		fragmentTransaction.commit();
		// ���û����˵�������
		sm = getSlidingMenu();
		// sm.setShadowWidth(15);
		// sm.setShadowDrawable(R.drawable.sliding_shadow);
		sm.setBehindOffset(130);// ���ʣ�����
		// ���ó��뵯��ģʽ
		sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		sm.setMode(SlidingMenu.RIGHT);
	}

	public SQLiteDatabase openDataBase() {
		SQLiteDatabase db = openOrCreateDatabase(get_userPhone + ".db3",
				MODE_PRIVATE, null);
		return db;
	}
	
	public void setUserPhone(String userPhone)
	{
		this.get_userPhone=get_userPhone;
	}
	
	public String getUserPhone()
	{
		return get_userPhone;
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			 if(!isClickBack){
				 isClickBack = true;
                 Toast.makeText(this, "再次按返回键退出", Toast.LENGTH_SHORT).show();   
                 new Handler().postDelayed(new Runnable() {
                        @Override  
                        public void run() {
                        	isClickBack = false;  
                        }  
                    },1500);  
                 return true;
             }else {  
                 finish();
                 return false;
             } 
			 
		}
		return false;
	}
	
	public MainFragment getMainFragment()
	{
		return mainFragment;
	}
}
