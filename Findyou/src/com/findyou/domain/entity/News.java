package com.findyou.domain.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName="news")
public class News {
	
//	private static final long serialVersionUID = 6874823823497497357L;
	
	public News(){
		newsId=0;
		newsUserPhone="";
		newsUserName="";
		newsType="";
		newsContent="";
		newsLatitude=0;
		newsLongtitude=0;
		newsTime="";
		newsAddress="";
	}
	
	@DatabaseField(generatedId = true)
	private int newsId;
	
	@DatabaseField(canBeNull=false)
	private String newsUserPhone;
	
	@DatabaseField(canBeNull=false)
	private String newsUserName;
	
	@DatabaseField(canBeNull=false)
	private String newsType;
	
	@DatabaseField(canBeNull=false)
	private String newsContent;
	
	@DatabaseField(canBeNull=false)
	private double newsLatitude;
	
	@DatabaseField(canBeNull=false)
	private double newsLongtitude;
	
	@DatabaseField(canBeNull=false)
	private String newsTime;

	@DatabaseField(canBeNull=false)
	private String newsAddress;

	public int getNewsId() {
		return newsId;
	}

	public void setNewsId(int newsId) {
		this.newsId = newsId;
	}

	public String getNewsUserPhone() {
		return newsUserPhone;
	}

	public void setNewsUserPhone(String newsUserPhone) {
		this.newsUserPhone = newsUserPhone;
	}

	public String getNewsUserName() {
		return newsUserName;
	}

	public void setNewsUserName(String newsUserName) {
		this.newsUserName = newsUserName;
	}

	public String getNewsType() {
		return newsType;
	}

	public void setNewsType(String newsType) {
		this.newsType = newsType;
	}

	public String getNewsContent() {
		return newsContent;
	}

	public void setNewsContent(String newsContent) {
		this.newsContent = newsContent;
	}

	public double getNewsLatitude() {
		return newsLatitude;
	}

	public void setNewsLatitude(double newsLatitude) {
		this.newsLatitude = newsLatitude;
	}

	public double getNewsLongtitude() {
		return newsLongtitude;
	}

	public void setNewsLongtitude(double newsLongtitude) {
		this.newsLongtitude = newsLongtitude;
	}

	public String getNewsTime() {
		return newsTime;
	}

	public void setNewsTime(String newsTime) {
		this.newsTime = newsTime;
	}

	public String getNewsAddress() {
		return newsAddress;
	}

	public void setNewsAddress(String newsAddress) {
		this.newsAddress = newsAddress;
	}
	
	
	
	
}
