package com.telc.ui.main;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.SubMenu;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import com.telc.data.dbDriver.DBConstant;
import com.telc.domain.Emtity.Timing;
import com.telc.domain.Service.TimingService;
import com.telc.domain.Service.locationSettingService;
import com.telc.domain.time.Service.TimeService;
import com.telc.resource.baidumap.locationServiceInfoTran;
import com.telc.smartmemo.R;
import com.telc.ui.Memos.PeriodicMemoActivity;
import com.telc.ui.Memos.RealtimeMemoActivity;
import com.telc.ui.Memos.TimingMemoActivity;
import com.telc.ui.main.viewModel.MenuFragment;
import com.telc.ui.main.viewModel.UnfinishFragment;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.FragmentTransaction;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.widget.Toast;

/**
 * @author WWB
 *
 */
public class SlidingActivity extends SlidingFragmentActivity implements DBConstant {

	
	//创建启动  Service  的  Intent 
	final Intent intent =  new Intent("com.telc.domain.Service.locationService");
	SQLiteDatabase db;
	TimeService timeService=TimeService.getInstance();
	TimingService timing;
	private boolean isToggle=false;
	private boolean isClickBack=false;
//	intent.setAction("com.telc.domain.Service.locationService");
	
	public SharedPreferences sp;
	
	//系统设置
	SharedPreferences preferences;
	SharedPreferences.Editor editor;
	
	//声明一个NotificationManager类
	private NotificationManager notificationManager;
//	//定位相关
//	private LocationClient mLocClient;
//	private LocationData locData =  null;

	//linshi yong lei bofang
//	private MediaPlayer mediaPlayer;

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setTitle("智慧备忘录");//设置备忘录标题
		
		setContentView(R.layout.activity_unfinish);
		sp=getSharedPreferences("Login",MODE_PRIVATE);
		setBehindContentView(R.layout.activity_menu);//设置侧边的布局文件
        //Fragment事件开始
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        //创建两个fragment
        MenuFragment menuFragment = new MenuFragment();
        UnfinishFragment unfinishFragment=new UnfinishFragment();
        //设置对应的framelayout的ID
        fragmentTransaction.replace(R.id.menu, menuFragment);
        fragmentTransaction.replace(R.id.unfinish, unfinishFragment);
//        提交事务
        fragmentTransaction.commit();
        
//        设置滑动菜单的属性
		SlidingMenu sm= getSlidingMenu();
		sm.setShadowWidth(15);
		sm.setShadowDrawable(R.drawable.sliding_shadow);
		sm.setBehindOffset(130);//侧边剩余距离
//		设置抽屉弹出模式
		sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		
		getActionBar().setDisplayHomeAsUpEnabled(true);//让actionbar为可点击
		
		//初始化NotificationManager对象
		notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);

		locationServiceInfoTran.canBeDestroy = false;
		
		preferences = getSharedPreferences("setInfo", MODE_PRIVATE);
		editor = preferences.edit();
		MyGetSettingInfo();

		startService(intent);
	}

	public void MyGetSettingInfo(){

		int settingflag = preferences.getInt("flag", 0);
		
		if(settingflag == 1){
			
			locationSettingService.MY_PROVINCE = preferences.getString("mprovince", null);

			locationSettingService.MY_CITY = preferences.getString("mCity", null);

			locationSettingService.MY_REMINDDISTINCE = preferences.getInt("mRemindDistance", 0);

			locationSettingService.MY_LOCATIONTIME = preferences.getInt("mLocationTime", 0);
		}
		
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		
		startService(intent);

		MyGetSettingInfo();
		
		super.onResume();
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
    	SubMenu plusMenu=menu.addSubMenu("新建备忘录");//添加按钮名称
        plusMenu.add(0,1,0,"新建实时提醒");//添加下拉Item
        plusMenu.add(0,2,0,"新建定时提醒");
        plusMenu.add(0,3,0,"新建周期性提醒");
        
        //实例化按钮
        MenuItem menuItem=plusMenu.getItem();
        menuItem.setIcon(android.R.drawable.ic_input_add);
        menuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS | MenuItem.SHOW_AS_ACTION_WITH_TEXT);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	Intent intent;
            if (item.getItemId() == android.R.id.home) {
				//获取闹钟管理器
//				AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
				//设置闹钟
//				alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+5000, pendingIntent);
//				alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+86400000, 10*1000, pendingIntent);
//				alarmManager.cancel(pendingIntent);
				//toggle就是程序自动判断是打开还是关闭
                toggle();
                return false;
            }else {
            	switch (item.getItemId()) {
            	case 1:
		           intent=new Intent(SlidingActivity.this,RealtimeMemoActivity.class);
		           startActivity(intent);
		           Toast.makeText(this,item.getTitle() ,Toast.LENGTH_SHORT).show();
	            	return true;
            	case 2:
            		intent=new Intent(SlidingActivity.this,TimingMemoActivity.class);
 		           	startActivity(intent);
            		Toast.makeText(this,item.getTitle() ,Toast.LENGTH_SHORT).show();
	            	return true;
            	case 3:
            		intent=new Intent(SlidingActivity.this,PeriodicMemoActivity.class);
 		           	startActivity(intent);
 		           	Toast.makeText(this,item.getTitle() ,Toast.LENGTH_SHORT).show();
	            	return true;
	            default:
	            		return false;
            }
         }
    }

    //z 
    private void setNotiType(int iconID,String text){
    	
    	PendingIntent contentintent = PendingIntent.getActivity(getApplicationContext(), 0, getIntent(), 0);
    	
    	Notification notification = new Notification(iconID,"我的通知栏	",System.currentTimeMillis());
    	
    	notification.flags |= Notification.FLAG_ONGOING_EVENT;

    	notification.setLatestEventInfo(getApplicationContext(), "智慧备忘录", text, contentintent);
    
    	notificationManager.notify(1, notification);
    }
    
    
    public  Dialog buildDialog(Context context){
    	AlertDialog.Builder builder = new AlertDialog.Builder(context);
    
    	builder.setTitle("退出");
    	builder.setPositiveButton("退出并提醒", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				
				int ic = R.drawable.z_ic_launcher;
				setNotiType(ic, "点击查看备忘录");

				//后台运行
//				moveTaskToBack(false);
				
				//销毁Activity
				SlidingActivity.this.finish();

				//后台定位服务不可被销毁
				locationServiceInfoTran.canBeDestroy = false;
				//开启定位服务
//				startService(intent);

			}
		});
    	
    	builder.setNegativeButton("直接退出", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				notificationManager.cancelAll();
				
				//关闭后台的定位服务
				locationServiceInfoTran.canBeDestroy = true;
				stopService(intent);
				
				SlidingActivity.this.finish();
			}
		});
		return builder.create();
    }
    
    
    
	@Override
	protected void onPrepareDialog(int id, Dialog dialog) {
		// TODO Auto-generated method stub
		super.onPrepareDialog(id, dialog);
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		// TODO Auto-generated method stub
		if(id == 0x112233){
			return this.buildDialog(SlidingActivity.this);
		}else{
			return null;
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			 if(!isClickBack){
				 isClickBack = true;
                 Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();   
                 new Handler().postDelayed(new Runnable() {
                        @Override  
                        public void run() {
                        	isClickBack = false;  
                        }  
                    }, 2000);  
                 return true;
             }else {  
                 showDialog(0x112233);
                 return false;
             } 
			 
		}
		return false;
	}
}
