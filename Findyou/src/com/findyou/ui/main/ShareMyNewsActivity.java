package com.findyou.ui.main;

import com.findyou.domain.tool.Calculation;
import com.findyou.R;
import com.findyou.domain.Service.MyNewsService;
import com.findyou.domain.entity.News;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ShareMyNewsActivity extends Activity{
	
	private String categoryString = null;
	private String mylocationplcaeString = null;
	private MyNewsService mNewsService=null;
	private SharedPreferences sp;
	private News mNews;
	private ArrayAdapter<String> categoryAdapter= null;
	
	private Button sharemylocation_publishButton;
	private TextView sharemylocation_placetTextView;
	private Spinner sharemylocation_categorySpinner;
	private EditText sharemylocation_shareinfoEditText;
	
	private ProgressDialog progressDialog;

	
	private static final String []category = {"心情","美食"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		sp=getPreferences(MODE_PRIVATE);
		
		setContentView(R.layout.activity_share_mylocation);
		
		init();

		sharemylocation_publishButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String shareinfoString =  sharemylocation_shareinfoEditText.getText().toString().trim();
				if(shareinfoString.equals("") || shareinfoString == null){
					Toast.makeText(getApplicationContext(), "亲，说点什么吧！（分享内容不能为空哦！）", Toast.LENGTH_SHORT).show();
					return;
				}
				String myLocationNameString =  sharemylocation_placetTextView.getText().toString().trim();
				if(myLocationNameString.equals("") || myLocationNameString == null){
					Toast.makeText(getApplicationContext(), "亲，定位不成功哦！）", Toast.LENGTH_SHORT).show();
					return;
				}
				
				//在这里 将获取到的位置  还有经纬度  还有分享的内容 类别 发布到服务器
//				if(mNewsService.shareMyUserInfo(mNews.getNewsLatitude(), mNews.getNewsLongtitude(), categoryString, shareinfoString))
//				{
//					Toast.makeText(ShareMyNewsActivity.this, "发布成功！", Toast.LENGTH_SHORT).show();
					progressDialog = ProgressDialog.show(ShareMyNewsActivity.this, "请稍后", "正在上传数据到服务器。。。", true, false);
					
					new Thread(){

						public void run() {
							Calculation.calculate(4);//在8秒时间内获取数据
							
							//向handler发消息
							pd_handler.sendEmptyMessage(0);
						}}.start();

					
//				}
//				else{
//					Toast.makeText(ShareMyNewsActivity.this, "发布失败！", Toast.LENGTH_SHORT).show();
//				}
			}
		});
		
	}
	
	//初始化各控件
	private void init() {
		mNewsService=new MyNewsService();
		sharemylocation_publishButton = (Button) findViewById(R.id.bt_sharemylocation_publish);
		
		mNews=new News();
		mNews.setNewsUserName(sp.getString("userName", ""));
		sharemylocation_placetTextView = (TextView)findViewById(R.id.tv_sharemylocation_place);
		
		SharedPreferences ferences=getSharedPreferences("LOCATION_INFO",0);  
        String mylocationplcaeString = ferences.getString("Location_name", "");  
        
        sharemylocation_placetTextView.setText(mylocationplcaeString);
		
		
		sharemylocation_categorySpinner =  (Spinner) findViewById(R.id.sp_sharemylocation_category);
		categoryAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item,category);
		categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sharemylocation_categorySpinner.setAdapter(categoryAdapter);
		sharemylocation_categorySpinner.setOnItemSelectedListener(new SpinnerCategorySelected());
		
		sharemylocation_shareinfoEditText = (EditText) findViewById(R.id.ed_sharemylocation_shareinfo);
		
		
		progressDialog = ProgressDialog.show(ShareMyNewsActivity.this, "请稍后", "正在定位。。。。", true, false);
		
		new Thread(){

			public void run() {
				Calculation.calculate(4);//在8秒时间内获取数据
				
				//向handler发消息
				pd_handlerFirst.sendEmptyMessage(0);
			}}.start();

		

	}
	
	//接收信号 关闭progress框
		private Handler pd_handler = new Handler(){

			@Override
			public void handleMessage(Message msg) {
				
					Toast.makeText(getApplicationContext(), "服务器已迁移，数据上传失败！", Toast.LENGTH_SHORT).show();
					//关闭ProgressDialog
					progressDialog.dismiss();
			}};
			private Handler pd_handlerFirst = new Handler(){

				@Override
				public void handleMessage(Message msg) {
					
						//关闭ProgressDialog
						progressDialog.dismiss();
				}};
			
	
	//spinner  下拉框响应事件
	class SpinnerCategorySelected implements OnItemSelectedListener{

		@Override
		public void onItemSelected(AdapterView<?> arg0, View view, int num,
				long arg3) {
			
			TextView tv = (TextView)view;
			tv.setTextColor(getResources().getColor(R.color.black));//设置显示字体的颜色
			
			switch (num) {
			case 0:
				categoryString = "心情";
				break;
			case 1:
				categoryString = "美食";
				break;

			}			
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {	
			categoryString = "心情";
		}
		
	}
	
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
	}
	
}
