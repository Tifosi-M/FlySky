package domain.SystemManagement;

/**
 * AbstractAccount entity provides the base persistence definition of the
 * Account entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractAccount implements java.io.Serializable {

	// Fields

	private String tel;
	private String pwd;

	// Constructors

	/** default constructor */
	public AbstractAccount() {
	}

	/** minimal constructor */
	public AbstractAccount(String tel) {
		this.tel = tel;
	}

	/** full constructor */
	public AbstractAccount(String tel, String pwd) {
		this.tel = tel;
		this.pwd = pwd;
	}

	// Property accessors

	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getPwd() {
		return this.pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

}