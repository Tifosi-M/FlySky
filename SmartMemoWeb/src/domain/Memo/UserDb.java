package domain.Memo;

/**
 * UserDb entity. @author MyEclipse Persistence Tools
 */
public class UserDb extends AbstractUserDb implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public UserDb() {
	}

	/** minimal constructor */
	public UserDb(String userTel) {
		super(userTel);
	}

	/** full constructor */
	public UserDb(String userTel, byte[] memoDb) {
		super(userTel, memoDb);
	}

}
