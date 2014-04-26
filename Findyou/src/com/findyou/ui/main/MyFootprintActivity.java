package com.findyou.ui.main;


import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.map.LocationData;
import com.baidu.mapapi.map.MapController;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationOverlay;
import com.baidu.mapapi.map.PopupClickListener;
import com.baidu.mapapi.map.PopupOverlay;
import com.baidu.platform.comapi.basestruct.GeoPoint;
import com.findyou.R;
import com.findyou.app.FindYouApplication;
import com.findyou.domain.Service.MyNewsService;
import com.findyou.domain.tool.BMapUtil;

import android.app.Activity;
import android.content.Context;
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
		
//		OverlayTest itemOverlay=null;
//		private OverlayItem mCurItem = null;
		
		// 定位相关
		private LocationClient mLocClient;
		private LocationData locData = null;
		
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
//		OnCheckedChangeListener radioButtonListener = null;
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
//				clearOverlay();
			}
		});
		
	    mResetBtn = (Button) findViewById(R.id.reset);
	    mResetBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
	
//				initOverlay();
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
//		MyLocationListenner myListenner = new MyLocationListenner();
		mLocClient.registerLocationListener(myListener);
		LocationClientOption option = new LocationClientOption();
		option.setOpenGps(true);
		option.setScanSpan(1000);
		mLocClient.setLocOption(option);
		mLocClient.start();
//		
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
		
	
//	    pop = new PopupOverlay(mMapView,popListener);
		    
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
//		PopupClickListener popListener = new PopupClickListener(){
//			@Override
//			public void onClickedPopup(int index) {
//				//写上 pop的响应事件
//			}
//		};

		public void createPaopao(){
			viewCache = getLayoutInflater().inflate(R.layout.map_custom_text_view, null);
	        popupText =(TextView) viewCache.findViewById(R.id.textcache);
	        //泡泡点击响应回调
	        PopupClickListener popListener = new PopupClickListener(){
				@Override
				public void onClickedPopup(int index) {

				}
	        };
	        pop = new PopupOverlay(mMapView,popListener);
	        MyLocationMapView.pop = pop;
		}		
		
		
		//手动触发一次请求定位事件
		public void requestLocationClick(){
			
			isRequest =true;
			 mLocClient.requestLocation();
		    Toast.makeText(MyFootprintActivity.this, "正在定位……", Toast.LENGTH_SHORT).show();
		}
		
		
		//清除屏幕上的标志 
//		public  void clearOverlay(){
//			
//			if (pop != null){
//		        pop.hidePop();
//			}
//			//清除overlay  
//			 itemOverlay.removeAll();  
//			 mMapView.refresh();
//		}
		
		//添加标志
