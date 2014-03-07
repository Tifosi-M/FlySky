package com.telc.ui.main.viewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.telc.data.dbDriver.DBConstant;
import com.telc.domain.Emtity.Periodic;
import com.telc.domain.Emtity.RealTime;
import com.telc.domain.Emtity.Timing;
import com.telc.domain.Service.PeriodicService;
import com.telc.domain.Service.RealTimeService;
import com.telc.domain.Service.TimingService;
import com.telc.domain.time.Service.TimeService;
import com.telc.smartmemo.R;
import com.telc.ui.Memos.PeriodicMemoDelActivity;
import com.telc.ui.Memos.RealtimeMemoDelActivity;
import com.telc.ui.Memos.TimingMemoActivity;
import com.telc.ui.Memos.TimingMemoDelActivity;
import com.telc.ui.main.SlidingActivity;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.SimpleAdapter;
import android.widget.SimpleAdapter.ViewBinder;
import android.widget.TextView;
import android.widget.Toast;

public class UnfinishFragment extends Fragment {

	// 数据库
	private SQLiteDatabase db;
	private TimingService timingService;
	private RealTimeService realTimeService;
	private PeriodicService periodicService;
	private SharedPreferences sp;// 用来获取xml保存的useiId
	View view = null;

	private TextView textListCategory;
	int color;
	public TimeService timService;
	String category;
	Drawable drawable;
	ListView uncompleteList;
	// 保存list中的item的列表
	List<Map<String, Object>> mList;
	// listView适配器
	SimpleAdapter mAdapter = null;
	// 适配器中的key
	String[] from = { "textListCategory", "ratingBarListItem",
			"textListContent"};
	// value
	int[] to = { R.id.textListCategory, R.id.ratingBarListItem,
			R.id.textListContent};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		timService = TimeService.getInstance();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		view = inflater.inflate(R.layout.activity_unfinish, null);

		uncompleteList = (ListView) view.findViewById(R.id.listViewUnfinish);
		sp = getActivity().getSharedPreferences("Login",
				getActivity().MODE_PRIVATE);
		// 实例化Adapter
		initAdapert();
		if (mAdapter != null)
			uncompleteList.setAdapter(mAdapter);

		// listView中Item的监听
		uncompleteList
				.setOnItemLongClickListener(new OnItemLongClickListener() {
					@Override
					public boolean onItemLongClick(AdapterView<?> parent,
							View view, int position, long id) {
						// TODO Auto-generated method stub
						String itemValue = parent.getItemAtPosition(position)
								.toString();
						String[] itemSplit = itemValue.split(",");
						final String index = itemSplit[2].substring(11);
						String categoryString = itemSplit[1].substring(18);
						final CharSequence[] items = { "标记为已完成" };
						TextView textSign;
						if (categoryString.compareTo("实时提醒") == 0) {
							// TODO Auto-generated method stub
							final Dialog mDialog = new Dialog(getActivity());
							mDialog.setTitle("更多操作：");
							LayoutInflater inflater = LayoutInflater
									.from(getActivity());
							final View dialogView = inflater.inflate(
									R.layout.sign_finish_dialog, null);
							textSign = (TextView) dialogView
									.findViewById(R.id.textSignFinish);
							textSign.setOnClickListener(new OnClickListener() {
								@Override
								public void onClick(View arg0) {
									// TODO Auto-generated method stub
									realTimeService.updateIsfinish(index);
									Toast.makeText(getActivity(), "标记成功！",
											Toast.LENGTH_SHORT).show();
									initAdapert();
									uncompleteList.setAdapter(mAdapter);
									mDialog.dismiss();
								}
							});
							mDialog.setContentView(dialogView);
							mDialog.show();
						} else if (categoryString.compareTo("定时提醒") == 0) {
							// TODO Auto-generated method stub
							final Dialog mDialog = new Dialog(getActivity());
							mDialog.setTitle("更多操作：");
							LayoutInflater inflater = LayoutInflater
									.from(getActivity());
							final View dialogView = inflater.inflate(
									R.layout.sign_finish_dialog, null);
							textSign = (TextView) dialogView
									.findViewById(R.id.textSignFinish);
							textSign.setOnClickListener(new OnClickListener() {
								@Override
								public void onClick(View arg0) {
									// TODO Auto-generated method stub
									timingService.updateIsfinish(index);
									Toast.makeText(getActivity(), "标记成功！",
											Toast.LENGTH_SHORT).show();
									initAdapert();
									uncompleteList.setAdapter(mAdapter);
									mDialog.dismiss();
								}
							});
							mDialog.setContentView(dialogView);
							mDialog.show();
						}
						// else if(categoryString.compareTo("周期性提醒")==0){
						// // TODO Auto-generated method stub
						// final Dialog mDialog = new Dialog(getActivity());
						// mDialog.setTitle("更多操作：");
						// LayoutInflater inflater =
						// LayoutInflater.from(getActivity());
						// final View dialogView =
						// inflater.inflate(R.layout.sign_finish_dialog,
						// null);
						// textSign=(TextView)
						// dialogView.findViewById(R.id.textSignFinish);
						// textSign.setOnClickListener(new OnClickListener()
						// {
						// @Override
						// public void onClick(View arg0) {
						// // TODO Auto-generated method stub
						// periodicService.updateIsfinish(index);
						// Toast.makeText(getActivity(), "标记成功！",
						// Toast.LENGTH_SHORT).show();
						// initAdapert();
						// uncompleteList.setAdapter(mAdapter);
						// mDialog.dismiss();
						// }
						// });
						// mDialog.setContentView(dialogView);
						// mDialog.show();
						// }
						return true;
					}
				});

