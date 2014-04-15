package com.findyou.ui.main;

import java.util.ArrayList;
import java.util.List;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.map.ItemizedOverlay;
import com.baidu.mapapi.map.MapController;
import com.baidu.mapapi.map.MapView;
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

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MyFootprintActivity extends Activity{
		
		private MapView mMapView = null;
		private FindYouApplication app;
		private MyNewsService mNewsService=null;
		private Button mClearBtn;
		private Button mResetBtn;
		
		private PopupOverlay   pop  = null;
	
		private TextView  popupText = null;
		private View viewCache = null;
		private View popupInfo = null;
		private View popupLeft = null;
		private View popupRight = null;
		
		OverlayTest itemOverlay=null;
		private OverlayItem mCurItem = null;
		
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			app = (FindYouApplication)this.getApplication();
			mNewsService=new MyNewsService();
	        if (app.mBMapManager == null) {
	            app.mBMapManager = new BMapManager(getApplicationContext());
	            app.mBMapManager.init(FindYouApplication.strKey,null);
	        }
		
		setContentView(R.layout.activity_my_footprint);
		
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
			
			
		mMapView.setBuiltInZoomControls(true);
		//设置启用内置的缩放控件
		MapController mMapController=mMapView.getController();
		// 得到mMapView的控制权,可以用它控制和驱动平移和缩放
		GeoPoint point =new GeoPoint((int)(39.915* 1E6),(int)(116.404* 1E6));
		//用给定的经纬度构造一个GeoPoint，单位是微度 (度 * 1E6)
		mMapController.setCenter(point);//设置地图中心点
		mMapController.setZoom(12);//设置地图zoom级别
		
	
	    pop = new PopupOverlay(mMapView,popListener);
		    
		}
		
		////创建pop对象，注册点击事件监听接口
		//PopupOverlay pop = new PopupOverlay(mMapView,new PopupClickListener() {                
		//        @Override
		//        public void onClickedPopup(int index) {
		//                //在此处理pop点击事件，index为点击区域索引,点击区域最多可有三个
		//        }
		//});
		
		/**
		 * 创建一个popupoverlay
		 */
		PopupClickListener popListener = new PopupClickListener(){
			@Override
			public void onClickedPopup(int index) {
				//写上 pop的响应事件
			}
		};
		
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
			new1.setNewsContent("new1");
			
			new2.setNewsLatitude(39.9022);
			new2.setNewsLongtitude(116.3922);
			new2.setNewsContent("new2");
			
			new3.setNewsLatitude(39.917723);
			new3.setNewsLongtitude(116.3722);
			new3.setNewsContent("new3");
			
			mNewsList.add(new1);
			mNewsList.add(new2);
			mNewsList.add(new3);
			
			/*
			 * 暂时注释掉     这个地方是从服务器上获取列表数据  现在用上面的假数据代替下
			 * */
//			mNewsList=mNewsService.getMyNewsList();
			
			// 用给定的经纬度构造GeoPoint，单位是微度 (度 * 1E6)
//			GeoPoint p1 = new GeoPoint((int) (mLat1 * 1E6), (int) (mLon1 * 1E6));
//			GeoPoint p2 = new GeoPoint((int) (mLat2 * 1E6), (int) (mLon2 * 1E6));
//			GeoPoint p3 = new GeoPoint((int) (mLat3 * 1E6), (int) (mLon3 * 1E6));

			
			List<GeoPoint> listPoint =  new ArrayList<GeoPoint>();
			
			GeoPoint[] point=null;
//			GeoPoint tempGeoPoint  = new GeoPoint();

			for(int i=0;i<mNewsList.size();i++){
				point[i] =new GeoPoint((int)(mNewsList.get(i).getNewsLatitude() * 1E6),(int)(mNewsList.get(i).getNewsLongtitude() * 1E6));
				listPoint.add(point[i]);
			}
			//准备overlay图像数据，根据实情情况修复
			Drawable mark= getResources().getDrawable(R.drawable.icon_marka);
			//用OverlayItem准备Overlay数据
//			OverlayItem item1 = new OverlayItem(p1,"这里的景色很好哦","item1");
		
			//使用setMarker()方法设置overlay图片,如果不设置则使用构建ItemizedOverlay时的默认设置
//			OverlayItem item2 = new OverlayItem(p2,"宣武门····","item2");
//			item2.setMarker(mark);
//			OverlayItem item3 = new OverlayItem(p3,"天啦！北京西城也有福建小吃","item3");
			 
			List<OverlayItem> listItems = new ArrayList<OverlayItem>();
//			OverlayItem[] item=null;
			for(int i=0;i<mNewsList.size();i++){
				OverlayItem item =new OverlayItem(listPoint.get(i), mNewsList.get(i).getNewsContent(), "");
				itemOverlay.addItem(item);
			}
			
			
			//创建IteminizedOverlay
			itemOverlay = new OverlayTest(mark, mMapView);
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
}