//		public void initOverlay(){
//			List<News> mNewsList = new ArrayList<News>();
//			//这里填写要显示的地点的经纬度  举例四个
////			double mLat1 = 39.90923;
////			double mLon1 = 116.397428;
////			double mLat2 = 39.9022;
////			double mLon2 = 116.3922;
////			double mLat3 = 39.917723;
////			double mLon3 = 116.3722;
//			
//			
//			News new1 = new News();
//			News new2 = new News();
//			News new3 = new News();
//			
//			new1.setNewsLatitude(39.90923);
//			new1.setNewsLongtitude(116.397428);
//			new1.setNewsContent("这里的景色很好哦");
//			
//			new2.setNewsLatitude(39.9022);
//			new2.setNewsLongtitude(116.3922);
//			new2.setNewsContent("宣武门···");
//			
//			new3.setNewsLatitude(39.917723);
//			new3.setNewsLongtitude(116.3722);
//			new3.setNewsContent("天啦！北京西城也有福建小吃");
//			
//			mNewsList.add(new1);
//			mNewsList.add(new2);
//			mNewsList.add(new3);
//			
//			/*
//			 * 暂时注释掉     这个地方是从服务器上获取列表数据  现在用上面的假数据代替下
//			 * */
////			mNewsList=mNewsService.getMyNewsList();
//			
//		
//			List<GeoPoint> listPoint =  new ArrayList<GeoPoint>();
//			
//			GeoPoint[] point=new GeoPoint[mNewsList.size()];
//
//			for(int i=0;i<mNewsList.size();i++){
//				point[i] =new GeoPoint((int)(mNewsList.get(i).getNewsLatitude() * 1E6),(int)(mNewsList.get(i).getNewsLongtitude() * 1E6));
//				listPoint.add(point[i]);
//			}
//			//准备overlay图像数据，根据实情情况修复
//			Drawable mark= getResources().getDrawable(R.drawable.icon_marka);
//			//用OverlayItem准备Overlay数据
////			OverlayItem item1 = new OverlayItem(p1,"这里的景色很好哦","item1");
//		
//			//使用setMarker()方法设置overlay图片,如果不设置则使用构建ItemizedOverlay时的默认设置
////			OverlayItem item2 = new OverlayItem(p2,"宣武门····","item2");
////			item2.setMarker(mark);
////			OverlayItem item3 = new OverlayItem(p3,"天啦！北京西城也有福建小吃","item3");
//			
//			//创建IteminizedOverlay
//			itemOverlay = new OverlayTest(mark, mMapView);
//			 
//			List<OverlayItem> listItems = new ArrayList<OverlayItem>();
//
//			//创建IteminizedOverlay
//			itemOverlay = new OverlayTest(mark, mMapView);
//			//将IteminizedOverlay添加到MapView中
//			for(int i=0;i<mNewsList.size();i++){
//				OverlayItem item =new OverlayItem(listPoint.get(i), mNewsList.get(i).getNewsContent(), "");
//				itemOverlay.addItem(item);
//			}
//			mMapView.getOverlays().clear();
//			mMapView.getOverlays().add(itemOverlay);
//			
//			
//			viewCache = getLayoutInflater().inflate(R.layout.map_custom_text_view, null);
//		    popupInfo = (View) viewCache.findViewById(R.id.popinfo);
//		    popupLeft = (View) viewCache.findViewById(R.id.popleft);
//		    popupRight = (View) viewCache.findViewById(R.id.popright);
//		    popupText =(TextView) viewCache.findViewById(R.id.textcache);
//		    
//			 
//			//现在所有准备工作已准备好，使用以下方法管理overlay.
//			//添加overlay, 当批量添加Overlay时使用addItem(List<OverlayItem>)效率更高
////			itemOverlay.addItem(item1);
////			itemOverlay.addItem(item2);
////			itemOverlay.addItem(item3);
//			mMapView.refresh();
//			 
//		}
		
		
		
		@Override
		protected void onDestroy(){
		        mMapView.destroy();
		        if(mLocClient != null)
		        	mLocClient.stop();
		        
//		        if(app.mBMapManager!=null){
//		                app.mBMapManager.destroy();
//		                app.mBMapManager=null;
//		        }
//		        if (pop != null){
//		            pop.hidePop();
//		    	}
		        super.onDestroy();
		}
		@Override
		protected void onPause(){
		        mMapView.onPause();
//		        if(app.mBMapManager!=null){
//		            app.mBMapManager.stop();
//		        }
		        super.onPause();
		}
		@Override
		protected void onResume(){
		        mMapView.onResume();
//		        if(app.mBMapManager!=null){
//		                app.mBMapManager.start();
//		        }
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
//		class OverlayTest extends ItemizedOverlay<OverlayItem> {
//		    //用MapView构造ItemizedOverlay
//		    public OverlayTest(Drawable mark,MapView mapView){
//		            super(mark,mapView);
//		    }
//		    protected boolean onTap(int index) {
//		        //在此处理item点击事件
//		       OverlayItem item = getItem(index);
//				mCurItem = item ;
//				   popupText.setText(getItem(index).getTitle());
//				   Bitmap[] bitMaps={
//					    BMapUtil.getBitmapFromView(popupLeft), 		
//					    BMapUtil.getBitmapFromView(popupInfo), 		
//					    BMapUtil.getBitmapFromView(popupRight) 		
//				    };
//				    pop.showPopup(bitMaps,item.getPoint(),32);
//				    
//		        return true;
//		    }
//		        public boolean onTap(GeoPoint pt, MapView mapView){
//		                //在此处理MapView的点击事件，当返回 true时
//		                super.onTap(pt,mapView);
//		                return false;
//		        }
//
//		}     
		
		
		    
		//定位
		public class MyLocationListenner implements BDLocationListener{

			@Override
			public void onReceiveLocation(BDLocation location) {
				
				if(location == null)
					return;
				
				Toast.makeText(getApplicationContext(), "dingwei", Toast.LENGTH_LONG).show();
				
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
//	            	mMapController.setCenter(new GeoPoint((int)(locData.latitude* 1e6), (int)(locData.longitude *  1e6)));
//	                mMapController.setZoom(12);

	                isRequest = false;
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
		
		//继承MyLocationOverlay重写dispatchTap实现点击处理
	  	public class locationOverlay extends MyLocationOverlay{

	  		public locationOverlay(MapView mapView) {
	  			super(mapView);
	  			// TODO Auto-generated constructor stub
	  		}
	  		@Override
	  		protected boolean dispatchTap() {
	  			// TODO Auto-generated method stub
	  			//处理点击事件,弹出泡泡
//	  			popupText.setBackgroundResource(R.drawable.popup);
				popupText.setText("我的位置");
				pop.showPopup(BMapUtil.getBitmapFromView(popupText),
						new GeoPoint((int)(locData.latitude*1e6), (int)(locData.longitude*1e6)),
						8);
	  			return true;
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