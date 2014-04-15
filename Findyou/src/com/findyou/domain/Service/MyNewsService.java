/**
 * 
 */
package com.findyou.domain.Service;

import java.util.ArrayList;
import java.util.List;

import com.findyou.domain.IService.IMyNewsService;
import com.findyou.domain.entity.News;
import com.findyou.domain.entity.UserInfo;


/**
 * @author wwb
 *
 */
public class MyNewsService implements IMyNewsService {

	/** (non-Javadoc)
	 * @see com.findyou.domain.IService.IMyLocationService#shareMyLocation(double, double, java.lang.String, java.lang.String)
	 * @return true 分享成功；false 分享失败
	 */
	@Override
	public boolean shareMyUserInfo(double latitude, double longtitude,
			String type, String content) {
		// TODO Auto-generated method stub
		return false;
	}

	/** (non-Javadoc)
	 * @see com.findyou.domain.IService.IMyLocationService#getMyLocatinoList()
	 */
	@Override
	public List<News> getMyNewsList() {
		// TODO Auto-generated method stub
		News temp=new News();
		News temp2=new News();
		News temp3=new News();
		List<News> mNews=new ArrayList<News>();
//		double mLat1 = 39.90923;
//		double mLon1 = 116.397428;
//		double mLat2 = 39.9022;
//		double mLon2 = 116.3922;
//		double mLat3 = 39.917723;
//		double mLon3 = 116.3722;
		temp.setNewsLatitude(39.90923);
		temp.setNewsLongtitude(116.39742);
		mNews.add(temp);
		return mNews;
	}

}
