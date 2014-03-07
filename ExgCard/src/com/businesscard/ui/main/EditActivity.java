package com.businesscard.ui.main;

import com.businesscard.domain.Emtity.NormalCardInfo;
import com.businesscard.domain.Service.NormalCardInfoService;
import com.businesscard.domain.tool.PinyinToolkit;
import com.businesscard.mobile.R;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class EditActivity extends Activity {
	private EditText edit_userName, edit_userPhone, edit_userTel,
			edit_userEmail, edit_userQQ, edit_userProfessional,
			edit_userAddress;
	private ImageButton imageBtn_save,imageBtnCancel;
	private String get_userPhone;
	private NormalCardInfo nci;
	private NormalCardInfoService ncis;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_first_card_edit);
		nci = new NormalCardInfo();
		SharedPreferences sharedPreferences = getSharedPreferences("current_user",
				MODE_PRIVATE);
		get_userPhone=sharedPreferences.getString("userPhone","");

		SQLiteDatabase db = openOrCreateDatabase(get_userPhone + ".db3",
				MODE_PRIVATE, null);
		ncis = new NormalCardInfoService(db);
		nci = ncis.findMyCardByUserPhone(get_userPhone);
		edit_userName = (EditText) findViewById(R.id.edit_userName);
		edit_userPhone = (EditText) findViewById(R.id.edit_userPhone);
		edit_userTel = (EditText) findViewById(R.id.edit_userTel);
		edit_userEmail = (EditText) findViewById(R.id.edit_userEmail);
		edit_userQQ = (EditText) findViewById(R.id.edit_userQQ);
		edit_userProfessional = (EditText) findViewById(R.id.edit_userProfessional);
		edit_userAddress = (EditText) findViewById(R.id.edit_userAddress);
		imageBtn_save = (ImageButton) findViewById(R.id.imageBtn_save);
		imageBtnCancel=(ImageButton) findViewById(R.id.imageBtnCancel);
		edit_userPhone.setText(get_userPhone);

		edit_userName.setText(nci.getUserName());
		edit_userPhone.setText(nci.getUserPhone());
		edit_userTel.setText(nci.getUserTel());
		edit_userEmail.setText(nci.getUserEmail());
		edit_userQQ.setText(nci.getUserQQ());
		edit_userProfessional.setText(nci.getUserProfessional());
		edit_userAddress.setText(nci.getUserAddress());

		imageBtn_save.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (edit_userName.getText().toString().equals("")
						|| edit_userPhone.getText().toString().equals("")) {
					Toast toast = Toast.makeText(EditActivity.this,
							"用户名密码请填完整", Toast.LENGTH_SHORT);
					toast.show();
				} else {
					nci.setUserName(edit_userName.getText().toString());
					nci.setUserPhone(edit_userPhone.getText().toString());
					nci.setUserTel(edit_userTel.getText().toString());
					nci.setUserEmail(edit_userEmail.getText().toString());
					nci.setUserQQ(edit_userQQ.getText().toString());
					nci.setUserProfessional(edit_userProfessional.getText()
							.toString());
					nci.setUserAddress(edit_userAddress.getText().toString());
					ncis.alterMyCard(nci);
					String userNameFirstAlpha=PinyinToolkit.cn2FirstSpell(nci.getUserName());
					ncis.alterNormalCard(nci, get_userPhone,userNameFirstAlpha);
					Intent intent = new Intent(EditActivity.this,
							SlidingActivity.class);
					startActivity(intent);
					finish();
				}
			}
		});
		
		imageBtnCancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(EditActivity.this,
						SlidingActivity.class);
				startActivity(intent);
				finish();
			}
		});
	}
	@Override    
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}
