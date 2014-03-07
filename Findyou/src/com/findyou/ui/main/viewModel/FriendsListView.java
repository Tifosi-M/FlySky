package com.findyou.ui.main.viewModel;

import android.widget.TextView;

public class FriendsListView {
	TextView tv_card_list_item_name, tv_alpha;

	public FriendsListView(TextView tv_card_list_item_name, TextView tv_alpha) {
		super();
		this.tv_card_list_item_name = tv_card_list_item_name;
		this.tv_alpha = tv_alpha;
	}

	public TextView getTv_card_list_item_name() {
		return tv_card_list_item_name;
	}

	public void setTv_card_list_item_name(TextView tv_card_list_item_name) {
		this.tv_card_list_item_name = tv_card_list_item_name;
	}

	public TextView getTv_alpha() {
		return tv_alpha;
	}

	public void setTv_alpha(TextView tv_alpha) {
		this.tv_alpha = tv_alpha;
	}

}
