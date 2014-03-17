/**
 * 
 */
package com.findyou.domain.Service;

import java.util.List;

import com.findyou.domain.IService.IMyNewsService;
import com.findyou.domain.entity.News;
import com.findyou.domain.entity.UserInfo;


/**
 * @author wwb
 *
 */
public class MyNewsService implements IMyNewsService {

	/* (non-Javadoc)
	 * @see com.findyou.domain.IService.IMyLocationService#shareMyLocation(double, double, java.lang.String, java.lang.String)
	 */
	@Override
	public boolean shareMyUserInfo(double latitude, double longtitude,
			String type, String content) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.findyou.domain.IService.IMyLocationService#getMyLocatinoList()
	 */
	@Override
	public List<News> getMyNewsList() {
		// TODO Auto-generated method stub
		return null;
	}

}
