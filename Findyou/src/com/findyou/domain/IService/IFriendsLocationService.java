package com.findyou.domain.IService;

import java.util.List;

import com.findyou.domain.entity.Friend;
import com.findyou.model.LocationInfo;

public interface IFriendsLocationService {
	public List<LocationInfo> getFriendsLocationList();

}