		// listView中Item的监听
		uncompleteList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				String itemValue = arg0.getItemAtPosition(position).toString();
				String[] itemSplit = itemValue.split(",");
				String index = itemSplit[2].substring(11);
				String categoryString = itemSplit[1].substring(18);

				if (categoryString.compareTo("实时提醒") == 0) {
					Bundle bundle = new Bundle(); // 创建Bundle对象
					bundle.putString("index", index);
					Intent intent = new Intent((SlidingActivity) getActivity(),
							RealtimeMemoDelActivity.class);
					intent.putExtras(bundle);
					startActivity(intent);
				} else if (categoryString.compareTo("定时提醒") == 0) {
					Bundle bundle = new Bundle(); // 创建Bundle对象
					bundle.putString("index", index);
					Intent intent = new Intent((SlidingActivity) getActivity(),
							TimingMemoDelActivity.class);
					intent.putExtras(bundle);
					startActivity(intent);
				} else if (categoryString.compareTo("周期性提醒") == 0) {
					Bundle bundle = new Bundle(); // 创建Bundle对象
					bundle.putString("index", index);
					Intent intent = new Intent((SlidingActivity) getActivity(),
							PeriodicMemoDelActivity.class);
					intent.putExtras(bundle);
					startActivity(intent);
				} else {
					return;
				}
			}
		});

		return view;
	}

	private void initAdapert() {
		// TODO Auto-generated method stub

		db = getActivity().openOrCreateDatabase(DBConstant.DB_FILENAME,
				getActivity().MODE_PRIVATE, null);
		// 实例化数据库服务
		timingService = new TimingService(db);
		realTimeService = new RealTimeService(db);
		periodicService = new PeriodicService(db);
		// 获取userId
		String userId = sp.getString("user", null);
		// 数据库中获取的List<Timing>

		List<Timing> timingList = timingService.getTimingByUserID(userId);
		List<RealTime> realList = realTimeService.getRealTimeByUserID(userId);
		List<Periodic> perioList = periodicService.getPeriodicByUserID(userId);

		mList = new ArrayList<Map<String, Object>>();

		if (timingList != null) {
			Collections.sort(timingList, new Comparator<Timing>() {
				@Override
				public int compare(Timing lhs, Timing rhs) {
					String timingStartTime1 = lhs.getStart_time();
					String timingStartTime2 = rhs.getStart_time();
					if (lhs.getPriority() < rhs.getPriority()) {
						return 1;
					} else if (lhs.getPriority() == rhs.getPriority()) {
						if (timingStartTime1.compareTo(timingStartTime2) > 0) {
							return 1;
						} else {
							return -1;
						}
					} else {
						return -1;
					}
				}
			});
		}

		if (perioList != null) {
			Collections.sort(perioList, new Comparator<Periodic>() {

				@Override
				public int compare(Periodic lhs, Periodic rhs) {
					String periodicStartTime1 = lhs.getStart_time();
					String periodicStartTime2 = rhs.getStart_time();
					if (lhs.getPriority() < rhs.getPriority()) {
						return 1;
					} else if (lhs.getPriority() == rhs.getPriority()) {
						if (periodicStartTime1.compareTo(periodicStartTime2) > 0) {
							return 1;
						} else {
							return -1;
						}
					} else {
						return -1;
					}
				}
			});
		}

		if (realList != null) {
			Collections.sort(realList, new Comparator<RealTime>() {

				@Override
				public int compare(RealTime lhs, RealTime rhs) {
					String realStartTime1 = lhs.getStart_time();
					String realStartTime2 = rhs.getStart_time();
					if (lhs.getPriority() < rhs.getPriority()) {
						return 1;
					} else if (lhs.getPriority() == rhs.getPriority()) {
						if (realStartTime1.compareTo(realStartTime2) > 0) {
							return 1;
						} else {
							return -1;
						}
					} else {
						return -1;
					}
				}
			});
		}

		if (realList != null) {
			// 实时提醒迭代器
			RealTime tempRealTime;
			Iterator itRealTime = realList.iterator();
			while (itRealTime.hasNext()) {
				tempRealTime = (RealTime) itRealTime.next();
				if (tempRealTime.getIsfinish() == 0) {
					Map<String, Object> mListItem = new HashMap<String, Object>();
					String temp;
					mListItem.put("textListCategory", "实时提醒");
					mListItem.put("ratingBarListItem",
							(float) tempRealTime.getPriority());
					if (tempRealTime.getContent().length() <= 10) {
						temp = tempRealTime.getContent();
					} else {
						temp = tempRealTime.getContent().substring(0, 10)
								+ "……";
					}
					mListItem.put("textListContent", temp);
					mListItem.put("textIndex", tempRealTime.getReal_id());
					mList.add(mListItem);
				}
			}
		}

		if (timingList != null) {
			Timing tempTiming;
			// 定时提醒迭代器
			Iterator itTiming = timingList.iterator();
			while (itTiming.hasNext()) {
				tempTiming = (Timing) itTiming.next();
				if (tempTiming.getIsfinish() == 0) {
					Map<String, Object> mListItem = new HashMap<String, Object>();
					String temp;
					mListItem.put("textListCategory", "定时提醒");
					mListItem.put("ratingBarListItem",
							(float) tempTiming.getPriority());
					if (tempTiming.getContent().length() <= 10) {
						temp = tempTiming.getContent();
					} else {
						temp = tempTiming.getContent().substring(0, 10) + "……";
					}
					mListItem.put("textListContent", temp);
					mListItem.put("textIndex", tempTiming.getTiming_id());

					mList.add(mListItem);
				}
			}
		}

		if (perioList != null) {
			Periodic tempPreiodic;
			// 周期性提醒迭代器
			Iterator itPeriodic = perioList.iterator();
			while (itPeriodic.hasNext()) {
				tempPreiodic = (Periodic) itPeriodic.next();
				Map<String, Object> mListItem = new HashMap<String, Object>();
				String temp;
				mListItem.put("textListCategory", "周期性提醒");
				mListItem.put("ratingBarListItem",
						(float) tempPreiodic.getPriority());
				if (tempPreiodic.getContent().length() <= 10) {
					temp = tempPreiodic.getContent();
				} else {
					temp = tempPreiodic.getContent().substring(0, 10) + "……";
				}
				mListItem.put("textListContent", temp);
				mListItem.put("textIndex", tempPreiodic.getPeriodic_id());
				mList.add(mListItem);
			}
		}
		mAdapter = new SimpleAdapter(getActivity(), mList,
				R.layout.listview_layout, from, to);

		// 重写Adapter支持RatingBar
		mAdapter.setViewBinder(new ViewBinder() {
			@Override
			public boolean setViewValue(View view, Object data,
					String textRepresentation) {
				// TODO Auto-generated method stub

				
				if (view.getId() == R.id.textListCategory) {
					category = (String) data;
					if (category.equals("定时提醒")) {
						color = getResources().getColor(R.color.red);
						drawable = getResources().getDrawable(
								R.drawable.alarm_icon_getup);
					} else if (category.equals("实时提醒")) {
						color = getResources().getColor(R.color.red);
						drawable = getResources().getDrawable(
								R.drawable.alarm_icon_normal);
					} else if (category.equals("周期性提醒")) {
						color = getResources().getColor(R.color.red);
						drawable = getResources().getDrawable(
						R.drawable.alarm_icon_monthly);
					}
					TextView textListCategory = (TextView) view;
					textListCategory.setTextColor(color);
					textListCategory.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);
					
				}

//				if (view.getId() == R.id.imageView1_type) {
//					if (category.equals("定时提醒")) {
//						drawable = getResources().getDrawable(
//								R.drawable.alarm_icon_getup);
//					} else if (category.equals("实时提醒")) {
//						drawable = getResources().getDrawable(
//								R.drawable.alarm_icon_normal);
//					} else if (category.equals("周期性提醒")) {
//						drawable = getResources().getDrawable(
//								R.drawable.alarm_icon_monthly);
//					}
//					ImageView imageView1_type = (ImageView) view;
//					imageView1_type.setImageDrawable(drawable);
//
//				}

				if (view.getId() == R.id.ratingBarListItem) {
					Float value = (Float) data;
					float ratingValue = value.floatValue();
					RatingBar ratingBar = (RatingBar) view;
					ratingBar.setRating(ratingValue);

					return true;
				} else
					return false;
			}
		});
		memosRemind();
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		 mAdapter = null;
		 initAdapert();
