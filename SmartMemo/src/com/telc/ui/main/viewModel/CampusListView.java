package com.telc.ui.main.viewModel;

import android.widget.Button;
import android.widget.TextView;

public class CampusListView {

	private TextView textListContent,textListCategory;
	private Button btn_state;
	public CampusListView(TextView textListContent, TextView textListCategory,
			Button btn_state) {
		super();
		this.textListContent = textListContent;
		this.textListCategory = textListCategory;
		this.btn_state = btn_state;
	}
	public TextView getTextListContent() {
		return textListContent;
	}
	public void setTextListContent(TextView textListContent) {
		this.textListContent = textListContent;
	}
	public TextView getTextListCategory() {
		return textListCategory;
	}
	public void setTextListCategory(TextView textListCategory) {
		this.textListCategory = textListCategory;
	}
	public Button getBtn_state() {
		return btn_state;
	}
	public void setBtn_state(Button btn_state) {
		this.btn_state = btn_state;
	}

	
}
