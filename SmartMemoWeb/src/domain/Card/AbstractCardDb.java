package domain.Card;

/**
 * AbstractCardDb entity provides the base persistence definition of the CardDb
 * entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractCardDb implements java.io.Serializable {

	// Fields

	private String tel;
	private byte[] dbfile;

	// Constructors

	/** default constructor */
	public AbstractCardDb() {
	}

	/** minimal constructor */
	public AbstractCardDb(String tel) {
		this.tel = tel;
	}

	/** full constructor */
	public AbstractCardDb(String tel, byte[] dbfile) {
		this.tel = tel;
		this.dbfile = dbfile;
	}

	// Property accessors

	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public byte[] getDbfile() {
		return this.dbfile;
	}

	public void setDbfile(byte[] dbfile) {
		this.dbfile = dbfile;
	}

}