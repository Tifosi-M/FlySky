package domain.Memo;

import domain.SystemManagement.User;

/**
 * AbstractRealtime entity provides the base persistence definition of the
 * Realtime entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractRealtime implements java.io.Serializable {

	// Fields

	private Integer id;
	private User user;
	private String startTime;
	private String location;
	private Integer aging;
	private String content;
	private Integer priority;

	// Constructors

	/** default constructor */
	public AbstractRealtime() {
	}

	/** minimal constructor */
	public AbstractRealtime(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public AbstractRealtime(Integer id, User user, String startTime,
			String location, Integer aging, String content, Integer priority) {
		this.id = id;
		this.user = user;
		this.startTime = startTime;
		this.location = location;
		this.aging = aging;
		this.content = content;
		this.priority = priority;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Integer getAging() {
		return this.aging;
	}

	public void setAging(Integer aging) {
		this.aging = aging;
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