/**
 * 
 */
package com.findyou.domain.Service;

import java.util.List;

import com.findyou.domain.IService.IMyLocationService;
import com.findyou.model.LocationInfo;

/**
 * @author wwb
 *
 */
public class MyLocationService implements IMyLocationService {

	/* (non-Javadoc)
	 * @see com.findyou.domain.IService.IMyLocationService#shareMyLocation(double, double, java.lang.String, java.lang.String)
	 */
	@Override
	public boolean shareMyLocation(double latitude, double lontitude,
			String type, String content) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.findyou.domain.IService.IMyLocationService#getMyLocation()
	 */
	@Override
	public LocationInfo getMyLocation() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.findyou.domain.IService.IMyLocationService#getMyLocatinoList()
	 */
	@Override
	public List<LocationInfo> getMyLocatinoList() {
		// TODO Auto-generated method stub
		return null;
	}

}
