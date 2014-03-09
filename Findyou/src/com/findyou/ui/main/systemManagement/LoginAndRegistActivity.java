package com.findyou.ui.main.systemManagement;

import java.sql.SQLException;
import java.util.HashMap;

import webservice.MemoWebPara;
import webservice.WebServiceDelegate;
import webservice.WebServiceUtils;

import com.findyou.R;
import com.findyou.data.dbDriver.DataContext;
import com.findyou.data.dbDriver.DataHelper;
import com.findyou.ui.main.SlidingActivity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class LoginAndRegistActivity extends Activity implements WebServiceDelegate {
	private String userPhone="";
	private Button btnLogin,btnRegist;
	private EditText etUserPhone,etPassword;
	private SharedPreferences sp;// xml保持登录信息
	private WebServiceUtils webService;
	private boolean loginOrRegistr;// login=true,register=false
//	private User user;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login_and_regist);
		webService= new WebServiceUtils(MemoWebPara.SM_NAMESPACE,
				MemoWebPara.SM_URL, this);
		etUserPhone=(EditText) findViewById(R.id.et_userphone);
		etPassword=(EditText) findViewById(R.id.et_password);
		btnLogin=(Button) findViewById(R.id.btn_login);
		btnLogin.setOnClickListener(new OnClickListener() {
			
				@Override
				public void onClick(View v) {
					userPhone = etUserPhone.getText().toString();

					String password = etUserPhone.getText().toString();
					if (userPhone.equals("") || password.equals("")) {
						Toast toast = Toast.makeText(LoginAndRegistActivity.this,
								"用户名密码请填完整", Toast.LENGTH_SHORT);
						toast.show();
					} else {
						loginOrRegistr = true;
						HashMap<String, Object> args = new HashMap<String, Object>();
						args.put("tel", userPhone);
						args.put("pwd", password);
						webService.callWebService("login", args, boolean.class);
					}

				}
		});
	}

	//处理web返回异常
	@Override
	public void handleException(Object ex) {
		Toast toast = Toast.makeText(LoginAndRegistActivity.this, "请检查网络连接",
				Toast.LENGTH_SHORT);
		toast.show();

	}
	//处理web返回结果
	@Override
	public void handleResultOfWebService(String methodName, Object result) {
		boolean flag = (Boolean) result;
		if (flag == true) {
			// 将登录状态改为已登录，并保存当前登录的用户用户名
			userPhone = etUserPhone.getText().toString();
			String password = etPassword.getText().toString();
			Editor editor = sp.edit();
			editor.putBoolean("login_in", true);
			editor.putString("user", userPhone);
			editor.commit();
			Intent intent = new Intent(LoginAndRegistActivity.this,
					SlidingActivity.class);
			startActivity(intent);
			LoginAndRegistActivity.this.finish();
		}else{
			if(flag== false){
				if(loginOrRegistr==true){
					Toast toast = Toast.makeText(LoginAndRegistActivity.this,
							"密码不正确", Toast.LENGTH_SHORT);
					toast.show();
				}else if(loginOrRegistr == false){
					Toast toast = Toast.makeText(LoginAndRegistActivity.this,
							"用户已存在", Toast.LENGTH_SHORT);
					toast.show();
				}
			}
		}
	}

}
