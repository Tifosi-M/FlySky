package com.findyou.domain.IService;

import com.findyou.model.LocationInfo;

public interface IMyLocationService {
	public boolean shareMyLocation(double latitude,double lontitude,String type,String content);
	public LocationInfo getMyLocation();
}
