package com.telc.ui.systemManagement;

import com.telc.domain.Emtity.User;
import com.telc.domain.IService.IUserService;
import com.telc.domain.Service.UserService;
import com.telc.smartmemo.R;
import com.telc.ui.main.SlidingActivity;
import com.telc.ui.main.viewModel.UnfinishFragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.telc.data.dbDriver.DBConstant;

public class PersonalInfoFragment extends Fragment implements DBConstant {
	private ImageView iv_photo;
	private EditText et_name;
	private EditText et_age;
	private Switch sw_sex;
	private EditText et_phone;
	private EditText et_prof;
	private EditText et_hob;
	private Button bt_ok;
	private SharedPreferences sp;
	private String userphone;
	private IUserService userService;
	private ArrayAdapter<String> adapter;
	Drawable drawable;
	private View view;
	private String sex;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view = inflater.inflate(R.layout.activity_personalinfo, null);
		iv_photo = (ImageView) view.findViewById(R.id.photo);
		et_name = (EditText) view.findViewById(R.id.et_info_name);
		et_age = (EditText) view.findViewById(R.id.et_info_age);
		et_phone = (EditText) view.findViewById(R.id.ed_info_phone);
		et_hob = (EditText) view.findViewById(R.id.et_info_hob);
		et_prof = (EditText) view.findViewById(R.id.et_info_prof);
		bt_ok = (Button) view.findViewById(R.id.bt_info_ok);
		 sw_sex=(Switch) view.findViewById(R.id.sw_sex);
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		SQLiteDatabase db = getActivity().openOrCreateDatabase(DB_FILENAME,
				getActivity().MODE_PRIVATE, null);
		// // 将可选内容与ArrayAdapter连接起来
		// adapter = new ArrayAdapter<String>(getActivity(),
		// android.R.layout.simple_spinner_item, mdata);
		//
		// // 设置下拉列表的风格
		// adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

		userService = new UserService(db);
		sp = getActivity().getSharedPreferences("Login",
				getActivity().MODE_PRIVATE);
		userphone = sp.getString("user", null);
		System.out.println(userphone);
		User user = userService.getUserByUserPhone(userphone.toString());

		et_phone.setText(user.getPhoneNum());

		if(user.getUserName().equals("null"))
			et_name.setText("");
		else
			et_name.setText(user.getUserName());

		et_name.setOnFocusChangeListener(new OnFocusChangeListener() {

			@SuppressWarnings("deprecation")
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// TODO Auto-generated method stub
				if (hasFocus) {
					drawable = getResources().getDrawable(
							R.drawable.bg_sms_input);
					et_name.setBackgroundDrawable(drawable);
				} else {
					et_name.setBackgroundDrawable(null);
				}

			}
		});

		et_age.setText((new Integer(user.getAge()).toString()));

		et_age.setOnFocusChangeListener(new OnFocusChangeListener() {

			@SuppressWarnings("deprecation")
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// TODO Auto-generated method stub
				if (hasFocus) {
					drawable = getResources().getDrawable(
							R.drawable.bg_sms_input);
					et_age.setBackgroundDrawable(drawable);
				} else {
					et_age.setBackgroundDrawable(null);
				}
			}
		});

		if(user.getHob().equals("null"))
			et_hob.setText("");
		else
			et_hob.setText(user.getHob());

		et_hob.setOnFocusChangeListener(new OnFocusChangeListener() {
			@SuppressWarnings("deprecation")
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// TODO Auto-generated method stub
				if (hasFocus) {
					drawable = getResources().getDrawable(
							R.drawable.bg_sms_input);
					et_hob.setBackgroundDrawable(drawable);
				} else {
					et_hob.setBackgroundDrawable(null);
				}
			}
		});

		
		if(user.getProf().equals("null"))
			et_prof.setText("");
		else
			et_prof.setText(user.getProf());
		et_prof.setOnFocusChangeListener(new OnFocusChangeListener() {

			@SuppressWarnings("deprecation")
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// TODO Auto-generated method stub
				if (hasFocus) {
					drawable = getResources().getDrawable(
							R.drawable.bg_sms_input);
					et_prof.setBackgroundDrawable(drawable);
				} else {
					et_prof.setBackgroundDrawable(null);
				}
			}
		});

		
//		et_name.setText(user.getUserName());
 
		sex=user.getSex();
		if(sex.equals("男"))
		{
			sw_sex.setChecked(true);
		}
		else
		{
			sw_sex.setChecked(false);
		}
		sw_sex.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					sex="男";
				}
				else
				{
					sex="女";
				}
			}
		});
		
		bt_ok.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				User user = new User();
				// String t = et_age.getText().toString();
				user.setAge(Integer.valueOf((et_age.getText().toString())));
				user.setHob(et_hob.getText().toString());
				user.setPhoneNum(userphone);
				user.setUserID(userphone);
				user.setProf(et_prof.getText().toString());
				user.setUserName(et_name.getText().toString());
				user.setSex(sex);
				userService.updateUser(user);
				Toast.makeText(getActivity(), "保存成功！", Toast.LENGTH_SHORT).show();
				((SlidingActivity)getActivity()).toggle();
			}
		});

	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	}

}
