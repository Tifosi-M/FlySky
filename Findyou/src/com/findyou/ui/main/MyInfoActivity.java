package com.findyou.ui.main;

import com.findyou.R;
import com.findyou.domain.Service.UserInfoService;
import com.findyou.domain.entity.UserInfo;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class MyInfoActivity extends Activity{
	private SharedPreferences sp;// xml保持登录信息
	private ImageButton btnEdit=null;
	private TextView textEditCardBack=null;
	private EditText editSex,editPhone,editEmail,editQQ,editHobby,editCity=null;
	private UserInfoService userInfoService=null;
	private boolean flag=false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_info);
		sp=getPreferences(MODE_PRIVATE);
		flag=getIntent().getExtras().getBoolean("editable");
		initEditText(flag);
		textEditCardBack=(TextView) findViewById(R.id.text_edit_userInfo);
		textEditCardBack.requestFocus();
		btnEdit=(ImageButton) findViewById(R.id.btn_edit_card_back);
		
		textEditCardBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent eIntent=new Intent(MyInfoActivity.this,MyInfoActivity.class);
				eIntent.putExtra("editable", true);
				finish();
			}
		});
		btnEdit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});

	}
	private void initEditText(boolean flag) {
		// TODO Auto-generated method stub
		if(flag==false){
			editSex=(EditText) findViewById(R.id.tv_watch_card_info_sex);
			editPhone=(EditText) findViewById(R.id.tv_watch_card_info_mobilephone);
			editEmail=(EditText) findViewById(R.id.tv_watch_card_info_email);
			editQQ=(EditText) findViewById(R.id.tv_watch_card_info_QQ);
			editHobby=(EditText) findViewById(R.id.tv_watch_card_info_professional);
			editCity=(EditText) findViewById(R.id.tv_watch_card_info_address);
		
			editSex.setEnabled(flag);
			editPhone.setEnabled(flag);
			editEmail.setEnabled(flag);
			editQQ.setEnabled(flag);
			editHobby.setEnabled(flag);
			editCity.setEnabled(flag);

		}else{
			editSex=(EditText) findViewById(R.id.tv_watch_card_info_sex);
			editPhone=(EditText) findViewById(R.id.tv_watch_card_info_mobilephone);
			editEmail=(EditText) findViewById(R.id.tv_watch_card_info_email);
			editQQ=(EditText) findViewById(R.id.tv_watch_card_info_QQ);
			editHobby=(EditText) findViewById(R.id.tv_watch_card_info_professional);
			editCity=(EditText) findViewById(R.id.tv_watch_card_info_address);
	
			editSex.setEnabled(flag);
			editPhone.setEnabled(flag);
			editEmail.setEnabled(flag);
			editQQ.setEnabled(flag);
			editHobby.setEnabled(flag);
			editCity.setEnabled(flag);
		}
		
	}
}
