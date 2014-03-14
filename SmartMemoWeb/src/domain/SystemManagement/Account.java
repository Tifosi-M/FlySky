package domain.SystemManagement;

/**
 * Account entity. @author MyEclipse Persistence Tools
 */
public class Account extends AbstractAccount implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Account() {
	}

	/** minimal constructor */
	public Account(String tel) {
		super(tel);
	}

	/** full constructor */
	public Account(String tel, String pwd) {
		super(tel, pwd);
	}

}
