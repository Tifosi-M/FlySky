package com.telc.domain.IService;

import com.telc.domain.Emtity.User;

/**
 * 
 * 注UserID=UserPhone
 * @author SZP
 */
public interface IUserService {

	public User getUserByUserPhone(String phoneNum);

	public boolean addUser(User user);

	public boolean updateUser(User user);//修改个人信息

}
