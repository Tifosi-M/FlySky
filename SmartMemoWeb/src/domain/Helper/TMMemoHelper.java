package domain.Helper;

import domain.SystemManagement.User;

public class TMMemoHelper {
	private String timingId;
	private String userId;
	private String startTime;
	private String endTime;
	private String location;
	private String content;
	private String priority;

	public String getTimingId() {
		return timingId;
	}

	public void setTimingId(String timingId) {
		this.timingId = timingId;
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

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
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
