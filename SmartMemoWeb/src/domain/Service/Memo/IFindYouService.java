package domain.Service.Memo;

import javax.jws.WebParam;
import javax.jws.WebService;

import domain.findyou.entity.UserInfo;

@WebService
public interface IFindYouService {

	public boolean add(@WebParam(name = "addUser") UserInfo friends);// 增加朋友

}
