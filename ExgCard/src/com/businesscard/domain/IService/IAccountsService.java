package com.businesscard.domain.IService;

import com.businesscard.domain.Emtity.Accounts;

/**
 * �˺���ݲ����ӿ�
 * 
 * @author evil
 * 
 */
public interface IAccountsService {

	public Accounts findAccountByID(String account);

	public boolean addAccount(Accounts account);
	
	public boolean alterPwd(Accounts account);
}
