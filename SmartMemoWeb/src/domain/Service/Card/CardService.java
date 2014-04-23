package domain.Service.Card;



import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import domain.Card.CardDb;
import domain.Card.CardDbDAO;
import domain.SystemManagement.AccountDAO;
import domain.SystemManagement.UserDAO;

public class CardService implements ICardService {
	private ApplicationContext ctx;
	private AccountDAO accountDAO;
	private CardDbDAO cardDbDAO;
	
	@Override
	public boolean uploadCardDBFile(String tel, byte[] db) {
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		cardDbDAO = CardDbDAO.getFromApplicationContext(ctx);
		CardDb cardDb = new CardDb();
		cardDb.setDbfile(db);
		cardDb.setTel(tel);
		try {
			cardDbDAO.save(cardDb);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public byte[] downloadCardDBFile(String tel) {
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		cardDbDAO = CardDbDAO.getFromApplicationContext(ctx);
		CardDb db = cardDbDAO.findById("tel");
		if(db!=null)
			return db.getDbfile();
		return null;
	}

	@Override
	public boolean deleteCardDBFile(String tel) {
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		cardDbDAO = CardDbDAO.getFromApplicationContext(ctx);
		CardDb db = cardDbDAO.findById("tel");
		if(db!=null){
			try {
				cardDbDAO.delete(db);
				return false;
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
			
		return false;
	}

}
