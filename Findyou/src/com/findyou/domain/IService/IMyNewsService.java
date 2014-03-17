package com.findyou.domain.IService;

import java.util.List;

import com.findyou.domain.entity.News;
import com.findyou.domain.entity.UserInfo;

public interface IMyNewsService {
	//分享我的动态
	public boolean shareMyUserInfo(double latitude,double lontitude,String type,String content);
	public List<News> getMyNewsList();//获取我的动态列表
}
