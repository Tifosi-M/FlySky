package com.findyou.ui.main;


import java.util.ArrayList;
import java.util.List;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.map.ItemizedOverlay;
import com.baidu.mapapi.map.LocationData;
import com.baidu.mapapi.map.MapController;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationOverlay;
import com.baidu.mapapi.map.OverlayItem;
import com.baidu.mapapi.map.PopupClickListener;
import com.baidu.mapapi.map.PopupOverlay;
import com.baidu.platform.comapi.basestruct.GeoPoint;
import com.findyou.R;
import com.findyou.app.FindYouApplication;
import com.findyou.domain.Service.MyNewsService;
import com.findyou.domain.entity.News;
import com.findyou.domain.tool.BMapUtil;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MyFootprintActivity extends Activity{
		
		private FindYouApplication app;
		private MyNewsService mNewsService=null;
		
		OverlayTest itemOverlay=null;
		private OverlayItem mCurItem = null;
		
		// 定位相关
		private LocationClient mLocClient;
		private LocationData locData = null;
		private LocationClientOption option = null;
		
		public MyLocationListenner myListener = new MyLocationListenner();
		
		//定位图层
		MyLocationOverlay myLocationOverlay = null;

		//弹出泡泡图层
		private PopupOverlay  pop  = null;//弹出泡泡图层，浏览节点时候使用
		private TextView  popupText = null;
		private View viewCache = null;
		private View popupInfo = null;
		private View popupLeft = null;
		private View popupRight = null;
		
		//自定义MapView 重写touch事件来实现泡泡处理
		MyLocationMapView mMapView = null;
		//设置启用内置的缩放控件
		private MapController mMapController = null;
		
		
		//UI 相关
		private Button mClearBtn;
		private Button mResetBtn;
		private Button mLocationBtn;
		
		private boolean isRequest = false;//是否是手动触发的定位请求
		private boolean isFirstLoc = true;//是否首次定位
		
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			app = (FindYouApplication)this.getApplication();
			mNewsService=new MyNewsService();
	        if (app.mBMapManager == null) {
	            app.mBMapManager = new BMapManager(getApplicationContext());
	            app.mBMapManager.init(null, new FindYouApplication.MyGeneralListener());

	        }
		
	    setContentView(R.layout.activity_new_my_footprint);
		
		mClearBtn = (Button) findViewById(R.id.clear);
		mClearBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				clearOverlay();
			}
		});
		
	    mResetBtn = (Button) findViewById(R.id.reset);
	    mResetBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
	
				initOverlay();
			}
		});
	    
	    mLocationBtn = (Button)findViewById(R.id.location_button);
	    mLocationBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				requestLocationClick();				
			}
		});
			
		mMapView=(MyLocationMapView)findViewById(R.id.bmapsView);
		mMapController=mMapView.getController();
        mMapView.getController().setZoom(14);
        mMapView.getController().enableClick(true);
        mMapView.setBuiltInZoomControls(true);
       //创建 弹出泡泡图层
        createPaopao();
        
		//定位初始化
		mLocClient = new LocationClient(getApplicationContext());
		locData = new LocationData();
		mLocClient.registerLocationListener(myListener);
		option = new LocationClientOption();
		option.setOpenGps(true);
		option.setScanSpan(1000);
		mLocClient.setLocOption(option);
		mLocClient.start();
		
		 //定位图层初始化
		myLocationOverlay = new MyLocationOverlay(mMapView);
		//设置定位数据
	    myLocationOverlay.setData(locData);
	    //定位图层 图标绘制
	    myLocationOverlay.setMarker(null);
	    //添加定位图层
		mMapView.getOverlays().add(myLocationOverlay);
		myLocationOverlay.enableCompass();
		//修改定位数据后刷新图层生效
		mMapView.refresh();
		
	
		}
		

		public void createPaopao(){
			viewCache = getLayoutInflater().inflate(R.layout.map_custom_text_view, null);
	        popupText =(TextView) viewCache.findViewById(R.id.textcache);
	        //泡泡点击响应回调
	        PopupClickListener popListener = new PopupClickListener(){
				@Override
				public void onClickedPopup(int index) {
//					Toast.makeText(getApplicationContext(), "haha", 200).show();
				}
	        };
	        pop = new PopupOverlay(mMapView,popListener);
	        MyLocationMapView.pop = pop;
		}		
		
		
		//手动触发一次请求定位事件
		public void requestLocationClick(){
			
			isRequest =true;
			 mLocClient.requestLocation();
		    Toast.makeText(MyFootprintActivity.this, "定位中 请稍后。。。", Toast.LENGTH_SHORT).show();
		}
		
		
		//清除屏幕上的标志 
		public  void clearOverlay(){
			
			if (pop != null){
		        pop.hidePop();
			}
			if(itemOverlay != null  && itemOverlay.size() > 0){
				 //清除overlay  
				 itemOverlay.removeAll();  
				 mMapView.refresh();
			 }
		}
		
		//添加标志
		public void initOverlay(){
			List<News> mNewsList = new ArrayList<News>();
			//这里填写要显示的地点的经纬度  举例四个
	
			// 119.21749
			//26.026232
			
			
			News new1 = new News();
			News new2 = new News();
			News new3 = new News();
			
			new1.setNewsLatitude(26.030000);
			new1.setNewsLongtitude(119.21749);
			new1.setNewsContent(" 旗山师大很漂亮 ");
			
			new2.setNewsLatitude(26.046232);
			new2.setNewsLongtitude(119.22749);
			new2.setNewsContent("  我在闽江旁看风景 ");
			
			new3.setNewsLatitude(26.012409174100426);
			new3.setNewsLongtitude(119.21682000160217);
			new3.setNewsContent("  溪源江，看风景。。  ");
			
			mNewsList.add(new1);
			mNewsList.add(new2);
			mNewsList.add(new3);
			
			/*
			 * 暂时注释掉     这个地方是从服务器上获取列表数据  现在用上面的假数据代替下
			 * */
//			mNewsList=mNewsService.getMyNewsList();
			
		
			List<GeoPoint> listPoint =  new ArrayList<GeoPoint>();
			
			GeoPoint[] point=new GeoPoint[mNewsList.size()];

			for(int i=0;i<mNewsList.size();i++){
				point[i] =new GeoPoint((int)(mNewsList.get(i).getNewsLatitude() * 1E6),(int)(mNewsList.get(i).getNewsLongtitude() * 1E6));
				listPoint.add(point[i]);
			}
			//准备overlay图像数据，根据实情情况修复
			Drawable mark= getResources().getDrawable(R.drawable.map_mark);
			
			//创建IteminizedOverlay
			itemOverlay = new OverlayTest(mark, mMapView);
			 
			List<OverlayItem> listItems = new ArrayList<OverlayItem>();

			//创建IteminizedOverlay
			itemOverlay = new OverlayTest(mark, mMapView);
			//将IteminizedOverlay添加到MapView中
			for(int i=0;i<mNewsList.size();i++){
				OverlayItem item =new OverlayItem(listPoint.get(i), mNewsList.get(i).getNewsContent(), "");
				itemOverlay.addItem(item);
			}
			mMapView.getOverlays().clear();
			mMapView.getOverlays().add(itemOverlay);
			
			
			viewCache = getLayoutInflater().inflate(R.layout.map_custom_text_view, null);
		    popupInfo = (View) viewCache.findViewById(R.id.popinfo);
//		    popupLeft = (View) viewCache.findViewById(R.id.popleft);
//		    popupRight = (View) viewCache.findViewById(R.id.popright);
		    popupText =(TextView) viewCache.findViewById(R.id.textcache);
		    
			mMapView.refresh();
			 
		}
		
		
		
		@Override
		protected void onDestroy(){
		        mMapView.destroy();
		        if(mLocClient != null)
		        	mLocClient.stop();
		        
		        super.onDestroy();
		}
		@Override
		protected void onPause(){
		        mMapView.onPause();
		        super.onPause();
		}
		@Override
		protected void onResume(){
		        mMapView.onResume();
		    super.onResume();
		}
		
	    @Override
	    protected void onSaveInstanceState(Bundle outState) {
	    	super.onSaveInstanceState(outState);
	    	mMapView.onSaveInstanceState(outState);
	    	
	    }
	    
	    @Override
	    protected void onRestoreInstanceState(Bundle savedInstanceState) {
	    	super.onRestoreInstanceState(savedInstanceState);
	    	mMapView.onRestoreInstanceState(savedInstanceState);
	    }
	    
	    @Override
	    public boolean onCreateOptionsMenu(Menu menu) {
//	        getMenuInflater().inflate(R.menu.activity_main, menu);
	        return true;
	    }
		
		/*
		 * 要处理overlay点击事件时需要继承ItemizedOverlay
		 * 不处理点击事件时可直接生成ItemizedOverlay.
		 */
		class OverlayTest extends ItemizedOverlay<OverlayItem> {
		    //用MapView构造ItemizedOverlay
		    public OverlayTest(Drawable mark,MapView mapView){
		            super(mark,mapView);
		    }
		    protected boolean onTap(int index) {
		        //在此处理item点击事件
		       OverlayItem item = getItem(index);
				mCurItem = item ;
				   popupText.setText(getItem(index).getTitle());
				   Bitmap[] bitMaps={
//					    BMapUtil.getBitmapFromView(popupLeft), 		
					    BMapUtil.getBitmapFromView(popupInfo), 		
//					    BMapUtil.getBitmapFromView(popupRight) 		
				    };
				    pop.showPopup(bitMaps,item.getPoint(),32);
				    
		        return true;
		    }
		        public boolean onTap(GeoPoint pt, MapView mapView){
		                //在此处理MapView的点击事件，当返回 true时
		                super.onTap(pt,mapView);
		                return false;
		        }

		}     
		
		
		    
		//定位
		public class MyLocationListenner implements BDLocationListener{

			@Override
			public void onReceiveLocation(BDLocation location) {
				
				if(location == null)
					return;
				
//				Toast.makeText(getApplicationContext(), "a", 200).show();
				
				locData.latitude = location.getLatitude();
	            locData.longitude = location.getLongitude();
	            //如果不显示定位精度圈，将accuracy赋值为0即可 
	            locData.accuracy = location.getRadius();
	            locData.direction = location.getDerect();
	            //更新定位数据
	            myLocationOverlay.setData(locData);
	            //更新图层数据执行刷新后生效
	            mMapView.refresh();
	            
	          //是手动触发请求或首次定位时，移动到定位点
	            if (isRequest || isFirstLoc){
	            	//移动地图到定位点
	                mMapController.animateTo(new GeoPoint((int)(locData.latitude* 1e6), (int)(locData.longitude *  1e6)));

	                isRequest = false;
	                
	        		option.setScanSpan(30000);
	        		mLocClient.setLocOption(option);

	            }
	            //首次定位完成
	            isFirstLoc = false;
			}

			@Override
			public void onReceivePoi(BDLocation poiLocation) {

				if(poiLocation ==  null)
					return;
			}
			
		}
		
}

	class MyLocationMapView extends MapView{
  		static PopupOverlay   pop  = null;//弹出泡泡图层，点击图标使用
  		public MyLocationMapView(Context context) {
  			super(context);
  			// TODO Auto-generated constructor stub
  		}
  		public MyLocationMapView(Context context, AttributeSet attrs){
  			super(context,attrs);
  		}
  		public MyLocationMapView(Context context, AttributeSet attrs, int defStyle){
  			super(context, attrs, defStyle);
  		}
  		@Override
  	    public boolean onTouchEvent(MotionEvent event){
  			if (!super.onTouchEvent(event)){
  				//消隐泡泡
  				if (pop != null && event.getAction() == MotionEvent.ACTION_UP)
  					pop.hidePop();
  			}
  			return true;
  		}
    
  	}