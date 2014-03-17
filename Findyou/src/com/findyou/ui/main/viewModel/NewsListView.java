package com.findyou.ui.main.viewModel;

import android.widget.TextView;

public class NewsListView {
	TextView tv_friend_info_name,tv_date,tv_style,tv_address,tv_content;

	public NewsListView(TextView tv_friend_info_name, TextView tv_date,
			TextView tv_style, TextView tv_location, TextView tv_content) {
		super();
		this.tv_friend_info_name = tv_friend_info_name;
		this.tv_date = tv_date;
		this.tv_style = tv_style;
		this.tv_address = tv_location;
		this.tv_content = tv_content;
	}

	public TextView getTv_friend_info_name() {
		return tv_friend_info_name;
	}

	public void setTv_friend_info_name(TextView tv_friend_info_name) {
		this.tv_friend_info_name = tv_friend_info_name;
	}

	public TextView getTv_date() {
		return tv_date;
	}

	public void setTv_date(TextView tv_date) {
		this.tv_date = tv_date;
	}

	public TextView getTv_style() {
		return tv_style;
	}

	public void setTv_style(TextView tv_style) {
		this.tv_style = tv_style;
	}

	public TextView getTv_location() {
		return tv_address;
	}

	public void setTv_location(TextView tv_location) {
		this.tv_address = tv_location;
	}

	public TextView getTv_content() {
		return tv_content;
	}

	public void setTv_content(TextView tv_content) {
		this.tv_content = tv_content;
	}

}
