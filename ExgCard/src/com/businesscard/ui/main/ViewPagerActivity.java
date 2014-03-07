/**
 * Program  : ViewPagerActivity.java
 * Author   : qianj
 * Create   : 2012-5-31 涓嬪�?:02:15
 */

package com.businesscard.ui.main;

import java.util.ArrayList;
import java.util.List;
import com.businesscard.mobile.R;
import android.app.Activity;
import android.app.LocalActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TextView;

public class ViewPagerActivity extends Activity {

	List<View> listViews;
	ImageButton btnBack;
	Context context = null;
	ViewPagerActivity vpa;
	@SuppressWarnings("deprecation")
	LocalActivityManager manager = null;
	TextView tvTab1, tvTab2, tvTab3;
	TabHost tabHost = null;
	private ViewPager pager = null;
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.viewpager);
		vpa = this;
		manager = new LocalActivityManager(this, true);
		manager.dispatchCreate(savedInstanceState);
		btnBack=(ImageButton) findViewById(R.id.viewpagerBtnBack);
		tabHost = (TabHost) findViewById(R.id.tabhost);
		tabHost.setup();
		tabHost.setup(manager);

		context = ViewPagerActivity.this;

		pager = (ViewPager) findViewById(R.id.viewpager);

		listViews = new ArrayList<View>();

		Intent i1 = new Intent(context, NormalCardActivity.class);
		listViews.add(getView("NormalCardActivity", i1));
		Intent i2 = new Intent(context, BusinessCardActivity.class);
		listViews.add(getView("BusinessCardActivity", i2));
		Intent i3 = new Intent(context, ContactActivity.class);
		listViews.add(getView("ContactActivity", i3));
		RelativeLayout tabIndicator1 = (RelativeLayout) LayoutInflater.from(
				this).inflate(R.layout.tabwidget, null);
		tvTab1 = (TextView) tabIndicator1.findViewById(R.id.tv_title);
		tvTab1.setText("个人名片");
		tvTab1.setTextColor(vpa.getResources().getColor(R.color.main_red));
		RelativeLayout tabIndicator2 = (RelativeLayout) LayoutInflater.from(
				this).inflate(R.layout.tabwidget, null);
		tvTab2 = (TextView) tabIndicator2.findViewById(R.id.tv_title);
		tvTab2.setText("商家名片");

		RelativeLayout tabIndicator3 = (RelativeLayout) LayoutInflater.from(
				this).inflate(R.layout.tabwidget, null);
		tvTab3 = (TextView) tabIndicator3.findViewById(R.id.tv_title);
		tvTab3.setText("通讯录");

		tabHost.addTab(tabHost.newTabSpec("A").setIndicator(tabIndicator1)
				.setContent(i1));
		tabHost.addTab(tabHost.newTabSpec("B").setIndicator(tabIndicator2)
				.setContent(i2));
		tabHost.addTab(tabHost.newTabSpec("C").setIndicator(tabIndicator3)
				.setContent(i3));

	
				tabHost.setOnTabChangedListener(new OnTabChangeListener() {
					@Override
					public void onTabChanged(String tabId) {
						if ("A".equals(tabId)) {
							tvTab1.setTextColor(vpa.getResources().getColor(
									R.color.main_red));
							tvTab2.setTextColor(vpa.getResources().getColor(
									R.color.black));
							tvTab3.setTextColor(vpa.getResources().getColor(
									R.color.black));
							pager.setCurrentItem(0);

						}
						if ("B".equals(tabId)) {
							tvTab1.setTextColor(vpa.getResources().getColor(
									R.color.black));
							tvTab2.setTextColor(vpa.getResources().getColor(
									R.color.main_red));
							tvTab3.setTextColor(vpa.getResources().getColor(
									R.color.black));
							pager.setCurrentItem(1);
							
						}
						if ("C".equals(tabId)) {
							tvTab1.setTextColor(vpa.getResources().getColor(
									R.color.black));
							tvTab2.setTextColor(vpa.getResources().getColor(
									R.color.black));
							tvTab3.setTextColor(vpa.getResources().getColor(
									R.color.main_red));
							pager.setCurrentItem(2);
					
						}
					}
				});


		pager.setAdapter(new MyPageAdapter(listViews));
		pager.setOnPageChangeListener(new OnPageChangeListener() {
			@Override
			public void onPageSelected(int position) {
				tabHost.setCurrentTab(position);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});

		btnBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		
	}

	@SuppressWarnings("deprecation")
	private View getView(String id, Intent intent) {
		Log.d("EyeAndroid", "getView() called! id = " + id);
		return manager.startActivity(id, intent).getDecorView();
	}

	private class MyPageAdapter extends PagerAdapter {

		private List<View> list;

		private MyPageAdapter(List<View> list) {
			this.list = list;
		}

		@Override
		public void destroyItem(ViewGroup view, int position, Object arg2) {
			ViewPager pViewPager = ((ViewPager) view);
			pViewPager.removeView(list.get(position));
		}

		@Override
		public void finishUpdate(View arg0) {
		}

		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public Object instantiateItem(ViewGroup view, int position) {
			ViewPager pViewPager = ((ViewPager) view);
			pViewPager.addView(list.get(position));
			return list.get(position);
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public void restoreState(Parcelable arg0, ClassLoader arg1) {
		}

		@Override
		public Parcelable saveState() {
			return null;
		}

		@Override
		public void startUpdate(View arg0) {
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		((NormalCardActivity)manager.getActivity("NormalCardActivity")).initNormalList();
	}
	
}