//		 memosRemind();
		 if (mAdapter != null) {
		 uncompleteList.setAdapter(mAdapter);
		 }
	}

	public void memosRemind() {
		int tSize,pSize;
		List<Timing> timingList = timingService.
				getTimingByUserID(sp.getString("user", null));
		List<Periodic> periodicList=periodicService.
				getPeriodicByUserID(sp.getString("user", null));
		if (timingList != null) {
			Collections.sort(timingList, new Comparator<Timing>() {
				@Override
				public int compare(Timing lhs, Timing rhs) {
					long timingEndtime1 = timService.getSecondsFromDate(lhs
							.getEnd_time());
					long timingEndtime2 = timService.getSecondsFromDate(rhs
							.getEnd_time());
					if (timingEndtime1 <= timingEndtime2) {
						return -1;
					} else
						return 1;
				}
			});
		}
			
		if (periodicList != null) {
			Collections.sort(periodicList, new Comparator<Periodic>() {
				@Override
				public int compare(Periodic lhs, Periodic rhs) {
					long periodicEndtime1 = timService.getSecondsFromDate(lhs.getEnd_time());
					long periodicEndtime2 = timService.getSecondsFromDate(rhs.getEnd_time());
					if (periodicEndtime1<=periodicEndtime2) {
						return -1;
					} else 
						return 1;
					}
			});
		}
			if(timingList!=null&&periodicList!=null){
				tSize=timingList.size();
				pSize=periodicList.size();
			}else if(timingList!=null&&periodicList==null){
				tSize=timingList.size();
				pSize=0;
			}else if(timingList==null&&periodicList!=null){
				tSize=0;
				pSize=periodicList.size();
			}else{
				tSize=0;
				pSize=0;
			}
			if(0!=tSize&&0!=pSize){
				for (int i = 1; i <= tSize; i++) {
					if(timingList.get(i-1).getIsfinish()==0){
						if(timService.getSecondsFromDate(periodicList.get(0).getEnd_time())>
								timService.getSecondsFromDate(timingList.get(i-1).getEnd_time())){
							String content = timingList.get(i - 1).getContent();
							String userId = sp.getString("user", null);
							long endTime = timService.getSecondsFromDate(timingList
									.get(i - 1).getEnd_time());
		
							Intent timingAlarm = new Intent(getActivity(),
									Receiver.class);
							Bundle bund = new Bundle();
							bund.putString("class", "timing");
							bund.putString("user", userId);
							bund.putString("content", content);
							bund.putString("Id", timingList.get(i - 1)
									.getTiming_id());
							if (timingList.get(i - 1).getLocation().isEmpty()) {
								bund.putString("location", null);
							} else {
								bund.putString("location", timingList.get(i - 1)
										.getLocation());
							}
							timingAlarm.putExtras(bund);
							PendingIntent pendingIntent = PendingIntent.getBroadcast(
									getActivity(), 0, timingAlarm,
									PendingIntent.FLAG_UPDATE_CURRENT);
							AlarmManager timingManager = (AlarmManager) getActivity()
									.getSystemService(getActivity().ALARM_SERVICE);
							timingManager.set(AlarmManager.RTC_WAKEUP, endTime,
									pendingIntent);
							return;
						}else if(periodicList!=null){
							String content = periodicList.get(0).getContent();
							String userId = sp.getString("user", null);
							long endTime = timService.getSecondsFromDate(periodicList.get(0).getEnd_time());
							
							Intent timingAlarm = new Intent(getActivity(),
									Receiver.class);
							Bundle bund = new Bundle();
							bund.putString("class", "periodic");
							bund.putString("user", userId);
							bund.putString("content", content);
							bund.putString("Id", periodicList.get(0).getPeriodic_id());
							bund.putString("location", null);
							timingAlarm.putExtras(bund);
							PendingIntent pendingIntent = PendingIntent.getBroadcast(
									getActivity(), 0, timingAlarm,
									PendingIntent.FLAG_UPDATE_CURRENT);
							AlarmManager timingManager = (AlarmManager) getActivity()
									.getSystemService(getActivity().ALARM_SERVICE);
							timingManager.set(AlarmManager.RTC_WAKEUP, endTime,
									pendingIntent);
							return;
						}
					}else {
						Intent timingAlarm = new Intent(getActivity(),
								Receiver.class);
						PendingIntent pendingIntent = PendingIntent.getBroadcast(
								getActivity(), 0, timingAlarm,
								PendingIntent.FLAG_UPDATE_CURRENT);
						AlarmManager timingManager = (AlarmManager) getActivity()
								.getSystemService(getActivity().ALARM_SERVICE);
						timingManager.cancel(pendingIntent);
						}
				}
			}else if(0!=pSize){
				String content=periodicList.get(0).getContent();
				String userId=sp.getString("user", null);
				long endTime= timService.getSecondsFromDate(periodicList.get(0).getEnd_time());
				
				Intent timingAlarm=new Intent(getActivity(),Receiver.class);
				Bundle bund=new Bundle();
				bund.putString("user", userId);
				bund.putString("class", "periodic");
				bund.putString("content", content);
				bund.putString("Id", periodicList.get(0).getPeriodic_id());
				timingAlarm.putExtras(bund);
				PendingIntent pendingIntent = PendingIntent.getBroadcast(getActivity(), 0, timingAlarm, PendingIntent.FLAG_UPDATE_CURRENT);
				AlarmManager timingManager=(AlarmManager) getActivity().getSystemService(getActivity().ALARM_SERVICE);
				timingManager.set(AlarmManager.RTC_WAKEUP, endTime, pendingIntent);
				return;
			}else if(0!=tSize){
				for(int i=0;i<tSize;i++){
				if (timingList.get(i).getIsfinish() == 0) {
					String content = timingList.get(i).getContent();
					String userId = sp.getString("user", null);
					long endTime = timService.getSecondsFromDate(timingList
							.get(i).getEnd_time());

					Intent timingAlarm = new Intent(getActivity(),
							Receiver.class);
					Bundle bund = new Bundle();
					bund.putString("class", "timing");
					bund.putString("user", userId);
					bund.putString("content", content);
					bund.putString("Id", timingList.get(i).getTiming_id());
					if (timingList.get(i).getLocation().isEmpty()) {
						bund.putString("location", null);
					} else {
						bund.putString("location", timingList.get(i)
								.getLocation());
					}
					timingAlarm.putExtras(bund);
					PendingIntent pendingIntent = PendingIntent.getBroadcast(
							getActivity(), 0, timingAlarm,
							PendingIntent.FLAG_UPDATE_CURRENT);
					AlarmManager timingManager = (AlarmManager) getActivity()
							.getSystemService(getActivity().ALARM_SERVICE);
					timingManager.set(AlarmManager.RTC_WAKEUP, endTime,
							pendingIntent);
					return;
				}
				}
			}
		}

	
