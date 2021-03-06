package com.findyou.data.Adapter;

import com.findyou.R;
import com.findyou.domain.tool.SetTitleImage;
import com.findyou.ui.main.viewModel.FriendsListView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class FriendsCursorAdapter extends SimpleCursorAdapter {
	private Context context;
	private int layout;
	private FriendsListView friendsListView;

	@SuppressWarnings("deprecation")
	public FriendsCursorAdapter(Context context, int layout, Cursor c,
			String[] from, int[] to) {
		super(context, layout, c, from, to);
		// TODO Auto-generated constructor stub
		this.context = context;
		this.layout = layout;
	}

	@SuppressLint("DefaultLocale")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		LayoutInflater lf = LayoutInflater.from(context);
		View view = lf.inflate(layout, null);

		TextView tv_alpha, tv_card_list_item_name;
		tv_alpha = (TextView) view.findViewById(R.id.tv_alpha);
		tv_card_list_item_name = (TextView) view
				.findViewById(R.id.tv_card_list_item_name);
		friendsListView = new FriendsListView(tv_card_list_item_name,
				tv_alpha);

		view.setTag(friendsListView);

		Cursor cursor = (Cursor) getItem(position);
		int userNameIndex = cursor.getColumnIndex("userName");
		String userName = cursor.getString(userNameIndex);
		tv_card_list_item_name.setText(userName);

		int imageIndex = cursor.getColumnIndex("userImage");
		String imageId = cursor.getString(imageIndex);
		SetTitleImage sti = new SetTitleImage();
		Drawable drawable = sti.setListTitleImage(imageId, context);
		tv_card_list_item_name.setCompoundDrawablesWithIntrinsicBounds(
				drawable, null, null, null);

		int unfaIndex = cursor.getColumnIndex("userNameFirstAlpha");
		String unfa = cursor.getString(unfaIndex).substring(0, 1);
		
        if(cursor.getCount()==1||position==0)
        {
        	tv_alpha.setVisibility(View.VISIBLE);
			tv_alpha.setText(unfa.toUpperCase());
        }
        else  {
			Cursor cursor2 = (Cursor) getItem(position - 1);
			int unfaIndex2Index = cursor2.getColumnIndex("userNameFirstAlpha");
			String unfa2 = cursor2.getString(unfaIndex2Index).substring(0, 1);

			if (!unfa.equals(unfa2)) {
				tv_alpha.setVisibility(View.VISIBLE);
				tv_alpha.setText(unfa.toUpperCase());
			}
		}
		return view;
	}
}
