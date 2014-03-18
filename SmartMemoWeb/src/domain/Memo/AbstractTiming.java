package domain.Memo;

import domain.SystemManagement.User;

/**
 * AbstractTiming entity provides the base persistence definition of the Timing
 * entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractTiming implements java.io.Serializable {

	// Fields

	private Integer timingId;
	private User user;
	private String startTime;
	private String endTime;
	private String location;
	private String content;
	private Integer priority;

	// Constructors

	/** default constructor */
	public AbstractTiming() {
	}

	/** minimal constructor */
	public AbstractTiming(Integer timingId) {
		this.timingId = timingId;
	}

	/** full constructor */
	public AbstractTiming(Integer timingId, User user, String startTime,
			String endTime, String location, String content, Integer priority) {
		this.timingId = timingId;
		this.user = user;
		this.startTime = startTime;
		this.endTime = endTime;
		this.location = location;
		this.content = content;
		this.priority = priority;
	}

	// Property accessors

	public Integer getTimingId() {
		return this.timingId;
	}

	public void setTimingId(Integer timingId) {
		this.timingId = timingId;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getStartTime() {
		return this.startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return this.endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getPriority() {
		return this.priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

}