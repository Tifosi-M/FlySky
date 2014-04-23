package domain.Service.Card;

import javax.jws.WebParam;
import javax.jws.WebService;
@WebService
public interface ICardService {
	public boolean uploadCardDBFile(@WebParam(name="tel")String tel,@WebParam(name="db")byte[]db);
	public byte[] downloadCardDBFile(@WebParam(name="tel")String tel);
	public boolean deleteCardDBFile(@WebParam(name="tel")String tel);
	
}
