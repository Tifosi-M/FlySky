package com.telc.ui.other;

import com.telc.smartmemo.R;

import android.app.Dialog;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class AboutFragment extends Fragment {

	private static final String version = "版本V1.0";
	private static final String user_title2 = "1.提醒:";
	private static final String user_title3 = "2.添加备忘录:";
	private static final String user_title4 = "3.查看备忘录:";
	private static final String user_title5 = "4.语音提醒:";
	private static final String user_title6 = "5.同步操作:";
	private static final String use_instructions2 = "为便于进行语音提醒，若注册时未使用本人电话号码，请修改为自己的电话号码，避免误提醒；";
	private static final String use_instructions3 = "添加备忘录，备忘录分为三种——定时备忘，定点备忘，定时定点备忘，添加定时定点备忘录时，打开地址选项即可；";
	private static final String use_instructions4 = "查看备忘录，主要包括——查看已完成记录和未完成记录，在菜单中，点击“已完成”“未完成”选项即可进行相应的查看；";
	private static final String use_instructions5 = "语音提醒，主要有地点提醒和定时提醒，若是到达您设定的地点或者时间，会提醒您该做的事情；";
	private static final String use_instructions6 = "同步操作，若是您选择此项操作，您将从数据库中下载您以前设置的备忘录，便于您在其他地方进行查看。";
	private static final String user_warning = "此版本适用于Android4.0及以上固件手机。本软件的下载、安装完全免费，适用过程中产生的GPRS数据流量费用，由运营商收取。";
	private static final String copyright = "CanFly团队 版权所有 \n"
			+ "Copyright © 2012-2013 CanFly \n" + "All rights reserved.";
	Context context;
	// 界面控件
	private TextView text_version;
	private TextView text_user_warning;
	private TextView text_copyright;
	private TextView txt_help_contest2, txt_help_contest3,
			txt_help_contest4, txt_help_contest5, txt_help_contest6;
	private TextView txt_help_title2, txt_help_title3,
			txt_help_title4, txt_help_title5, txt_help_title6;
	private Button btn_help;
	private Button btn_back;
	Dialog help_dialog;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	private void findRegAndEvt(View view) {
		text_version = (TextView) view.findViewById(R.id.soft_current_version);
		// text_struction = (TextView) view.findViewById(R.id.use_instructions);
		text_user_warning = (TextView) view.findViewById(R.id.user_warning);
		text_copyright = (TextView) view.findViewById(R.id.copyright);
		btn_help = (Button) view.findViewById(R.id.btn_help);
		context = view.getContext();
		text_version.setText(version);
		// text_struction.setText(use_instructions);
		text_user_warning.setText(user_warning);
		text_copyright.setText(copyright);

		btn_help.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				help_dialog = new Dialog(context);
				help_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
				LayoutInflater inflater = LayoutInflater.from(context);
				final View dialogView = inflater.inflate(R.layout.help_dialog,
						null);

				initHelpDialog(dialogView);
				help_dialog.setContentView(dialogView);
				help_dialog.show();
			}
		});
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.activity_about, null);
		// context = getActivity().getApplicationContext();
		findRegAndEvt(view);
		return view;
	}

	public void initHelpDialog(View dialogView) {
		
		txt_help_contest2 = (TextView) dialogView
				.findViewById(R.id.txt_help_contest2);
		txt_help_contest2.setText(use_instructions2);
		txt_help_title2=(TextView)dialogView
				.findViewById(R.id.txt_help_title2);
		txt_help_title2.setText(user_title2);
		
		txt_help_contest3 = (TextView) dialogView
				.findViewById(R.id.txt_help_contest3);
		txt_help_contest3.setText(use_instructions3);
		txt_help_title3=(TextView)dialogView
				.findViewById(R.id.txt_help_title3);
		txt_help_title3.setText(user_title3);
		
		txt_help_contest4 = (TextView) dialogView
				.findViewById(R.id.txt_help_contest4);
		txt_help_contest4.setText(use_instructions4);
		txt_help_title4=(TextView)dialogView
				.findViewById(R.id.txt_help_title4);
		txt_help_title4.setText(user_title4);
		
		txt_help_contest5 = (TextView) dialogView
				.findViewById(R.id.txt_help_contest5);
		txt_help_contest5.setText(use_instructions5);
		txt_help_title5=(TextView)dialogView
				.findViewById(R.id.txt_help_title5);
		txt_help_title5.setText(user_title5);
		
		txt_help_contest6 = (TextView) dialogView
				.findViewById(R.id.txt_help_contest6);
		txt_help_contest6.setText(use_instructions6);
		txt_help_title6=(TextView)dialogView
				.findViewById(R.id.txt_help_title6);
		txt_help_title6.setText(user_title6);

		btn_back = (Button) dialogView.findViewById(R.id.btn_help_back);
		btn_back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				help_dialog.dismiss();
			}
		});
	}
}
