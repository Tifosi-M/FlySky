package com.businesscard.ui.main.systemManagement;

import java.util.HashMap;

import webservice.MemoWebPara;
import webservice.WebServiceDelegate;
import webservice.WebServiceUtils;

import com.businesscard.data.dbDriver.CreateUserDB;
import com.businesscard.domain.Emtity.Accounts;
import com.businesscard.domain.Service.AccountsService;
import com.businesscard.mobile.R;
import com.businesscard.ui.main.FirstEditActivity;
import com.businesscard.ui.main.SlidingActivity;
import com.businesscard.ui.main.viewModel.MyProgressDialog;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginAndRegistActivity extends Activity implements
		WebServiceDelegate {
	private Button btn_login, btn_regist;
	private EditText et_userphone, et_password;
	private AccountsService acs;
	private String userPhone, password;
	private WebServiceUtils webService;
	private SharedPreferences login_sp;
	private boolean loginOrRegistr;// login=true,register=false

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		btn_login = (Button) findViewById(R.id.btn_login);
		btn_regist = (Button) findViewById(R.id.btn_regist);
		et_userphone = (EditText) findViewById(R.id.et_userphone);
		et_userphone.setText("");
		et_password = (EditText) findViewById(R.id.et_password);
		et_password.setText("");
		webService = new WebServiceUtils(MemoWebPara.SM_NAMESPACE,
				MemoWebPara.SM_URL, this);
		SQLiteDatabase db = openOrCreateDatabase("BusinessCard.db3",
				MODE_PRIVATE, null);
		acs = new AccountsService(db);
		login_sp = getSharedPreferences("Login", MODE_PRIVATE);
		if (login_sp.getBoolean("login_in", false)) {
			Intent intent = new Intent(LoginAndRegistActivity.this,
					SlidingActivity.class);
			startActivity(intent);
			finish();
		}

		btn_login.setOnClickListener(new OnClickListener() {

			@SuppressLint("SdCardPath")
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				userPhone = et_userphone.getText().toString();
				password = et_password.getText().toString();
				loginOrRegistr = true;
				if (userPhone.equals("") || password.equals("")) {
					Toast toast = Toast.makeText(LoginAndRegistActivity.this,
							"用户名密码请填完整", Toast.LENGTH_SHORT);
					toast.show();
				} else {
					loginOrRegistr = true;
					HashMap<String, Object> args = new HashMap<String, Object>();
					args.put("tel", userPhone);
					args.put("pwd", password);
					MyProgressDialog.show(LoginAndRegistActivity.this, "登陆中，请稍后……", true, false);
					webService.callWebService("login", args, boolean.class);
				}
			}
		});
		btn_regist.setOnClickListener(new OnClickListener() {

			@SuppressLint("SdCardPath")
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				userPhone = et_userphone.getText().toString();
				password = et_password.getText().toString();
				loginOrRegistr = false;
				if (userPhone.equals("") || password.equals("")) {
					Toast toast = Toast.makeText(LoginAndRegistActivity.this,
							"用户名密码请填完整", Toast.LENGTH_SHORT);
					toast.show();
				} else if (!userPhone.matches("^(13|15|18)\\d{9}$")) {
					Toast.makeText(getApplicationContext(), "电话号码格式不正确！",
							Toast.LENGTH_SHORT).show();
				} else {
					HashMap<String, Object> args = new HashMap<String, Object>();
					args.put("tel", userPhone);
					args.put("pwd", password);
					// 调用web服务
					MyProgressDialog.show(LoginAndRegistActivity.this, "注册中，请稍后……", true, false);
					webService.callWebService("register", args, boolean.class);
				}

			}
		});
	}

	private void saveUserInfo() {
		SharedPreferences sharedPreferences = getSharedPreferences(
				"current_user", MODE_PRIVATE);
		Editor editor = sharedPreferences.edit();
		editor.putString("userPhone", userPhone);
		editor.commit();
		login_sp = getSharedPreferences("Login", MODE_PRIVATE);
		editor = login_sp.edit();
		editor.putBoolean("login_in", true);
		editor.commit();
	}

	@Override
	public void handleException(Object ex) {
		// TODO Auto-generated method stub
		MyProgressDialog.cancel();
		Toast toast = Toast.makeText(LoginAndRegistActivity.this, "请检查网络连接",
				Toast.LENGTH_SHORT);
		toast.show();
	}

	@SuppressLint("SdCardPath")
	@Override
	public void handleResultOfWebService(String methodName, Object result) {
		// TODO Auto-generated method stub
		boolean flag = (Boolean) result;
		if (flag == true) {
			saveUserInfo();
			Accounts ac = new Accounts();
			ac.setAccount(userPhone);
			ac.setPwd(et_password.getText().toString());
			if (loginOrRegistr == true & acs.findAccountByID(userPhone) == null) {
				acs.addAccount(ac);
				String path = "/data/data/com.businesscard.mobile/databases/";
				new CreateUserDB(path, userPhone, LoginAndRegistActivity.this);
				Intent intent = new Intent(LoginAndRegistActivity.this,
						FirstEditActivity.class);
				startActivity(intent);
				finish();
			} else if (loginOrRegistr == true
					& acs.findAccountByID(userPhone) != null) {
				Intent intent = new Intent(LoginAndRegistActivity.this,
						SlidingActivity.class);
				startActivity(intent);
				finish();
			} else if (loginOrRegistr == false) {
				acs.addAccount(ac);
				String path = "/data/data/com.businesscard.mobile/databases/";
				new CreateUserDB(path, userPhone, LoginAndRegistActivity.this);
				Intent intent = new Intent(LoginAndRegistActivity.this,
						FirstEditActivity.class);
				startActivity(intent);
				finish();
			}

		} else if (flag == false) {
			if (loginOrRegistr == true) {
				MyProgressDialog.cancel();
				Toast toast = Toast.makeText(LoginAndRegistActivity.this,
						"密码不正确", Toast.LENGTH_SHORT);
				toast.show();
			} else if (loginOrRegistr == false) {
				MyProgressDialog.cancel();
				Toast toast = Toast.makeText(LoginAndRegistActivity.this,
						"用户已存在", Toast.LENGTH_SHORT);
				toast.show();
			}
		}
	}

}
