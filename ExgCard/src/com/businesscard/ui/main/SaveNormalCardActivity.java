package com.businesscard.ui.main;

import com.businesscard.domain.Emtity.NormalCardInfo;
import com.businesscard.domain.Service.NormalCardInfoService;
import com.businesscard.domain.tool.PinyinToolkit;
import com.businesscard.mobile.R;
import com.businesscard.ui.main.systemManagement.LoginAndRegistActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

public class SaveNormalCardActivity extends Activity {

	private TextView tv_watch_card_info_name, tv_watch_card_info_mobilephone,
			tv_watch_card_info_telphone, tv_watch_card_info_email,
			tv_watch_card_info_QQ, tv_watch_card_info_professional,
			tv_watch_card_info_address, btn_save, btn_go_back;
	private NormalCardInfo nci = new NormalCardInfo();
	private String normalCardInfo, get_userPhone;
	private NormalCardInfoService ncis;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_save_normal_card);
		normalCardInfo = this.getIntent().getExtras()
				.getString("normalCardInfo");

		SharedPreferences sharedPreferences = getSharedPreferences(
				"current_user", MODE_PRIVATE);
		get_userPhone = sharedPreferences.getString("userPhone", "");

		SQLiteDatabase db = openOrCreateDatabase(get_userPhone + ".db3",
				MODE_PRIVATE, null);
		ncis = new NormalCardInfoService(db);

		tv_watch_card_info_name = (TextView) findViewById(R.id.tv_watch_card_info_name);
		tv_watch_card_info_mobilephone = (TextView) findViewById(R.id.tv_watch_card_info_mobilephone);
		tv_watch_card_info_telphone = (TextView) findViewById(R.id.tv_watch_card_info_telphone);
		tv_watch_card_info_email = (TextView) findViewById(R.id.tv_watch_card_info_email);
		tv_watch_card_info_QQ = (TextView) findViewById(R.id.tv_watch_card_info_QQ);
		tv_watch_card_info_professional = (TextView) findViewById(R.id.tv_watch_card_info_professional);
		tv_watch_card_info_address = (TextView) findViewById(R.id.tv_watch_card_info_address);
		btn_save = (TextView) findViewById(R.id.btn_save);
		btn_go_back = (TextView) findViewById(R.id.btn_go_back);
		String normalCardInfo_arr[] = normalCardInfo.split(";");
		nci.setUserPhone(normalCardInfo_arr[1]);
		nci.setUserName(normalCardInfo_arr[2]);
		nci.setUserTel(normalCardInfo_arr[3]);
		nci.setUserEmail(normalCardInfo_arr[4]);
		nci.setUserProfessional(normalCardInfo_arr[5]);
		nci.setUserAddress(normalCardInfo_arr[6]);
		nci.setUserImage(normalCardInfo_arr[7]);
		nci.setUserQQ(normalCardInfo_arr[8]);
		nci.setUserId(get_userPhone);
		tv_watch_card_info_name.setText(nci.getUserName());
		tv_watch_card_info_mobilephone.setText(nci.getUserPhone());
		tv_watch_card_info_telphone.setText(nci.getUserTel());
		tv_watch_card_info_email.setText(nci.getUserEmail());
		tv_watch_card_info_professional.setText(nci.getUserProfessional());
		tv_watch_card_info_address.setText(nci.getUserAddress());
		tv_watch_card_info_QQ.setText(nci.getUserQQ());

		btn_go_back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				SaveNormalCardActivity.this.finish();
			}
		});

		btn_save.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String apla=PinyinToolkit.cn2FirstSpell(nci.getUserName());
				ncis.addNormalCardByUserPhone(
						apla, nci);
				Toast toast = Toast.makeText(SaveNormalCardActivity.this,
						"保存成功", Toast.LENGTH_SHORT);
				toast.show();
				SaveNormalCardActivity.this.finish();
			}
		});

		// nci.getUserImage()

	}
}
