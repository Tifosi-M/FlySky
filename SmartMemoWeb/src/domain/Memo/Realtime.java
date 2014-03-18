package domain.Memo;

import java.sql.Timestamp;

import domain.SystemManagement.User;

/**
 * Realtime entity. @author MyEclipse Persistence Tools
 */
public class Realtime extends AbstractRealtime implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Realtime() {
	}

	/** minimal constructor */
	public Realtime(Integer id) {
		super(id);
	}

	/** full constructor */
	public Realtime(Integer id, User user, String startTime, String location,
			Integer aging, String content,Integer priority) {
		super(id, user, startTime, location, aging, content,priority);
	}

}
