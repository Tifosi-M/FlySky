package com.businesscard.domain.Service;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.businesscard.domain.Emtity.Accounts;
import com.businesscard.domain.IService.IAccountsService;

public class AccountsService implements IAccountsService {

	private SQLiteDatabase db;
	private Cursor cursor;
	private String sql;

	public AccountsService(SQLiteDatabase db) {
		this.db = db;
	}

	@Override
	public Accounts findAccountByID(String account) {
		// TODO Auto-generated method stub
		Accounts accounts = new Accounts();
		sql = "select * from Accounts where userPhone='" + account + "'";
		cursor = db.rawQuery(sql, null);
		if (cursor.moveToFirst() == false) {
			return null;
		} else {
			for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor
					.moveToNext()) {
				int userPhoneColumn = cursor.getColumnIndex("userPhone");
				String userPhone = cursor.getString(userPhoneColumn);
				accounts.setAccount(userPhone);
				int pwdColumn = cursor.getColumnIndex("pwd");
				String pwd = cursor.getString(pwdColumn);
				accounts.setPwd(pwd);
			}
			return accounts;
		}
	}

	@Override
	public boolean addAccount(Accounts account) {
		// TODO Auto-generated method stub
		String userPhone = account.getAccount();
		String pwd = account.getPwd();
		sql = "insert into Accounts ( userPhone,pwd ) values ('"
				+ userPhone + "','" + pwd + "')";
		db.execSQL(sql);
		
		return true;
	}

	@Override
	public boolean alterPwd(Accounts account) {
		// TODO Auto-generated method stub
		return false;
	}

}
