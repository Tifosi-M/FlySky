package com.businesscard.ui.main.viewModel;

import android.widget.LinearLayout;
import android.widget.TextView;

public class BusinessCardListView {

	private LinearLayout ll_businesscard_bg;
	private TextView tv_business_address,tv_business_name;
	public BusinessCardListView(LinearLayout ll_businesscard_bg,
			TextView tv_business_address, TextView tv_business_name) {
		super();
		this.ll_businesscard_bg = ll_businesscard_bg;
		this.tv_business_address = tv_business_address;
		this.tv_business_name = tv_business_name;
	}
	public LinearLayout getLl_businesscard_bg() {
		return ll_businesscard_bg;
	}
	public void setLl_businesscard_bg(LinearLayout ll_businesscard_bg) {
		this.ll_businesscard_bg = ll_businesscard_bg;
	}
	public TextView getTv_business_address() {
		return tv_business_address;
	}
	public void setTv_business_address(TextView tv_business_address) {
		this.tv_business_address = tv_business_address;
	}
	public TextView getTv_business_name() {
		return tv_business_name;
	}
	public void setTv_business_name(TextView tv_business_name) {
		this.tv_business_name = tv_business_name;
	}
	
}
