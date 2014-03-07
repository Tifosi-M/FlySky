package com.businesscard.ui.main.viewModel;

import java.util.HashMap;

import webservice.MemoWebPara;
import webservice.WebServiceDelegate;
import webservice.WebServiceUtils;

import com.baidu.frontia.Frontia;
import com.baidu.frontia.api.FrontiaSocialShare;
import com.baidu.frontia.api.FrontiaSocialShareContent;
import com.baidu.frontia.api.FrontiaSocialShareListener;
import com.baidu.frontia.api.FrontiaSocialShare.FrontiaTheme;
import com.businesscard.domain.Emtity.NormalCardInfo;
import com.businesscard.domain.Emtity.UserCard;
import com.businesscard.domain.Service.NormalCardInfoService;
import com.businesscard.domain.tool.PinyinToolkit;
import com.businesscard.mobile.R;
import com.businesscard.ui.main.EditActivity;
import com.businesscard.ui.main.QRCodeActivity;
import com.businesscard.ui.main.ShareFrontiaActivity;
import com.businesscard.ui.main.SlidingActivity;
import com.businesscard.ui.main.systemManagement.Conf;
import com.businesscard.ui.main.systemManagement.LoginAndRegistActivity;

import android.app.Dialog;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainFragment extends Fragment implements WebServiceDelegate {

	private ImageButton btn_show;
	private TextView btn_share, btn_edit, tv_watch_card_info_name,
			tv_watch_card_info_mobilephone, tv_watch_card_info_telphone,
			tv_watch_card_info_email, tv_watch_card_info_QQ,
			tv_watch_card_info_professional, tv_watch_card_info_address,
			tv_share_create_qrcode, tv_share_by_message, tv_share_by_frontia,
			tv_share_by_soft;
	private Context context;
	private Dialog dialog_card_share, dialog_set_phone;
	private String get_userPhone;
	private NormalCardInfo normalCardInfo;
	private FrontiaSocialShare mSocialShare;
	private FrontiaSocialShareContent mImageContent = new FrontiaSocialShareContent();
	private EditText edt_phone;
	private Button btn_sure;
	private WebServiceUtils webService;
    private UserCard userCard;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		context = ((SlidingActivity) getActivity());
		get_userPhone = ((SlidingActivity) getActivity()).getUserPhone();
		Frontia.init(((SlidingActivity) getActivity()).getApplicationContext(),
				Conf.APIKEY);
		mSocialShare = Frontia.getSocialShare();
		mSocialShare.setContext(context);
		webService = new WebServiceUtils(MemoWebPara.MM_NAMESPACE,
				MemoWebPara.MM_URL, this);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.activity_main, null);
		SQLiteDatabase db = ((SlidingActivity) getActivity()).openDataBase();
		NormalCardInfoService ncis = new NormalCardInfoService(db);
		normalCardInfo = new NormalCardInfo();
		normalCardInfo = ncis.findMyCardByUserPhone(get_userPhone);
		userCard=new UserCard();
		userCard= ncis.findCardByUserPhone(get_userPhone);
		userCard.setUserNameFirstAlpha(PinyinToolkit.cn2FirstSpell(userCard.getUserName()));
		System.out.println(userCard.getUserId());
		btn_show = (ImageButton) view.findViewById(R.id.btn_show);
		btn_share = (TextView) view.findViewById(R.id.btn_share);
		btn_edit = (TextView) view.findViewById(R.id.btn_edit);
		tv_watch_card_info_name = (TextView) view
				.findViewById(R.id.tv_watch_card_info_name);
		tv_watch_card_info_mobilephone = (TextView) view
				.findViewById(R.id.tv_watch_card_info_mobilephone);
		tv_watch_card_info_telphone = (TextView) view
				.findViewById(R.id.tv_watch_card_info_telphone);
		tv_watch_card_info_email = (TextView) view
				.findViewById(R.id.tv_watch_card_info_email);
		tv_watch_card_info_QQ = (TextView) view
				.findViewById(R.id.tv_watch_card_info_QQ);
		tv_watch_card_info_professional = (TextView) view
				.findViewById(R.id.tv_watch_card_info_professional);
		tv_watch_card_info_address = (TextView) view
				.findViewById(R.id.tv_watch_card_info_address);

		tv_watch_card_info_name.setText(normalCardInfo.getUserName());
		tv_watch_card_info_mobilephone.setText(normalCardInfo.getUserPhone());
		tv_watch_card_info_telphone.setText(normalCardInfo.getUserTel());
		tv_watch_card_info_email.setText(normalCardInfo.getUserEmail());
		tv_watch_card_info_professional.setText(normalCardInfo
				.getUserProfessional());
		tv_watch_card_info_address.setText(normalCardInfo.getUserAddress());
		tv_watch_card_info_QQ.setText(normalCardInfo.getUserQQ());
		btn_show.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				((SlidingActivity) getActivity()).getSlidingMenu().showMenu();
			}
		});
		btn_edit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(((SlidingActivity) getActivity()),
						EditActivity.class);
				Bundle bundle = new Bundle(); // ����Bundle����
				bundle.putString("userPhone", get_userPhone); // װ�����
				intent.putExtras(bundle);
				startActivity(intent);
				((SlidingActivity) getActivity()).finish();
			}
		});
		btn_share.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				dialog_card_share = new Dialog(context);
				dialog_card_share.requestWindowFeature(Window.FEATURE_NO_TITLE);
				LayoutInflater inflater = LayoutInflater
						.from(((SlidingActivity) getActivity()));
				final View dialogView = inflater.inflate(
						R.layout.dialog_card_share, null);
				Button mCancel=(Button) dialogView.findViewById(R.id.dialogCardShareCancel);
				mCancel.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						dialog_card_share.cancel();
					}
				});
				dialog_card_share.setContentView(dialogView);
				InitShareCardDialog(dialogView);
				dialog_card_share.show();
			}
		});
		return view;
	}

	protected void InitShareCardDialog(View dialogView) {
		tv_share_create_qrcode = (TextView) dialogView
				.findViewById(R.id.tv_share_create_qrcode);
		tv_share_create_qrcode.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(((SlidingActivity) getActivity()),
						QRCodeActivity.class);
				startActivity(intent);
				dialog_card_share.dismiss();
			}
		});

		tv_share_by_soft = (TextView) dialogView
				.findViewById(R.id.tv_share_by_soft);
		tv_share_by_soft.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				dialog_card_share.dismiss();
				dialog_set_phone = new Dialog(context);
				dialog_set_phone.requestWindowFeature(Window.FEATURE_NO_TITLE);
				LayoutInflater inflater = LayoutInflater
						.from(((SlidingActivity) getActivity()));
				final View dialogPhoneView = inflater.inflate(
						R.layout.dialog_set_phone, null);
				dialog_set_phone.setContentView(dialogPhoneView);
				edt_phone = (EditText) dialogPhoneView
						.findViewById(R.id.edt_phone);
				btn_sure = (Button) dialogPhoneView
						.findViewById(R.id.btn_sure);
				btn_sure.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						if (edt_phone.getText().toString().equals("")) {
							Toast toast = Toast.makeText(
									((SlidingActivity) getActivity()),
									"�绰���벻Ϊ��", Toast.LENGTH_SHORT);
							toast.show();
						} else {
							HashMap<String, Object> args = new HashMap<String, Object>();
							String u = edt_phone.getText().toString();
							args.put("tel", u);
							args.put("myCardInfo", userCard);
							webService.callWebService(
									"updateCardDBFileInsertInfo", args,
									boolean.class);

						}
					}
				});
				dialog_set_phone.show();
			}
		});

		tv_share_by_frontia = (TextView) dialogView
				.findViewById(R.id.tv_share_by_frontia);
		tv_share_by_frontia.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				// Intent intent = new Intent(((SlidingActivity) getActivity()),
				// ShareFrontiaActivity.class);
				// startActivity(intent);
				dialog_card_share.dismiss();
				View view1 = ((SlidingActivity) getActivity()).getWindow()
						.getDecorView();
				Display display = ((SlidingActivity) getActivity())
						.getWindowManager().getDefaultDisplay();
				view1.layout(0, 0, display.getWidth(), display.getHeight());
				view1.setDrawingCacheEnabled(true);
				Bitmap bitmap = Bitmap.createBitmap(view1.getDrawingCache());
				mImageContent.setContent("�ҵ���Ƭ,����һ�����");
				mImageContent.setTitle("�㼣�����ƶ���Ƭ");
				mImageContent.setLinkUrl("http://www.exg-card.com/");
				mImageContent.setImageData(bitmap);
				mSocialShare.show(((SlidingActivity) getActivity()).getWindow()
						.getDecorView(), mImageContent, FrontiaTheme.DARK,
						new ShareListener());

			}
		});
		tv_share_by_message = (TextView) dialogView
				.findViewById(R.id.tv_share_by_message);
		tv_share_by_message.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String sms = "����" + normalCardInfo.getUserName() + "\n"
						+ "�ֻ�" + normalCardInfo.getUserPhone();
				if (!normalCardInfo.getUserTel().equals("")) {
					sms = sms + "\n" + "�绰��" + normalCardInfo.getUserTel();
				}
				if (!normalCardInfo.getUserEmail().equals("")) {
					sms = sms + "\n" + "���䣺" + normalCardInfo.getUserEmail();
				}
				if (!normalCardInfo.getUserQQ().equals("")) {
					sms = sms + "\n" + "QQ��" + normalCardInfo.getUserQQ();
				}
				if (!normalCardInfo.getUserProfessional().equals("")) {
					sms = sms + "\n" + "ְҵ��"
							+ normalCardInfo.getUserProfessional();
				}
				if (!normalCardInfo.getUserAddress().equals("")) {
					sms = sms + "\n" + "��ַ��" + normalCardInfo.getUserAddress();
				}
				Uri smsToUri = Uri.parse("smsto:");// ��ϵ�˵�ַ
				Intent mIntent = new Intent(
						android.content.Intent.ACTION_SENDTO, smsToUri);
				mIntent.putExtra("sms_body", sms);// ��������
				context.startActivity(mIntent);
				dialog_card_share.dismiss();
			}
		});
	}

	public void ResetData() {
		SQLiteDatabase db = ((SlidingActivity) getActivity()).openDataBase();
		NormalCardInfoService ncis = new NormalCardInfoService(db);
		normalCardInfo = new NormalCardInfo();
		normalCardInfo = ncis.findMyCardByUserPhone(get_userPhone);
		tv_watch_card_info_name.setText(normalCardInfo.getUserName());
		tv_watch_card_info_mobilephone.setText(normalCardInfo.getUserPhone());
		tv_watch_card_info_telphone.setText(normalCardInfo.getUserTel());
		tv_watch_card_info_email.setText(normalCardInfo.getUserEmail());
		tv_watch_card_info_professional.setText(normalCardInfo
				.getUserProfessional());
		tv_watch_card_info_address.setText(normalCardInfo.getUserAddress());
		tv_watch_card_info_QQ.setText(normalCardInfo.getUserQQ());
	}

	private class ShareListener implements FrontiaSocialShareListener {

		@Override
		public void onSuccess() {
			Log.d("Test", "share success");
		}

		@Override
		public void onFailure(int errCode, String errMsg) {
			Log.d("Test", "share errCode " + errCode);
		}

		@Override
		public void onCancel() {
			Log.d("Test", "cancel ");
		}

	}

	@Override
	public void handleException(Object ex) {
		// TODO Auto-generated method stub
		Toast toast = Toast.makeText(((SlidingActivity) getActivity()),
				"������������", Toast.LENGTH_SHORT);
		toast.show();
	}

	@Override
	public void handleResultOfWebService(String methodName, Object result) {
		// TODO Auto-generated method stub
		boolean flag = (Boolean) result;
		if (flag == true) {
			Toast toast = Toast.makeText(((SlidingActivity) getActivity()),
					"���ͳɹ�", Toast.LENGTH_SHORT);
			toast.show();
		} else {
			Toast toast = Toast.makeText(((SlidingActivity) getActivity()),
					"�Է��˺Ų�����", Toast.LENGTH_SHORT);
			toast.show();
		}
	}

}
