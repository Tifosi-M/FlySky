package com.businesscard.ui.main;
import com.baidu.frontia.Frontia;
import com.baidu.frontia.api.FrontiaSocialShare;
import com.baidu.frontia.api.FrontiaSocialShare.FrontiaTheme;
import com.baidu.frontia.api.FrontiaSocialShareContent;
import com.baidu.frontia.api.FrontiaSocialShareListener;
import com.businesscard.domain.Emtity.NormalCardInfo;
import com.businesscard.domain.Service.NormalCardInfoService;
import com.businesscard.mobile.R;
import com.businesscard.ui.main.systemManagement.Conf;

import android.app.Activity;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.TextView;

public class ShareFrontiaActivity extends Activity{
	private FrontiaSocialShare mSocialShare;
	private FrontiaSocialShareContent mImageContent = new FrontiaSocialShareContent();
	private TextView tv_watch_card_info_name,
	tv_watch_card_info_mobilephone, tv_watch_card_info_telphone,
	tv_watch_card_info_email, tv_watch_card_info_QQ,
	tv_watch_card_info_professional, tv_watch_card_info_address;
	private String get_userPhone;
	private NormalCardInfo normalCardInfo;
	private NormalCardInfoService ncis;
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_share_frontia);
		Frontia.init(getApplicationContext(), Conf.APIKEY);
		mSocialShare = Frontia.getSocialShare();
		mSocialShare.setContext(this);
		normalCardInfo= new NormalCardInfo();
		SharedPreferences sharedPreferences = getSharedPreferences("current_user",
				MODE_PRIVATE);
		get_userPhone=sharedPreferences.getString("userPhone","");

		SQLiteDatabase db = openOrCreateDatabase(get_userPhone + ".db3",
				MODE_PRIVATE, null);
		ncis = new NormalCardInfoService(db);
		normalCardInfo = ncis.findMyCardByUserPhone(get_userPhone);
		tv_watch_card_info_name = (TextView)findViewById(R.id.tv_watch_card_info_name);
		tv_watch_card_info_mobilephone = (TextView)findViewById(R.id.tv_watch_card_info_mobilephone);
		tv_watch_card_info_telphone = (TextView)findViewById(R.id.tv_watch_card_info_telphone);
		tv_watch_card_info_email = (TextView) findViewById(R.id.tv_watch_card_info_email);
		tv_watch_card_info_QQ = (TextView) findViewById(R.id.tv_watch_card_info_QQ);
		tv_watch_card_info_professional = (TextView)findViewById(R.id.tv_watch_card_info_professional);
		tv_watch_card_info_address = (TextView)findViewById(R.id.tv_watch_card_info_address);

		tv_watch_card_info_name.setText(normalCardInfo.getUserName());
		tv_watch_card_info_mobilephone.setText(normalCardInfo.getUserPhone());
		tv_watch_card_info_telphone.setText(normalCardInfo.getUserTel());
		tv_watch_card_info_email.setText(normalCardInfo.getUserEmail());
		tv_watch_card_info_professional.setText(normalCardInfo
				.getUserProfessional());
		tv_watch_card_info_address.setText(normalCardInfo.getUserAddress());
		tv_watch_card_info_QQ.setText(normalCardInfo.getUserQQ());
		
		View view1 = getWindow().getDecorView();
		Display display = getWindowManager().getDefaultDisplay();
		view1.layout(0, 0, display.getWidth(), display.getHeight());
		view1.setDrawingCacheEnabled(true);
		Bitmap bitmap = Bitmap.createBitmap(view1.getDrawingCache());
		mImageContent.setContent("我的名片,与大家一起分享");
		mImageContent.setTitle("足迹·生活·移动名片");
		mImageContent.setLinkUrl("http://www.exg-card.com/");
		mImageContent.setImageData(bitmap);		
		mSocialShare.show(getWindow().getDecorView(), mImageContent, FrontiaTheme.DARK,  new ShareListener());
		
		
	}
	
	private class ShareListener implements FrontiaSocialShareListener {

		@Override
		public void onSuccess() {
			Log.d("Test","share success");
		}

		@Override
		public void onFailure(int errCode, String errMsg) {
			Log.d("Test","share errCode "+errCode);
		}

		@Override
		public void onCancel() {
			Log.d("Test","cancel ");
		}
		
	}
}
