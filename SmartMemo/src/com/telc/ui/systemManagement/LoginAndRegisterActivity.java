package com.telc.ui.systemManagement;

import java.io.File;
import java.util.HashMap;

import webservice.MemoWebPara;
import webservice.WebServiceDelegate;
import webservice.WebServiceUtils;

import com.telc.data.dbDriver.DBConstant;
import com.telc.data.dbDriver.MyDBHelp;
import com.telc.domain.Emtity.User;
import com.telc.domain.IService.IUserService;
import com.telc.domain.Service.UserService;
import com.telc.smartmemo.R;
import com.telc.ui.main.MemoApplication;
import com.telc.ui.main.SlidingActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class LoginAndRegisterActivity extends Activity implements DBConstant,
		WebServiceDelegate {
	private EditText et_phoneNum;
	private EditText et_password;
	private Button btn_login;
	private Button btn_regist;
	private IUserService service;
	private SharedPreferences sp;// xml保持登录信息
	private WebServiceUtils webService;
	private String userphone;
	private boolean loginOrRegistr;// login=true,register=false
	private User user;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login_register);
		user = new User();
		SQLiteDatabase db = openOrCreateDatabase(DB_FILENAME,
				MODE_PRIVATE, null);
		service = new UserService(db);
		initControlsAndRegEvent();
		// 判断是否状态是否为已登录，如果是则直接进入主界面
		if (sp.getBoolean("login_in", false)) {
			Intent intent = new Intent(LoginAndRegisterActivity.this,
					SlidingActivity.class);
			startActivity(intent);
			LoginAndRegisterActivity.this.finish();
		}
		btn_login.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				userphone = et_phoneNum.getText().toString();

				String password = et_password.getText().toString();
				if (userphone.equals("") || password.equals("")) {
					Toast toast = Toast.makeText(LoginAndRegisterActivity.this,
							"用户名密码请填完整", Toast.LENGTH_SHORT);
					toast.show();
				} else {
					loginOrRegistr = true;
					HashMap<String, Object> args = new HashMap<String, Object>();
					args.put("tel", userphone);
					args.put("pwd", password);
					webService.callWebService("login", args, boolean.class);
				}

			}
		});
		btn_regist.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				userphone = et_phoneNum.getText().toString();
				String password = et_password.getText().toString();
				loginOrRegistr = false;
				user.setUserID(userphone);
				user.setPhoneNum(userphone);
				user.setUserPwd(password);
				if (userphone.equals("") || password.equals("")) {
					Toast toast = Toast.makeText(LoginAndRegisterActivity.this,
							"用户名密码请填完整", Toast.LENGTH_SHORT);
					toast.show();
				} else if (!userphone.matches("^(13|15|18)\\d{9}$")) {
					Toast.makeText(getApplicationContext(), "电话号码格式不正确！",
							Toast.LENGTH_SHORT).show();
				} else {
					HashMap<String, Object> args = new HashMap<String, Object>();
					args.put("tel", userphone);
					args.put("pwd", password);
					//调用web服务
					webService.callWebService("register", args, boolean.class);
					Toast.makeText(getApplicationContext(), "注册成功！", Toast.LENGTH_SHORT).show();
				}
			}
		});
	}

	private void initControlsAndRegEvent() {
		btn_login = (Button) findViewById(R.id.btn_login);
		btn_regist = (Button) findViewById(R.id.btn_regist);
		et_phoneNum = (EditText) findViewById(R.id.et_userphone);
		et_password = (EditText) findViewById(R.id.login_password);
		sp = getSharedPreferences("Login", MODE_PRIVATE);
		webService = new WebServiceUtils(MemoWebPara.SM_NAMESPACE,
				MemoWebPara.SM_URL, this);
	}
	//处理web返回异常
	@Override
	public void handleException(Object ex) {
		Toast toast = Toast.makeText(LoginAndRegisterActivity.this, "请检查网络连接",
				Toast.LENGTH_SHORT);
		toast.show();

	}
	//处理web返回结果
	@Override
	public void handleResultOfWebService(String methodName, Object result) {
		boolean flag = (Boolean) result;
		if (flag == true) {
			// 将登录状态改为已登录，并保存当前登录的用户用户名
			userphone = et_phoneNum.getText().toString();
			String password = et_password.getText().toString();
			user.setUserID(userphone);
			user.setPhoneNum(userphone);
			user.setUserPwd(password);
			Editor editor = sp.edit();
			editor.putBoolean("login_in", true);
			editor.putString("user", userphone);
			editor.commit();
			if((service.getUserByUserPhone(userphone))==null){
				service.addUser(user);
			}
			if (loginOrRegistr == false) {
				service.addUser(user);
			}
			Intent intent = new Intent(LoginAndRegisterActivity.this,
					SlidingActivity.class);
			startActivity(intent);
			LoginAndRegisterActivity.this.finish();
		}else{
			if(flag== false){
				if(loginOrRegistr==true){
					Toast toast = Toast.makeText(LoginAndRegisterActivity.this,
							"密码不正确", Toast.LENGTH_SHORT);
					toast.show();
				}else if(loginOrRegistr == false){
					Toast toast = Toast.makeText(LoginAndRegisterActivity.this,
							"用户已存在", Toast.LENGTH_SHORT);
					toast.show();
				}
			}
		}
	}
}