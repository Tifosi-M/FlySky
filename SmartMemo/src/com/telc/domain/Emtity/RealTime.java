package com.telc.domain.Emtity;

/**
 * 实时提醒实体
 * @author CZW
 *
 */
public class RealTime {

	private String real_id;
	private String start_time;
	private String location;
	private int aging;
	private String content;
	private String user_id;
	private int priority;
	private int isfinish;
	private String location_detail;
	public String getReal_id() {
		return real_id;
	}
	public void setReal_id(String real_id) {
		this.real_id = real_id;
	}
	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}

	public int getAging() {
		return aging;
	}
	public void setAging(int aging) {
		this.aging = aging;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public int getIsfinish() {
		return isfinish;
	}
	public void setIsfinish(int isfinish) {
		this.isfinish = isfinish;
	}
	public String getLocation_detail() {
		return location_detail;
	}
	public void setLocation_detail(String location_detail) {
		this.location_detail = location_detail;
	}
	
}
