package com.telc.ui.main.viewModel;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

import org.kobjects.base64.Base64;

import webservice.MemoWebPara;
import webservice.WebServiceDelegate;
import webservice.WebServiceUtils;

import com.telc.smartmemo.R;
import com.telc.ui.main.SlidingActivity;
import com.telc.ui.other.AboutFragment;
import com.telc.ui.other.newSettingFragment;
import com.telc.ui.systemManagement.LoginAndRegisterActivity;
import com.telc.ui.systemManagement.PersonalInfoFragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

public class MenuFragment extends Fragment implements WebServiceDelegate{
	int index = 0;
	private WebServiceUtils webService;
	private boolean webflag = false;
	private SharedPreferences sp;
	TextView textUserInfo;
	TextView textFinished;
	TextView textUnfinish;
	TextView textAbout;
	TextView textSetting;
	TextView textExit;
	TextView textReturn;
	TextView txt_campus;
	FinishFragment finishFragment;
	UnfinishFragment unfinishFragment;
	Drawable drawable;
    CampusFragment campusFragment;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		sp=getActivity().getSharedPreferences("Login",
				getActivity().MODE_PRIVATE);
		webService = new WebServiceUtils(MemoWebPara.MM_NAMESPACE,
				MemoWebPara.MM_URL, this);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.activity_menu, null);
		drawable = getResources().getDrawable(R.drawable.bg_press);
		textUserInfo = (TextView) view.findViewById(R.id.txt_userinfo);
		textFinished = (TextView) view.findViewById(R.id.txt_finish);
		textUnfinish = (TextView) view.findViewById(R.id.txt_unfinish);
		textAbout = (TextView) view.findViewById(R.id.txt_about);
		textSetting = (TextView) view.findViewById(R.id.txt_setting);
		textExit = (TextView) view.findViewById(R.id.txt_exit);
		textReturn = (TextView) view.findViewById(R.id.txt_return);
		txt_campus=(TextView) view.findViewById(R.id.txt_campus);
		textUnfinish.setBackgroundDrawable(drawable);
		
		textUserInfo.setOnClickListener(new OnClickListener() {
			@SuppressWarnings("deprecation")
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (index == 1)
					((SlidingActivity) getActivity()).getSlidingMenu().toggle();
				index = 1;

				drawable = getResources().getDrawable(R.drawable.bg_press);
				textUserInfo.setBackgroundDrawable(drawable);
				textFinished.setBackgroundDrawable(null);
				textUnfinish.setBackgroundDrawable(null);
				textSetting.setBackgroundDrawable(null);
				textAbout.setBackgroundDrawable(null);
				textReturn.setBackgroundDrawable(null);
				textExit.setBackgroundDrawable(null);
				txt_campus.setBackgroundDrawable(null);
				FragmentManager fm = ((SlidingActivity) getActivity())
						.getFragmentManager();
				/**
				 * @parma replace（被替换的layout，新的fragment）
				 */

				fm.beginTransaction().replace(R.id.unfinish, new PersonalInfoFragment())
				.commit();
				
				((SlidingActivity)getActivity()).getSlidingMenu().showContent();
			}
		});

		textUnfinish.setOnClickListener(new OnClickListener() {

			@SuppressWarnings("deprecation")
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (index == 2)
					((SlidingActivity) getActivity()).getSlidingMenu().toggle();
				index = 2;
				FragmentManager fm = ((SlidingActivity) getActivity())
						.getFragmentManager();

				drawable = getResources().getDrawable(R.drawable.bg_press);
				textUserInfo.setBackgroundDrawable(null);
				textFinished.setBackgroundDrawable(null);
				textUnfinish.setBackgroundDrawable(drawable);
				textSetting.setBackgroundDrawable(null);
				textAbout.setBackgroundDrawable(null);
				textReturn.setBackgroundDrawable(null);
				textExit.setBackgroundDrawable(null);
				txt_campus.setBackgroundDrawable(null);
				/**
				 * @parma replace（被替换的layout，新的fragment）
				 */

				fm.beginTransaction().replace(R.id.unfinish, unfinishFragment == null ?new UnfinishFragment():unfinishFragment )
				.commit();
				((SlidingActivity)getActivity()).getSlidingMenu().showContent();	
			}
		});

		textFinished.setOnClickListener(new OnClickListener() {

			@SuppressWarnings("deprecation")
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				 if(index==3)
					 ((SlidingActivity)getActivity()).getSlidingMenu().toggle();
				 index=3;
				 FragmentManager fm =
				 ((SlidingActivity)getActivity()).getFragmentManager();
				 fm.beginTransaction().replace(R.id.unfinish, finishFragment
				 == null ?new FinishFragment():finishFragment)
				 .addToBackStack(null)
				 .commit();
				drawable = getResources().getDrawable(R.drawable.bg_press);
				textUserInfo.setBackgroundDrawable(null);
				textFinished.setBackgroundDrawable(drawable);
				textUnfinish.setBackgroundDrawable(null);
				textSetting.setBackgroundDrawable(null);
				txt_campus.setBackgroundDrawable(null);
				textAbout.setBackgroundDrawable(null);
				textReturn.setBackgroundDrawable(null);
				textExit.setBackgroundDrawable(null);
				 ((SlidingActivity)getActivity()).getSlidingMenu().showContent();	
			}
		});

		txt_campus.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				 if(index==5)
					 ((SlidingActivity)getActivity()).getSlidingMenu().toggle();
				 index=5;
				FragmentManager fm = ((SlidingActivity) getActivity())
						.getFragmentManager();
				fm.beginTransaction().replace(R.id.unfinish, campusFragment == null ?new CampusFragment():campusFragment )
				.commit();
				((SlidingActivity)getActivity()).getSlidingMenu().showContent();
				drawable = getResources().getDrawable(R.drawable.bg_press);
				textUserInfo.setBackgroundDrawable(null);
				textFinished.setBackgroundDrawable(null);
				textUnfinish.setBackgroundDrawable(null);
				textSetting.setBackgroundDrawable(null);
				textAbout.setBackgroundDrawable(null);
				textReturn.setBackgroundDrawable(null);
				textExit.setBackgroundDrawable(null);
				txt_campus.setBackgroundDrawable(drawable);
					webflag = false;
					String tel = "10000";
					HashMap<String, Object> args = new HashMap<String, Object>();
					args.put("tel", tel);
					webService.callWebService("downloadMemoDBFile", args,
							byte[].class);
			}
		});
		
		textReturn.setOnClickListener(new OnClickListener() {

			@SuppressWarnings("deprecation")
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Editor edit = ((SlidingActivity) getActivity()).sp.edit();
				edit.putBoolean("login_in", false);
				edit.commit();

				drawable = getResources().getDrawable(R.drawable.bg_press);
				textUserInfo.setBackgroundDrawable(null);
				textFinished.setBackgroundDrawable(null);
				textUnfinish.setBackgroundDrawable(null);
				textSetting.setBackgroundDrawable(null);
				textAbout.setBackgroundDrawable(null);
				textReturn.setBackgroundDrawable(drawable);
				textExit.setBackgroundDrawable(null);
				txt_campus.setBackgroundDrawable(null);
				Intent intent = new Intent(getActivity(),
						LoginAndRegisterActivity.class);
				startActivity(intent);
				getActivity().finish();
			}
		});

		textExit.setOnClickListener(new OnClickListener() {

			@SuppressWarnings("deprecation")
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				drawable = getResources().getDrawable(R.drawable.bg_press);
				textExit.setBackgroundDrawable(drawable);

				getActivity().showDialog(0x112233);

			}
		});

		textSetting.setOnClickListener(new OnClickListener() {

			@SuppressWarnings("deprecation")
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				drawable = getResources().getDrawable(R.drawable.bg_press);
				textUserInfo.setBackgroundDrawable(null);
				textFinished.setBackgroundDrawable(null);
				textUnfinish.setBackgroundDrawable(null);
				textSetting.setBackgroundDrawable(drawable);
				textAbout.setBackgroundDrawable(null);
				textReturn.setBackgroundDrawable(null);
				textExit.setBackgroundDrawable(null);
				txt_campus.setBackgroundDrawable(null);
				if (index == 4)
					((SlidingActivity) getActivity()).getSlidingMenu().toggle();
				index = 4;
				FragmentManager fm = ((SlidingActivity) getActivity())
						.getFragmentManager();
				fm.beginTransaction().replace(R.id.unfinish, new newSettingFragment() )
				.commit();
				((SlidingActivity)getActivity()).getSlidingMenu().showContent();
			}
		});

		textAbout.setOnClickListener(new OnClickListener() {

			@SuppressWarnings("deprecation")
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				drawable = getResources().getDrawable(R.drawable.bg_press);
				textUserInfo.setBackgroundDrawable(null);
				textFinished.setBackgroundDrawable(null);
				textUnfinish.setBackgroundDrawable(null);
				textSetting.setBackgroundDrawable(null);
				textAbout.setBackgroundDrawable(drawable);
				textReturn.setBackgroundDrawable(null);
				textExit.setBackgroundDrawable(null);
				txt_campus.setBackgroundDrawable(null);
				if (index == 5)
					((SlidingActivity) getActivity()).getSlidingMenu().toggle();
				index = 5;
				FragmentManager fm = ((SlidingActivity) getActivity())
						.getFragmentManager();
				fm.beginTransaction().replace(R.id.unfinish, new AboutFragment() )
				.commit();
				((SlidingActivity)getActivity()).getSlidingMenu().showContent();	
			}
		});
		return view;
	}
	

	@Override
	public void onDestroy() {

		super.onDestroy();
	}

	@Override
	public void onDetach() {

		super.onDetach();
	}

	@Override
	public void onPause() {

		super.onPause();
	}

	@Override
	public void onResume() {

		super.onResume();
	}

	@Override
	public void onStart() {

		super.onStart();
	}

	@Override
	public void onStop() {

		super.onStop();
	}

	public static boolean isConnect(Context context) {
		// 获取手机所有连接管理对象（包括对wi-fi,net等连接的管理）
		try {
			ConnectivityManager connectivity = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			if (connectivity != null) {
				// 获取网络连接管理的对象
				NetworkInfo info = connectivity.getActiveNetworkInfo();
				if (info != null && info.isConnected()) {
					// 判断当前网络是否已经连接
					if (info.getState() == NetworkInfo.State.CONNECTED) {
						return true;
					}
				}
			}
		} catch (Exception e) {
		}
		return false;
	}

	public byte[] getbyte() {

		byte[] tmp = new byte[1000];
		byte[] db = null;
		ByteArrayOutputStream os = new ByteArrayOutputStream(1000);
		int n;
		try {
			File file = new File(
					"/data/data/com.telc.smartmemo/databases/campus.db3");
			FileInputStream is = new FileInputStream(file);
			while ((n = is.read(tmp)) != -1) {
				os.write(tmp, 0, n);
			}
			is.close();
			os.close();
			db = os.toByteArray();

		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return db;
	}

	@Override
	public void handleException(Object ex) {
		Toast toast = Toast.makeText(getActivity(), "请检查网络连接",
				Toast.LENGTH_SHORT);
		toast.show();

	}

	@Override
	public void handleResultOfWebService(String methodName, Object result) {
		if (webflag == true) {
			boolean flag = (Boolean) result;
			if (flag == true) {
				Toast toast = Toast.makeText(getActivity(), "同步成功",
						Toast.LENGTH_SHORT);
				toast.show();
			} else {
				Toast toast = Toast.makeText(getActivity(), "同步失败",
						Toast.LENGTH_SHORT);
				toast.show();
			}
		}else if(webflag == false){
			String tmp = result.toString();
			//转化成byte数组
			byte[] retByte = Base64.decode(tmp);
			createDatabase(retByte);
		}
	}
	public void createDatabase(byte[] db){
		String path = "/data/data/com.telc.smartmemo/databases/";
		File file=new File(path);
	    file.mkdir();
	    path=path+"campus.db3";
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
}
