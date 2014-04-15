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
import com.findyou.domain.entity.UserInfo;
import com.findyou.domain.tool.BMapUtil;
import com.findyou.ui.main.ShareMyNewsActivity.MyLocationListenner;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MyFootprintActivity extends Activity{
		
		private MapView mMapView = null;
		private FindYouApplication app;
		private MyNewsService mNewsService=null;
		private Button mClearBtn;
		private Button mResetBtn;
		private Button mLocationBtn;
		
		private PopupOverlay  pop  = null;
	
		private TextView  popupText = null;
		private View viewCache = null;
		private View popupInfo = null;
		private View popupLeft = null;
		private View popupRight = null;
		
		OverlayTest itemOverlay=null;
		private OverlayItem mCurItem = null;
		
		//设置启用内置的缩放控件
		private MapController mMapController = null;
		
		// 定位相关
//		private LocationClient mLocationClient;
//		private LocationData locData = null;
//		
//		public MyLocationListenner myListener = new MyLocationListenner();
//		private boolean isRequest = false;//是否是手动触发的定位请求
//		private boolean isFirstLoc = true;//是否首次定位
//		
//		//定位图层
//		MyLocationOverlay myLocationOverlay = null;
		
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			app = (FindYouApplication)this.getApplication();
			mNewsService=new MyNewsService();
	        if (app.mBMapManager == null) {
	            app.mBMapManager = new BMapManager(getApplicationContext());
	            app.mBMapManager.init(FindYouApplication.strKey,null);
	        }
		
//		setContentView(R.layout.activity_my_footprint);
	    setContentView(R.layout.activity_new_my_footprint);
		
		mMapView=(MapView)findViewById(R.id.bmapsView);
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
//				requestLocationClick();				
			}
		});
			
	    
        
		mMapView.setBuiltInZoomControls(true);
		
		mMapController=mMapView.getController();
		// 得到mMapView的控制权,可以用它控制和驱动平移和缩放
		GeoPoint point =new GeoPoint((int)(39.915* 1E6),(int)(116.404* 1E6));
		//用给定的经纬度构造一个GeoPoint，单位是微度 (度 * 1E6)
		mMapController.setCenter(point);//设置地图中心点
		mMapController.setZoom(12);//设置地图zoom级别
		
		//定位初始化
//		mLocationClient = new LocationClient(getApplicationContext());
//		locData = new LocationData();
//		MyLocationListenner myListenner = new MyLocationListenner();
//		mLocationClient.registerLocationListener(myListenner);
//		LocationClientOption option = new LocationClientOption();
//		option.setOpenGps(true);
//		option.setScanSpan(5000);
//		mLocationClient.setLocOption(option);
//		mLocationClient.start();
//		
//		 //定位图层初始化
//		myLocationOverlay = new MyLocationOverlay(mMapView);
//		//设置定位数据
//	    myLocationOverlay.setData(locData);
//	    //添加定位图层
//		mMapView.getOverlays().add(myLocationOverlay);
//		myLocationOverlay.enableCompass();
		//修改定位数据后刷新图层生效
		mMapView.refresh();
		
	
	    pop = new PopupOverlay(mMapView,popListener);
		    
		}
		
//		//创建pop对象，注册点击事件监听接口
//		PopupOverlay pop = new PopupOverlay(mMapView,new PopupClickListener() {                
//		        @Override
//		        public void onClickedPopup(int index) {
//		                //在此处理pop点击事件，index为点击区域索引,点击区域最多可有三个
//		        }
//		});
		
		/**
		 * 创建一个popupoverlay
		 */
		PopupClickListener popListener = new PopupClickListener(){
			@Override
			public void onClickedPopup(int index) {
				//写上 pop的响应事件
			}
		};
		
		
		//手动触发一次请求定位事件
