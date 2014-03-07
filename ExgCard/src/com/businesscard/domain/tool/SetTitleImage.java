package com.businesscard.domain.tool;

import java.lang.reflect.Field;

import com.businesscard.mobile.R;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.TextView;

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

	public Drawable setBusinessCardBg(String imageId, Context context) {

		try {
			Field field = R.drawable.class.getField(imageId);
			int i = field.getInt(new R.drawable());
			drawable = context.getResources().getDrawable(i);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return drawable;
	}

}
