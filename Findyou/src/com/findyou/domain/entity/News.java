package com.findyou.domain.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName="news")
public class News {
	
	public News(){
		
	}
	
	@DatabaseField(generatedId = true)
	private String newsId;
	
	@DatabaseField(canBeNull=false)
	private String newsUserId;
	
	@DatabaseField(canBeNull=false)
	private String newsUserName;
	
	@DatabaseField(canBeNull=false)
	private String newsType;
	
	@DatabaseField(canBeNull=false)
	private String newsContent;
	
	@DatabaseField(canBeNull=false)
	private String newsLocation;
	
	@DatabaseField(canBeNull=false)
	private String newsTime;

	public String getNewsId() {
		return newsId;
	}

	public void setNewsId(String newsId) {
		this.newsId = newsId;
	}

	public String getNewsUserId() {
		return newsUserId;
	}

	public void setNewsUserId(String newsUserId) {
		this.newsUserId = newsUserId;
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

	public String getNewsLocation() {
		return newsLocation;
	}

	public void setNewsLocation(String newsLocation) {
		this.newsLocation = newsLocation;
	}

	public String getNewsTime() {
		return newsTime;
	}

	public void setNewsTime(String newsTime) {
		this.newsTime = newsTime;
	}
	
	
}
