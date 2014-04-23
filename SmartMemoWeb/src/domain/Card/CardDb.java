package domain.Card;

/**
 * CardDb entity. @author MyEclipse Persistence Tools
 */
public class CardDb extends AbstractCardDb implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public CardDb() {
	}

	/** minimal constructor */
	public CardDb(String tel) {
		super(tel);
	}

	/** full constructor */
	public CardDb(String tel, byte[] dbfile) {
		super(tel, dbfile);
	}

}
