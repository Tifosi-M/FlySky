package domain.Helper;

import java.sql.Timestamp;

import domain.SystemManagement.User;

public class RTMemoHelper {
	private String id;
	private String userId;
	private String startTime;
	private String location;
	private String aging;
	private String content;
	private String priority;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getAging() {
		return aging;
	}
	public void setAging(String aging) {
		this.aging = aging;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
}
