package com.businesscard.ui.main;

import com.businesscard.domain.Emtity.NormalCardInfo;
import com.businesscard.domain.Service.NormalCardInfoService;
import com.businesscard.mobile.R;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class ShowNormalCardActivity extends Activity {

	private NormalCardInfo nci;
	private NormalCardInfoService ncis;
	private String get_userPhone, userId;
	private TextView tv_watch_card_info_name, tv_watch_card_info_mobilephone,
			tv_watch_card_info_telphone, tv_watch_card_info_email,
			tv_watch_card_info_QQ, tv_watch_card_info_professional,
			tv_watch_card_info_address;
	private ImageButton btn_back,btn_del;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_normal_card);
		Intent intent = this.getIntent(); // ��ȡ���е�intent����
		Bundle bundle = intent.getExtras(); // ��ȡintent�����bundle����
		userId = bundle.getString("userId");
		nci = new NormalCardInfo();
		SharedPreferences sharedPreferences = getSharedPreferences(
				"current_user", MODE_PRIVATE);
		get_userPhone = sharedPreferences.getString("userPhone", "");

		SQLiteDatabase db = openOrCreateDatabase(get_userPhone + ".db3",
				MODE_PRIVATE, null);
		ncis = new NormalCardInfoService(db);
		nci = ncis.findNormalCardByUserPhone(userId);
		btn_back = (ImageButton) findViewById(R.id.btn_back);
		btn_del = (ImageButton) findViewById(R.id.btn_del);
		tv_watch_card_info_name = (TextView) findViewById(R.id.tv_watch_card_info_name);
		tv_watch_card_info_mobilephone = (TextView) findViewById(R.id.tv_watch_card_info_mobilephone);
		tv_watch_card_info_telphone = (TextView) findViewById(R.id.tv_watch_card_info_telphone);
		tv_watch_card_info_email = (TextView) findViewById(R.id.tv_watch_card_info_email);
		tv_watch_card_info_QQ = (TextView) findViewById(R.id.tv_watch_card_info_QQ);
		tv_watch_card_info_professional = (TextView) findViewById(R.id.tv_watch_card_info_professional);
		tv_watch_card_info_address = (TextView) findViewById(R.id.tv_watch_card_info_address);

		tv_watch_card_info_name.setText(nci.getUserName());
		tv_watch_card_info_mobilephone.setText(nci.getUserPhone());
		tv_watch_card_info_telphone.setText(nci.getUserTel());
		tv_watch_card_info_email.setText(nci.getUserEmail());
		tv_watch_card_info_professional.setText(nci.getUserProfessional());
		tv_watch_card_info_address.setText(nci.getUserAddress());
		tv_watch_card_info_QQ.setText(nci.getUserQQ());
		
		if(nci.getUserPhone().equals(get_userPhone))
		{
			btn_del.setVisibility(View.GONE);
		}
		System.out.println("---------------------"+userId);
		btn_back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				ShowNormalCardActivity.this.finish();
			}
		});
		
		btn_del.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ncis.delNormalCardByUserPhone(userId);
				ShowNormalCardActivity.this.finish();
			}
		});
		
	}
}
