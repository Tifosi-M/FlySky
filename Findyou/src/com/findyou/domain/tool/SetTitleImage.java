package com.findyou.domain.tool;

import com.findyou.R;

import android.content.Context;
import android.graphics.drawable.Drawable;

public class SetTitleImage {

	Drawable drawable;

	public Drawable setListTitleImage(String imageId, Context context) {

		if (imageId.equals("1")) {
			drawable = context.getResources().getDrawable(
					R.drawable.card_list_title_1);
		} else if (imageId.equals("2")) {
			drawable = context.getResources().getDrawable(
					R.drawable.card_list_title_2);
		} else if (imageId.equals("3")) {
			drawable = context.getResources().getDrawable(
					R.drawable.card_list_title_3);
		} else if (imageId.equals("4")) {
			drawable = context.getResources().getDrawable(
					R.drawable.card_list_title_4);
		} else if (imageId.equals("5")) {
			drawable = context.getResources().getDrawable(
					R.drawable.card_list_title_5);
		} else if (imageId.equals("6")) {
			drawable = context.getResources().getDrawable(
					R.drawable.card_list_title_6);
		}

		return drawable;
	}

}
