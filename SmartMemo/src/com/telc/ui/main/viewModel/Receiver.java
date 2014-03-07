package com.telc.ui.main.viewModel;

import com.telc.data.dbDriver.DBConstant;
import com.telc.domain.Emtity.Periodic;
import com.telc.domain.Service.PeriodicService;
import com.telc.domain.Service.TimingService;
import com.telc.domain.time.Service.TimeService;
import com.telc.resource.remind.connentNet;
import com.telc.resource.remind.remindContent;
import com.telc.ui.Memos.TimingMemoActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Toast;

public class Receiver extends BroadcastReceiver{

	@Override
	public void onReceive(Context ctx, Intent intent) {
		// TODO Auto-generated method stub
		SQLiteDatabase db=ctx.openOrCreateDatabase(DBConstant.DB_FILENAME,TimingMemoActivity.MODE_PRIVATE, null);
		TimeService time=TimeService.getInstance();
		Bundle bundle=new Bundle();
		bundle=intent.getExtras();
		String user=bundle.getString("user");
		String content=bundle.getString("content");
		String index=bundle.getString("Id");
		String location=bundle.getString("location");
		String mclass=bundle.getString("class");
		if(location!=null&& !location.isEmpty()){
			content="到"+location;
		}
		if(mclass.equals("timing")){
			TimingService timing=new TimingService(db);
			timing.updateIsfinish(index);
		}else if(mclass.equals("periodic")){
			PeriodicService periodic=new PeriodicService(db);
			Periodic temp=periodic.findPeriodicById(index);
				if(temp.getPeriod().equals("每天")){
					long endTime=time.getSecondsFromDate(temp.getEnd_time());
					endTime=endTime+86400000l;
					temp.setEnd_time(time.longSwithToString(endTime));
				}else if(temp.getPeriod().equals("每月")){
					long endTime=time.getSecondsFromDate(temp.getEnd_time());
					endTime=endTime+2592000000l;
					temp.setEnd_time(time.longSwithToString(endTime));
				}
			periodic.updatePeriodic(temp);
		}
		remindContent.Content=content;
		remindContent.useId=user;
		new connentNet().start();
		Toast.makeText(ctx, "定时提醒已发出  To:  "+user+"   "+content, Toast.LENGTH_SHORT).show();
//		Log.e("alarm", String.valueOf(System.currentTimeMillis()));
	}

}