//		public void requestLocationClick(){
//			
//			isRequest =true;
//			 mLocationClient.requestLocation();
//		    Toast.makeText(MyFootprintActivity.this, "正在定位……", Toast.LENGTH_SHORT).show();
//		}
		
		
		//清除屏幕上的标志 
		public  void clearOverlay(){
			
			if (pop != null){
		        pop.hidePop();
			}
			//清除overlay  
			 itemOverlay.removeAll();  
			 mMapView.refresh();
		}
		
		//添加标志
		public void initOverlay(){
			List<News> mNewsList = new ArrayList<News>();
			//这里填写要显示的地点的经纬度  举例四个
//			double mLat1 = 39.90923;
//			double mLon1 = 116.397428;
//			double mLat2 = 39.9022;
//			double mLon2 = 116.3922;
//			double mLat3 = 39.917723;
//			double mLon3 = 116.3722;
			
			
			News new1 = new News();
			News new2 = new News();
			News new3 = new News();
			
			new1.setNewsLatitude(39.90923);
			new1.setNewsLongtitude(116.397428);
			new1.setNewsContent("这里的景色很好哦");
			
			new2.setNewsLatitude(39.9022);
			new2.setNewsLongtitude(116.3922);
			new2.setNewsContent("宣武门···");
			
			new3.setNewsLatitude(39.917723);
			new3.setNewsLongtitude(116.3722);
			new3.setNewsContent("天啦！北京西城也有福建小吃");
			
			mNewsList.add(new1);
			mNewsList.add(new2);
			mNewsList.add(new3);
			
			/*
			 * 暂时注释掉     这个地方是从服务器上获取列表数据  现在用上面的假数据代替下
			 * */
//			mNewsList=mNewsService.getMyNewsList();
			
		
			List<GeoPoint> listPoint =  new ArrayList<GeoPoint>();
			
			
//			GeoPoint tempGeoPoint  = new GeoPoint();

			for(int i=0;i<mNewsList.size();i++){
				GeoPoint point =new GeoPoint((int)(mNewsList.get(i).getNewsLatitude() * 1E6),(int)(mNewsList.get(i).getNewsLongtitude() * 1E6));
				listPoint.add(point);
			}
			//准备overlay图像数据，根据实情情况修复
			Drawable mark= getResources().getDrawable(R.drawable.icon_marka);
			//用OverlayItem准备Overlay数据
//			OverlayItem item1 = new OverlayItem(p1,"这里的景色很好哦","item1");
		
			//使用setMarker()方法设置overlay图片,如果不设置则使用构建ItemizedOverlay时的默认设置
//			OverlayItem item2 = new OverlayItem(p2,"宣武门····","item2");
//			item2.setMarker(mark);
//			OverlayItem item3 = new OverlayItem(p3,"天啦！北京西城也有福建小吃","item3");
			
			//创建IteminizedOverlay
			itemOverlay = new OverlayTest(mark, mMapView);
			 
			List<OverlayItem> listItems = new ArrayList<OverlayItem>();

			for(int i=0;i<mNewsList.size();i++){
				OverlayItem item =new OverlayItem(listPoint.get(i), mNewsList.get(i).getNewsContent(), "");
				itemOverlay.addItem(item);
			}
			
			
			
			//将IteminizedOverlay添加到MapView中
		
			mMapView.getOverlays().clear();
			mMapView.getOverlays().add(itemOverlay);
			
			
			viewCache = getLayoutInflater().inflate(R.layout.map_custom_text_view, null);
		    popupInfo = (View) viewCache.findViewById(R.id.popinfo);
		    popupLeft = (View) viewCache.findViewById(R.id.popleft);
		    popupRight = (View) viewCache.findViewById(R.id.popright);
		    popupText =(TextView) viewCache.findViewById(R.id.textcache);
		    
			 
			//现在所有准备工作已准备好，使用以下方法管理overlay.
			//添加overlay, 当批量添加Overlay时使用addItem(List<OverlayItem>)效率更高
//			itemOverlay.addItem(item1);
//			itemOverlay.addItem(item2);
//			itemOverlay.addItem(item3);
			mMapView.refresh();
			 
		}
		
		
		
		@Override
		protected void onDestroy(){
		        mMapView.destroy();
		        if(app.mBMapManager!=null){
		                app.mBMapManager.destroy();
		                app.mBMapManager=null;
		        }
		        if (pop != null){
		            pop.hidePop();
		    	}
		        super.onDestroy();
		}
		@Override
		protected void onPause(){
		        mMapView.onPause();
		        if(app.mBMapManager!=null){
		            app.mBMapManager.stop();
		        }
		        super.onPause();
		}
		@Override
		protected void onResume(){
		        mMapView.onResume();
		        if(app.mBMapManager!=null){
		                app.mBMapManager.start();
		        }
		    super.onResume();
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
					    BMapUtil.getBitmapFromView(popupLeft), 		
					    BMapUtil.getBitmapFromView(popupInfo), 		
					    BMapUtil.getBitmapFromView(popupRight) 		
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
//		public class MyLocationListenner implements BDLocationListener{
//
//			@Override
//			public void onReceiveLocation(BDLocation location) {
//				
//				if(location == null)
//					return;
//				
//				Toast.makeText(getApplicationContext(), "dingwei", Toast.LENGTH_LONG).show();
//				
//				locData.latitude = location.getLatitude();
//	            locData.longitude = location.getLongitude();
//	            
//	          //是手动触发请求或首次定位时，移动到定位点
//	            if (isRequest || isFirstLoc){
//	            	//移动地图到定位点
////	                mMapController.animateTo(new GeoPoint((int)(locData.latitude* 1e6), (int)(locData.longitude *  1e6)));
//	            	mMapController.setCenter(new GeoPoint((int)(locData.latitude* 1e6), (int)(locData.longitude *  1e6)));
//	                mMapController.setZoom(12);
//
//	                isRequest = false;
//	            }
//	            //首次定位完成
//	            isFirstLoc = false;
//			}
//
//			@Override
//			public void onReceivePoi(BDLocation poiLocation) {
//
//				if(poiLocation ==  null)
//					return;
//			}
//			
//		}
		
	    
		
}