//	public void PeriodicRemind(){
//		List<Periodic> periodicList=periodicService.getPeriodicByUserID(sp.getString("user", null));
//		if (periodicList != null) {
//			Collections.sort(periodicList, new Comparator<Periodic>() {
//				@Override
//				public int compare(Periodic lhs, Periodic rhs) {
//					long periodicEndtime1 = timService.getSecondsFromDate(lhs.getEnd_time());
//					long periodicEndtime2 = timService.getSecondsFromDate(rhs.getEnd_time());
//					if (periodicEndtime1<=periodicEndtime2) {
//						return -1;
//					} else 
//						return 1;
//				}
//			});
//			for(int i=0;i<periodicList.size();i++){
//				if(periodicList.get(i).getIsfinish()==0){
//					String content=periodicList.get(i).getContent();
//					String userId=sp.getString("user", null);
//					long endTime= timService.getSecondsFromDate(periodicList.get(i).getEnd_time());
//					
//					Intent timingAlarm=new Intent(getActivity(),Receiver.class);
//					Bundle bund=new Bundle();
//					bund.putString("user", userId);
//					bund.putString("content", content);
//					bund.putString("periodicid", periodicList.get(i).getPeriodic_id());
//					timingAlarm.putExtras(bund);
//					PendingIntent pendingIntent = PendingIntent.getBroadcast(getActivity(), 0, timingAlarm, PendingIntent.FLAG_UPDATE_CURRENT);
//					AlarmManager timingManager=(AlarmManager) getActivity().getSystemService(getActivity().ALARM_SERVICE);
//					timingManager.set(AlarmManager.RTC_WAKEUP, endTime, pendingIntent);
//					return;
//				}
//			}
//		}
//	}
	

}
