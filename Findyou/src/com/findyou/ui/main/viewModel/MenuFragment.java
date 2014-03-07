package com.findyou.ui.main.viewModel;

import com.findyou.R;
import com.findyou.ui.main.FriendsListActivity;
import com.findyou.ui.main.MyInfoActivity;
import com.findyou.ui.main.SlidingActivity;
import com.findyou.ui.main.ViewPagerActivity;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

public class MenuFragment extends Fragment {

	TextView txt_user_info, txt_my_share, txt_friend_list, txt_my_lastet;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.activity_menu, null);
		txt_user_info = (TextView) view.findViewById(R.id.txt_user_info);
		txt_my_share = (TextView) view.findViewById(R.id.txt_my_share);
		txt_friend_list = (TextView) view.findViewById(R.id.txt_friend_list);
		txt_my_lastet = (TextView) view.findViewById(R.id.txt_my_lastet);
		txt_user_info.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(((SlidingActivity) getActivity()),
						MyInfoActivity.class);
				startActivity(intent);
			}
		});

		txt_my_lastet.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(((SlidingActivity) getActivity())
						.getFlag()!=("activity_latest_info")){
				((SlidingActivity) getActivity())
						.setContentView(R.layout.activity_latest_info);
				((SlidingActivity) getActivity()).fragmentTransaction = getFragmentManager()
						.beginTransaction();
				MainFragment mainFragment = new MainFragment();
				((SlidingActivity) getActivity()).fragmentTransaction.replace(
						R.id.main, mainFragment);
				((SlidingActivity) getActivity()).fragmentTransaction.commit();
			
				((SlidingActivity) getActivity()).setFlag("activity_latest_info");
				}
				((SlidingActivity) getActivity()).getSlidingMenu()
				.showContent();
			}
		});

		txt_my_share.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(((SlidingActivity) getActivity()),
						ViewPagerActivity.class);
				startActivity(intent);
			}
		});
		txt_friend_list.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				// Intent intent = new Intent(((SlidingActivity) getActivity()),
				// FriendsListActivity.class);
				// startActivity(intent);
				if(((SlidingActivity) getActivity())
						.getFlag()!=("activity_friends_list")){
				((SlidingActivity) getActivity())
						.setContentView(R.layout.activity_friends_list);
				((SlidingActivity) getActivity()).fragmentTransaction = getFragmentManager()
						.beginTransaction();
				FriendsListFragment friendsListFragment = new FriendsListFragment();
				((SlidingActivity) getActivity()).fragmentTransaction.replace(
						R.id.friend, friendsListFragment);
				((SlidingActivity) getActivity()).fragmentTransaction.commit();
		
				((SlidingActivity) getActivity()).setFlag("activity_friends_list");
				}
				((SlidingActivity) getActivity()).getSlidingMenu()
				.showContent();
			}
		});
		return view;
	}
}
