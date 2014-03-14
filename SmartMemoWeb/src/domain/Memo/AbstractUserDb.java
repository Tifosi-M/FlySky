package domain.Memo;

/**
 * AbstractUserDb entity provides the base persistence definition of the UserDb
 * entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractUserDb implements java.io.Serializable {

	// Fields

	private String userTel;
	private byte[] memoDb;

	// Constructors

	/** default constructor */
	public AbstractUserDb() {
	}

	/** minimal constructor */
	public AbstractUserDb(String userTel) {
		this.userTel = userTel;
	}

	/** full constructor */
	public AbstractUserDb(String userTel, byte[] memoDb) {
		this.userTel = userTel;
		this.memoDb = memoDb;
	}

	// Property accessors

	public String getUserTel() {
		return this.userTel;
	}

	public void setUserTel(String userTel) {
		this.userTel = userTel;
	}

	public byte[] getMemoDb() {
		return this.memoDb;
	}

	public void setMemoDb(byte[] memoDb) {
		this.memoDb = memoDb;
	}

}