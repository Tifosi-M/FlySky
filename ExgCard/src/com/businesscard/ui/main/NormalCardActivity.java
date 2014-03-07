package com.businesscard.ui.main;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

import org.kobjects.base64.Base64;

import webservice.MemoWebPara;
import webservice.WebServiceDelegate;
import webservice.WebServiceUtils;

import com.businesscard.data.Adapter.NormalCardCursorAdapter;
import com.businesscard.domain.Service.NormalCardInfoService;
import com.businesscard.mobile.R;
import com.businesscard.ui.main.systemManagement.LoginAndRegistActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class NormalCardActivity extends Activity implements WebServiceDelegate{

	private String get_userPhone;
	private NormalCardInfoService nois;
	private Cursor cursor;
	private NormalCardCursorAdapter normalCardCursorAdapter;
    private ListView normalcard_list;
    private ImageButton normal_card_capture_btn,normal_card_fresh_btn;
	private boolean webflag = false;
	private WebServiceUtils webService;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_normal_card_list);
		
		webService = new WebServiceUtils(MemoWebPara.MM_NAMESPACE,
				MemoWebPara.MM_URL, this);
		
		SharedPreferences sharedPreferences = getSharedPreferences("current_user",
				MODE_PRIVATE);
		get_userPhone=sharedPreferences.getString("userPhone","");
		SQLiteDatabase db = openOrCreateDatabase(get_userPhone + ".db3",
				MODE_PRIVATE, null);
		nois = new NormalCardInfoService(db);
		normalcard_list=(ListView) findViewById(R.id.normalcard_list);
		initNormalList();
		normal_card_capture_btn=(ImageButton) findViewById(R.id.imageButton3);
		normal_card_fresh_btn=(ImageButton) findViewById(R.id.imageButton2);
		
		normal_card_capture_btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(NormalCardActivity.this,
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
				Toast.makeText(NormalCardActivity.this,
						"正在同步...", Toast.LENGTH_SHORT).show();
			}
		});
		
	}
	@Override
	public void handleException(Object ex) {
		// TODO Auto-generated method stub
		Toast.makeText(NormalCardActivity.this, "请检查网络连接",
				Toast.LENGTH_SHORT).show();
	}
	@Override
	public void handleResultOfWebService(String methodName, Object result) {
		// TODO Auto-generated method stub
		if(webflag == false)
		{
			Toast.makeText(NormalCardActivity.this, "名片夹更新成功",
					Toast.LENGTH_SHORT).show();
			String tmp = result.toString();
			//转化成byte数组
			byte[] retByte = Base64.decode(tmp);
			createDatabase(retByte);
			initNormalList();
		}
	}
	
	
	@SuppressLint("SdCardPath")
	public void createDatabase(byte[] db){
		String path = "/data/data/com.businesscard.mobile/databases/";
		File file=new File(path);
	    file.mkdir();
	    path=path+get_userPhone+".db3";
	    file=new File(path);
	    try {
			file.createNewFile();
			 FileOutputStream os=new FileOutputStream(file);
			 os.write(db);
			 os.close();
			 System.out.println("success");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public void initNormalList()
	{
		cursor = nois.findNormalCardsByUserId(get_userPhone);
		normalCardCursorAdapter = new NormalCardCursorAdapter(this,
				R.layout.normal_card_item, cursor, new String[] {"userName"},
				new int[] {R.id.tv_card_list_item_name});
		normalcard_list.setAdapter(normalCardCursorAdapter);
		normalcard_list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				TextView tv_card_list_item_phone=(TextView) arg1
						.findViewById(R.id.tv_card_list_item_phone);
				Intent intent = new Intent(NormalCardActivity.this,
						ShowNormalCardActivity.class);
				Bundle bundle=new Bundle();
				bundle.putString("userId", tv_card_list_item_phone.getText().toString());
				intent.putExtras(bundle);
			
				startActivity(intent);			
			}
		});
	}
}
