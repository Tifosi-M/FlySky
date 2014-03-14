package domain.Memo;

import java.sql.Timestamp;

import domain.SystemManagement.User;

/**
 * Timing entity. @author MyEclipse Persistence Tools
 */
public class Timing extends AbstractTiming implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Timing() {
	}

	/** minimal constructor */
	public Timing(Integer timingId) {
		super(timingId);
	}

	/** full constructor */
	public Timing(Integer timingId, User user, String startTime,
			String endTime, String location, String content, Integer priority) {
		super(timingId, user, startTime, endTime, location, content, priority);
	}

}
