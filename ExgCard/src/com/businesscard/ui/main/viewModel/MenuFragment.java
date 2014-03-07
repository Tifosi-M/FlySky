package com.businesscard.ui.main.viewModel;

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

import com.businesscard.mobile.R;
import com.businesscard.ui.main.SlidingActivity;
import com.businesscard.ui.main.ViewPagerActivity;
import com.businesscard.ui.main.systemManagement.LoginAndRegistActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MenuFragment extends Fragment implements WebServiceDelegate {
	int index = 0;
	TextView txt_account_management, txt_check_version, txt_setting,
			txt_opinion, txt_news, txt_share, txt_weibo, txt_about_us,
			txt_card_management, txt_card_holder, txt_return, txt_exit,
			tv_backup_cloud, tv_synchronization;
	private Dialog dialog_card_management, dialog_account_management,
			dialog_synchronization, dialog_about_us;
	private Context context;
	private String get_userPhone;
	private boolean webflag = false;
	private WebServiceUtils webService;
	private static final String user_warning = "此版本适用于Android2.3及以上固件手机。本软件的下载、安装完全免费，适用过程中产生的GPRS数据流量费用，由运营商收取。";
	private static final String copyright = "CanFly团队 版权所有 \n"
			+ "Copyright © 2012-2013 CanFly \n" + "All rights reserved.";
	private static final String version = "版本V1.0";
	private TextView text_version;
	private TextView text_user_warning;
	private TextView text_copyright;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		context = ((SlidingActivity) getActivity());
		get_userPhone = ((SlidingActivity) getActivity()).getUserPhone();
		webService = new WebServiceUtils(MemoWebPara.MM_NAMESPACE,
				MemoWebPara.MM_URL, this);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.activity_menu, null);
		txt_card_management = (TextView) view
				.findViewById(R.id.txt_card_management);
		txt_card_management.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				dialog_card_management = new Dialog(context);
				dialog_card_management
						.requestWindowFeature(Window.FEATURE_NO_TITLE);
				LayoutInflater inflater = LayoutInflater
						.from(((SlidingActivity) getActivity()));
				final View dialogView = inflater.inflate(
						R.layout.dialog_card_management, null);
				dialog_card_management.setContentView(dialogView);
				InitCardManagementDialog(dialogView);
				dialog_card_management.show();
			}
		});
		txt_account_management = (TextView) view
				.findViewById(R.id.txt_account_management);
		txt_account_management.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				dialog_account_management = new Dialog(context);
				dialog_account_management
						.requestWindowFeature(Window.FEATURE_NO_TITLE);
				LayoutInflater inflater = LayoutInflater
						.from(((SlidingActivity) getActivity()));
				final View dialogView = inflater.inflate(
						R.layout.dialog_account_management, null);
				dialog_account_management.setContentView(dialogView);
				InitAccountManagementDialog(dialogView);
				dialog_account_management.show();
			}
		});
		txt_check_version = (TextView) view
				.findViewById(R.id.txt_check_version);
		txt_check_version.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			}
		});
		txt_setting = (TextView) view.findViewById(R.id.txt_setting);
		txt_setting.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				dialog_synchronization = new Dialog(context);
				dialog_synchronization
						.requestWindowFeature(Window.FEATURE_NO_TITLE);
				LayoutInflater inflater = LayoutInflater
						.from(((SlidingActivity) getActivity()));
				final View dialogView = inflater.inflate(
						R.layout.dialog_synchronization, null);
				dialog_synchronization.setContentView(dialogView);
				InitSynchronizationDialog(dialogView);
				dialog_synchronization.show();

			}
		});
		txt_opinion = (TextView) view.findViewById(R.id.txt_opinion);
		txt_opinion.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			}
		});
		txt_news = (TextView) view.findViewById(R.id.txt_news);
		txt_news.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			}
		});
		txt_share = (TextView) view.findViewById(R.id.txt_share);
		txt_share.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			}
		});
		txt_weibo = (TextView) view.findViewById(R.id.txt_weibo);
		txt_weibo.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			}
		});
		txt_about_us = (TextView) view.findViewById(R.id.txt_about_us);
		txt_about_us.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				dialog_about_us = new Dialog(context);
				dialog_about_us.requestWindowFeature(Window.FEATURE_NO_TITLE);
				LayoutInflater inflater = LayoutInflater
						.from(((SlidingActivity) getActivity()));
				final View dialogView = inflater.inflate(
						R.layout.dialog_about_us, null);
				dialog_about_us.setContentView(dialogView);
				InitAboutUsDialog(dialogView);
				dialog_about_us.show();
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

	public byte[] getbyte() {

		byte[] tmp = new byte[1000];
		byte[] db = null;
		ByteArrayOutputStream os = new ByteArrayOutputStream(1000);
		int n;
		try {
			File file = new File(
					"/data/data/com.businesscard.mobile/databases/"
							+ get_userPhone + ".db3");
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

	public void uploadDbFile() {
		byte[] db = getbyte();
		HashMap<String, Object> args = new HashMap<String, Object>();
		args.put("tel", get_userPhone);
		args.put("db", db);
		MyProgressDialog.show(getActivity(), "上传数据中，请稍后……", false, false);
		webService.callWebService("uploadCardDBFile", args, boolean.class);
	}

	protected void InitCardManagementDialog(View dialogView) {
		txt_card_holder = (TextView) dialogView
				.findViewById(R.id.txt_card_holder);
		txt_card_holder.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(((SlidingActivity) getActivity()),
						ViewPagerActivity.class);
				startActivity(intent);
				dialog_card_management.dismiss();
			}
		});
		
		Button btnCancel=(Button) dialogView.findViewById(R.id.dialogBtnCardManageCancel);
		btnCancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dialog_card_management.cancel();
			}
		});
	}

	protected void InitAboutUsDialog(View dialogView) {
		text_version = (TextView) dialogView.findViewById(R.id.soft_current_version);
		text_user_warning = (TextView) dialogView.findViewById(R.id.user_warning);
		text_copyright = (TextView) dialogView.findViewById(R.id.copyright);
		text_version.setText(version);
		text_user_warning.setText(user_warning);
		text_copyright.setText(copyright);
	}

	protected void InitAccountManagementDialog(View dialogView) {
		txt_return = (TextView) dialogView.findViewById(R.id.txt_return);
		txt_return.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Editor edit = ((SlidingActivity) getActivity()).sp.edit();
				edit.putBoolean("login_in", false);
				edit.commit();
				Intent intent = new Intent(((SlidingActivity) getActivity()),
						LoginAndRegistActivity.class);
				startActivity(intent);
				dialog_account_management.dismiss();
				((SlidingActivity) getActivity()).finish();
			}
		});

		Button btnCancel=(Button) dialogView.findViewById(R.id.dialogAccountManageCancel);
		btnCancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dialog_account_management.dismiss();
			}
		});
		
		txt_exit = (TextView) dialogView.findViewById(R.id.txt_exit);
		txt_exit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				getActivity().finish();
			}
		});
	}

	protected void InitSynchronizationDialog(View dialogView) {
		tv_backup_cloud = (TextView) dialogView
				.findViewById(R.id.tv_backup_cloud);
		tv_backup_cloud.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				webflag = true;
				uploadDbFile();
			}
		});
		tv_synchronization = (TextView) dialogView
				.findViewById(R.id.tv_synchronization);
		tv_synchronization.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				webflag = false;
				HashMap<String, Object> args = new HashMap<String, Object>();
				args.put("arg0", get_userPhone);
				MyProgressDialog.show(getActivity(), "下载数据中，请稍后……", false, false);
				webService.callWebService("downloadCardDBFile", args,
						byte[].class);
			}
		});
	}

	@Override
	public void handleException(Object ex) {
		// TODO Auto-generated method stub
		MyProgressDialog.cancel();
		Toast toast = Toast.makeText(getActivity(), "请检查网络连接",
				Toast.LENGTH_SHORT);
		toast.show();
	}

	@Override
	public void handleResultOfWebService(String methodName, Object result) {
		// TODO Auto-generated method stub
		if (webflag == true) {
			boolean flag = (Boolean) result;
			if (flag == true) {
				MyProgressDialog.cancel();
				Toast toast = Toast.makeText(getActivity(), "备份成功",
						Toast.LENGTH_SHORT);
				toast.show();
				dialog_synchronization.dismiss();
			} else {
				MyProgressDialog.cancel();
				Toast toast = Toast.makeText(getActivity(), "备份失败",
						Toast.LENGTH_SHORT);
				toast.show();
			}
		} else if (webflag == false) {
			String tmp = result.toString();
			// 转化成byte数组
			byte[] retByte = Base64.decode(tmp);
			createDatabase(retByte);
			((SlidingActivity) getActivity()).getMainFragment().ResetData();

			dialog_synchronization.dismiss();
			MyProgressDialog.cancel();
			Toast toast = Toast.makeText(getActivity(), "下载成功",
					Toast.LENGTH_SHORT);
			toast.show();
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
}
