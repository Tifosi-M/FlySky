package com.businesscard.ui.main;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

import org.kobjects.base64.Base64;

import webservice.WebServiceDelegate;
import webservice.WebServiceUtils;

import com.businesscard.data.Adapter.BusinessCardCursorAdapter;
import com.businesscard.domain.Service.BusinessCardInfoService;
import com.businesscard.mobile.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class BusinessCardActivity extends Activity implements
		WebServiceDelegate {

	private String get_userPhone;
	private Cursor cursor;
	private BusinessCardInfoService businessCardInfoService;
	private BusinessCardCursorAdapter businessCardCursorAdapter;
	private ImageButton normal_card_capture_btn, normal_card_fresh_btn;
	private ListView businesscard_list;
	private TextView tv_prompt;
	private boolean webflag = false;
	private WebServiceUtils webService;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_business_card_list);
		SharedPreferences sharedPreferences = getSharedPreferences(
				"current_user", MODE_PRIVATE);
		get_userPhone = sharedPreferences.getString("userPhone", "");
		SQLiteDatabase db = openOrCreateDatabase(get_userPhone + ".db3",
				MODE_PRIVATE, null);
		businessCardInfoService = new BusinessCardInfoService(db);

		businesscard_list = (ListView) findViewById(R.id.businesscard_list);
		initBusinessList();

		tv_prompt = (TextView) findViewById(R.id.tv_prompt);
		tv_prompt.setAlpha(180);

		normal_card_capture_btn = (ImageButton) findViewById(R.id.imageButton3);
		normal_card_fresh_btn = (ImageButton) findViewById(R.id.imageButton2);

		normal_card_capture_btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(BusinessCardActivity.this,
						CaptureActivity.class);
				startActivity(intent);
			}
		});
		normal_card_fresh_btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				webflag = false;
				HashMap<String, Object> args = new HashMap<String, Object>();
				args.put("arg0", get_userPhone);
				webService.callWebService("downloadMemoDBFile", args,
						byte[].class);
				Toast.makeText(BusinessCardActivity.this, "正在同步...",
						Toast.LENGTH_SHORT).show();
			}
		});
	}

	@Override
	public void handleException(Object ex) {
		// TODO Auto-generated method stub
		Toast.makeText(BusinessCardActivity.this, "请检查网络连接", Toast.LENGTH_SHORT)
				.show();
	}

	@Override
	public void handleResultOfWebService(String methodName, Object result) {
		// TODO Auto-generated method stub
		if (webflag == false) {
			Toast.makeText(BusinessCardActivity.this, "名片夹更新成功",
					Toast.LENGTH_SHORT).show();
			String tmp = result.toString();
			// ת����byte����
			byte[] retByte = Base64.decode(tmp);
			createDatabase(retByte);
			initBusinessList();
		}
	}

	@SuppressLint("SdCardPath")
	public void createDatabase(byte[] db) {
		String path = "/data/data/com.businesscard.mobile/databases/";
		File file = new File(path);
		file.mkdir();
		path = path + get_userPhone + ".db3";
		file = new File(path);
		try {
			file.createNewFile();
			FileOutputStream os = new FileOutputStream(file);
			os.write(db);
			os.close();
			System.out.println("success");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void initBusinessList() {
		cursor = businessCardInfoService.findBusinessCards();
		businessCardCursorAdapter = new BusinessCardCursorAdapter(this,
				R.layout.business_card_item, cursor, new String[] {
						"BusinessName", "BusinessAddress" }, new int[] {
						R.id.tv_business_name, R.id.tv_business_address });
		businesscard_list.setAdapter(businessCardCursorAdapter);
	}
}
