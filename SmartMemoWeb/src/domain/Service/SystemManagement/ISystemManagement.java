package domain.Service.SystemManagement;

import javax.jws.WebParam;
import javax.jws.WebService;

import domain.Helper.UserHelper;

@WebService
public interface ISystemManagement {
	public boolean saveUser(@WebParam(name = "User") UserHelper userHelper);

	public boolean updateUser(@WebParam(name = "User") UserHelper userHelper);

	public UserHelper getUserByTel(@WebParam(name = "Tel") String tel);

	public UserHelper getUserById(@WebParam(name = "UserId") String id);

	public boolean login(@WebParam(name = "tel") String tel,
			@WebParam(name = "pwd") String pwd);

	public boolean register(@WebParam(name = "tel")String tel,
			@WebParam(name = "pwd")String pwd);
}
