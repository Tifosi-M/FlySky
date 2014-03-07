package com.businesscard.data.Adapter;

import com.businesscard.domain.tool.SetTitleImage;
import com.businesscard.mobile.R;
import com.businesscard.ui.main.viewModel.BusinessCardListView;

import android.content.Context;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class BusinessCardCursorAdapter extends SimpleCursorAdapter {
	private Context context;
	private int layout;
	private BusinessCardListView businessCardListView;

	@SuppressWarnings("deprecation")
	public BusinessCardCursorAdapter(Context context, int layout, Cursor c,
			String[] from, int[] to) {
		super(context, layout, c, from, to);
		// TODO Auto-generated constructor stub
		this.context = context;
		this.layout = layout;
	}

	@SuppressWarnings("deprecation")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		LayoutInflater lf = LayoutInflater.from(context);
		View view = lf.inflate(layout, null);

		LinearLayout ll_businesscard_bg;
		TextView tv_business_address, tv_business_name;

		ll_businesscard_bg = (LinearLayout) view
				.findViewById(R.id.ll_businesscard_bg);
		tv_business_address = (TextView) view
				.findViewById(R.id.tv_business_address);
		tv_business_name = (TextView) view.findViewById(R.id.tv_business_name);

		businessCardListView = new BusinessCardListView(ll_businesscard_bg,
				tv_business_address, tv_business_name);
		view.setTag(businessCardListView);

		Cursor cursor = (Cursor) getItem(position);
		int BusinessNameIndex = cursor.getColumnIndex("BusinessName");
		String BusinessName = cursor.getString(BusinessNameIndex);
		tv_business_name.setText(BusinessName);

		int BusinessAddressIndex = cursor.getColumnIndex("BusinessAddress");
		String BusinessAddress = cursor.getString(BusinessAddressIndex);
		tv_business_address.setText(BusinessAddress);

		int BusinessImageIndex = cursor.getColumnIndex("BusinessImage");
		String BusinessImage = cursor.getString(BusinessImageIndex);
		SetTitleImage sti = new SetTitleImage();
		Drawable drawable = sti.setBusinessCardBg(BusinessImage, context);
		ll_businesscard_bg.setBackgroundDrawable(drawable);
		return view;
	}
}
